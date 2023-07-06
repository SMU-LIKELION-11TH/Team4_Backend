package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping("/stores")
    public ResponseEntity<StoreReturnDto> createStore(@RequestPart(value = "files",required = false) List<MultipartFile> multipartFiles,
                                                      @RequestPart(value = "storeRequestDto") StoreRequestDto storeRequestDto){
        StoreReturnDto storeReturnDto = storeService.save(multipartFiles,storeRequestDto);
        return ResponseEntity.ok(storeReturnDto);
    }


    @GetMapping("/stores")
    public ResponseEntity<List<StoreReturnDto>> getAllStore(){
        try{
            List<StoreReturnDto> storeReturnDtoList = storeService.findAll();

            return ResponseEntity.ok(storeReturnDtoList);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/stores/{storeid}")
    public ResponseEntity<StoreReturnDto> getStoreById(@PathVariable("storeid") Long id){
        StoreReturnDto storeReturnDto = storeService.findById(id);
        return ResponseEntity.ok(storeReturnDto);
    }

    @PutMapping("/stores/{storeid}")
    public ResponseEntity<StoreReturnDto> updateStoreById(@PathVariable("storeid") Long id, @RequestBody StoreRequestDto storeRequestDto) {
        StoreReturnDto storeReturnDto = storeService.update(id,storeRequestDto);
        return ResponseEntity.ok(storeReturnDto);
    }

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