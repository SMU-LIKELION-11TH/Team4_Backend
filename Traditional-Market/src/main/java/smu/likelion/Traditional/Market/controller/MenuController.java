package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.enums.Code;
import smu.likelion.Traditional.Market.dto.common.ReturnDto;
import smu.likelion.Traditional.Market.dto.menu.MenuRequestDto;
import smu.likelion.Traditional.Market.dto.menu.MenuReturnDto;
import smu.likelion.Traditional.Market.service.MenuServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MenuController {

    @Autowired
    MenuServiceImpl menuService;

    @PreAuthorize("hasRole('CEO') and (@permissionChecker.checkPermission(@storeServiceImpl.findById(#storeId).getUserId()))")
    @PostMapping("/store/{storeid}/menus")
    public ResponseEntity<ReturnDto> createMenu(
            @RequestPart(value = "file",required = false)MultipartFile multipartFile,
            @RequestPart(value = "menuRequestDto")MenuRequestDto menuRequestDto,
            @PathVariable(value = "storeid") Long storeId){
        MenuReturnDto menuReturnDto = menuService.save(multipartFile,menuRequestDto);
        return ResponseEntity.ok(ReturnDto.of(Code.OK));
    }

    @GetMapping("/store/{storeid}/menus")
    public ResponseEntity<ReturnDto> getAllMenu(){
        try{
            List<MenuReturnDto> menuReturnDtoList = menuService.findByAll();
            return ResponseEntity.ok(ReturnDto.of(Code.OK,menuReturnDtoList));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/store/{storeid}/menus/{menuid}")
    public ResponseEntity<ReturnDto> getMenuById(@PathVariable("storeid") Long storeid, @PathVariable("menuid") Long menuid){
        MenuReturnDto menuReturnDto = menuService.findById(menuid);
        return ResponseEntity.ok(ReturnDto.of(Code.OK,menuReturnDto));
    }

    @PreAuthorize("hasRole('CEO') and (@permissionChecker.checkPermission(@storeServiceImpl.findById(#id).getUserId()))")
    @PutMapping("/store/{storeid}/menus/{menuid}")
    public ResponseEntity<ReturnDto> updateMenuById(@PathVariable("storeid") Long id, @PathVariable("menuid") Long menuid,
                                                        @RequestPart("menuRequestDto") MenuRequestDto menuRequestDto,
                                                        @RequestPart(value = "file", required = false) MultipartFile file) {
        MenuReturnDto MenuReturnDto = menuService.update(menuid,menuRequestDto,file);
        return ResponseEntity.ok(ReturnDto.of(Code.OK));
    }

    @PreAuthorize("hasRole('CEO') and (@permissionChecker.checkPermission(@storeServiceImpl.findById(#storeId).getUserId()))")
    @DeleteMapping("/store/{storeid}/menus")
    public ResponseEntity<HttpStatus> deleteMenu(@RequestParam("menuid") Long id, @PathVariable("storeid") Long storeId){
        try{
            menuService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
