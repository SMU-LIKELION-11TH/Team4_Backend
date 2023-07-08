package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.dto.category.CategoryRequestDto;
import smu.likelion.Traditional.Market.dto.category.CategoryReturnDto;
import smu.likelion.Traditional.Market.service.CategoryServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    //사용자 인증 필요
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestParam("marketId") Long marketId, @RequestBody CategoryRequestDto categoryRequestDto){
        try{
            if(categoryService.save(categoryRequestDto, marketId)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryReturnDto>> getCategoriesByMarketId(@RequestParam("marketId") Long marketId){
        try{
            List<Category> categoryList = categoryService.findByMarketId(marketId);
            return ResponseEntity.ok(categoryList.stream().map(CategoryReturnDto::new).collect(Collectors.toList()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    @GetMapping("/category")
//    public ResponseEntity<List<CategoryReturnDto>> getCategoriesByMarketName(@RequestParam("marketName") String marketName){
//        try{
//            Optional<Market> marketOptional = marketService.findByMarketName(marketName);
//            if(marketOptional.isPresent()){
//                List<Category> categoryList = categoryService.findByMarketId(marketOptional.get().getId());
//                return ResponseEntity.ok(categoryList.stream().map(CategoryReturnDto::new).collect(Collectors.toList()));
//            }
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    //사용자 인증 필요
    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequestDto categoryRequestDto){
        try {
            if (categoryService.update(id, categoryRequestDto)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //사용자 인증 필요
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        try {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
