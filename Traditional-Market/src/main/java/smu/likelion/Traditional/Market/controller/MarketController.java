package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;
import smu.likelion.Traditional.Market.service.MarketServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MarketController {

    @Autowired
    MarketServiceImpl marketService;

    //사용자 인증 필요
    @PostMapping("/markets")
    public ResponseEntity<?> createMarket(@RequestBody MarketRequestDto marketRequestDto){
        try{
            //사용자 인증 필요한데..
            marketService.save(marketRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets")
    public ResponseEntity<List<MarketReturnDto>> getMarkets(){
        try{
            return ResponseEntity.ok(marketService.findAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets/{id}")
    public ResponseEntity<MarketReturnDto> getMarketById(@PathVariable("id") Long id){
        try{
            //Optional로 해야하나?
            MarketReturnDto marketReturnDto = marketService.findById(id);
            if(marketReturnDto != null){
                return ResponseEntity.ok(marketReturnDto);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //사용자 인증 필요
    @PutMapping("/markets/{id}")
    public ResponseEntity<?> updateMarket(@PathVariable("id") Long id, @RequestBody MarketRequestDto marketRequestDto){
        try {
            if(marketService.update(id, marketRequestDto)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //사용자 인증 필요
    @DeleteMapping("/markets/{id}")
    public ResponseEntity<?> deleteMarket(@PathVariable("id") Long id){
        try {
            marketService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
