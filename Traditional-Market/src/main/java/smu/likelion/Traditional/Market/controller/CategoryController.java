package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smu.likelion.Traditional.Market.dto.category.CategoryReturnDto;
import smu.likelion.Traditional.Market.service.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryReturnDto>> getCategoriesByMarketId(@RequestParam("market_id") Long marketId){
        try{
            System.out.println(">>> marketId = " + marketId);
            return ResponseEntity.ok(categoryService.findByMarketId(marketId));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
