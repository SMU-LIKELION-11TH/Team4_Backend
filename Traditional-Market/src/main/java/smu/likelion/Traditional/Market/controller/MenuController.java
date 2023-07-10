package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping("/store/{storeid}/menus")
    public ResponseEntity<MenuReturnDto> createMenu(
            @RequestPart(value = "file",required = false)MultipartFile multipartFile,
            @RequestPart(value = "menuRequestDto")MenuRequestDto menuRequestDto){
        MenuReturnDto menuReturnDto = menuService.save(multipartFile,menuRequestDto);
        return ResponseEntity.ok(menuReturnDto);
    }

    @GetMapping("/store/{storeid}/menus")
    public ResponseEntity<List<MenuReturnDto>> getAllMenu(){
        try{
            List<MenuReturnDto> menuReturnDtoList = menuService.findByAll();
            return ResponseEntity.ok(menuReturnDtoList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/store/{storeid}/menus/{menuid}")
    public ResponseEntity<MenuReturnDto> getMenuById(@PathVariable("storeid") Long storeid, @PathVariable("menuid") Long menuid){
        MenuReturnDto menuReturnDto = menuService.findById(menuid);
        return ResponseEntity.ok(menuReturnDto);
    }

    @PutMapping("/store/{storeid}/menus/{menuid}")
    public ResponseEntity<MenuReturnDto> updateMenuById(@PathVariable("storeid") Long id, @PathVariable("menuid") Long menuid,
                                                        @RequestPart("menuRequestDto") MenuRequestDto menuRequestDto,
                                                        @RequestPart(value = "file", required = false) MultipartFile file) {
        MenuReturnDto MenuReturnDto = menuService.update(menuid,menuRequestDto,file);
        return ResponseEntity.ok(MenuReturnDto);
    }

    @DeleteMapping("/store/{storeid}/menus")
    public ResponseEntity<HttpStatus> deleteMenu(@RequestParam("menuid") Long id){
        try{
            menuService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
