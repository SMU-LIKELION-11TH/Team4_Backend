package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.domain.entity.Market;
import smu.likelion.Traditional.Market.domain.enums.Code;
import smu.likelion.Traditional.Market.dto.common.ReturnDto;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;
import smu.likelion.Traditional.Market.service.CategoryServiceImpl;
import smu.likelion.Traditional.Market.service.MarketServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MarketController {

    @Autowired
    MarketServiceImpl marketService;

    @Autowired
    CategoryServiceImpl categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/markets")
    public ResponseEntity<?> createMarket(@RequestBody MarketRequestDto marketRequestDto){
        try{
            Optional<Market> marketOptional = marketService.findByMarketName(marketRequestDto.getMarketName());
            if(marketOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 시장 이름입니다.");
            }
            marketService.save(marketRequestDto);
            return ResponseEntity.ok(ReturnDto.of(Code.OK));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets/all")
    public ResponseEntity<ReturnDto> getMarkets(){
        try{
            List<Market> marketList = marketService.findAll();
            List<MarketReturnDto> marketReturnDtoList = new ArrayList<>();
            for (Market market : marketList){
                marketReturnDtoList.add(new MarketReturnDto(market));
            }
            return ResponseEntity.ok(ReturnDto.of(Code.OK,marketReturnDtoList));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets/{id}")
    public ResponseEntity<ReturnDto> getMarketById(@PathVariable("id") Long id){
        try{
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                market.setCategoryList(categoryService.findByMarketId(id));
                return ResponseEntity.ok(ReturnDto.of(Code.OK,new MarketReturnDto(market)));
            }
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ReturnDto.of(Code.BAD_REQUEST));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets")
    public ResponseEntity<ReturnDto> getMarketByName(@RequestParam("marketName") String marketName){
        try{
            Optional<Market> marketOptional = marketService.findByMarketName(marketName);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                market.setCategoryList(categoryService.findByMarketId(market.getId()));
                return ResponseEntity.ok(ReturnDto.of(Code.OK, new MarketReturnDto(market)));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ReturnDto.of(Code.BAD_REQUEST));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/markets/{id}")
    public ResponseEntity<?> updateMarket(@PathVariable("id") Long id, @RequestBody MarketRequestDto marketRequestDto){
        try {
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                Optional<Market> marketConflict = marketService.findByMarketName(marketRequestDto.getMarketName());
                //그 이름이 이미 존재하고, 그 이름이 원래의 이름이 아닐 경우
                if(marketConflict.isPresent() && !marketConflict.get().getMarketName().equals(marketOptional.get().getMarketName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 시장 이름입니다.");
                }
                if(marketService.update(id, marketRequestDto)){
                    return ResponseEntity.ok(ReturnDto.of(Code.OK));
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/markets/{id}")
    public ResponseEntity<?> deleteMarket(@PathVariable("id") Long id){
        try {
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                marketService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
