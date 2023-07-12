package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.enums.Code;
import smu.likelion.Traditional.Market.dto.common.ReturnDto;
import smu.likelion.Traditional.Market.dto.store.StoreRequestDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;
import smu.likelion.Traditional.Market.service.StoreServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class StoreController {

    @Autowired
    StoreServiceImpl storeService;

    @PreAuthorize("hasRole('CEO')")
    @PostMapping(value = "/stores", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReturnDto> createStore(@RequestPart(value = "files",required = false) List<MultipartFile> multipartFiles,
                                                      @RequestPart(value = "storeRequestDto") StoreRequestDto storeRequestDto){
        StoreReturnDto storeReturnDto = storeService.save(multipartFiles,storeRequestDto);
        return ResponseEntity.ok(ReturnDto.of(Code.OK));
    }

    @GetMapping("/stores")
    public ResponseEntity<ReturnDto> getAllStore(){
        try{
            List<StoreReturnDto> storeReturnDtoList = storeService.findAll();

            return ResponseEntity.ok(ReturnDto.of(Code.OK,storeReturnDtoList));


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/stores/{storeid}")
    public ResponseEntity<ReturnDto> getStoreById(@PathVariable("storeid") Long id){
        StoreReturnDto storeReturnDto = storeService.findById(id);
        System.out.println(storeReturnDto);
        return ResponseEntity.ok(ReturnDto.of(Code.OK,storeReturnDto));
    }

    @GetMapping("/stores/by-category/{categoryId}")
    public ResponseEntity<ReturnDto> getStoreByCategoryId(@PathVariable("categoryId") Long id){
        List<StoreReturnDto> storeReturnDtoList = storeService.findByCategoryId(id);
        return ResponseEntity.ok(ReturnDto.of(Code.OK,storeReturnDtoList));
    }

    @PreAuthorize("hasRole('CEO') and (@permissionChecker.checkPermission(@storeServiceImpl.findById(#id).getUserId()))")
    @PutMapping(value = "/stores/{storeid}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReturnDto> updateStoreById(@PathVariable("storeid") Long id,
                                                          @RequestPart(value = "files",required = false) List<MultipartFile> multipartFiles,
                                                          @RequestPart(value = "storeRequestDto") StoreRequestDto storeRequestDto) {
        StoreReturnDto storeReturnDto = storeService.update(id,storeRequestDto,multipartFiles);
        return ResponseEntity.ok(ReturnDto.of(Code.OK,storeReturnDto));
    }

    @PreAuthorize("hasRole('CEO') and (@permissionChecker.checkPermission(@storeServiceImpl.findById(#id).getUserId()))")
    @DeleteMapping("/stores")
    public ResponseEntity<HttpStatus> deleteStore(@RequestParam("storeid") Long id){
        try{
            storeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}