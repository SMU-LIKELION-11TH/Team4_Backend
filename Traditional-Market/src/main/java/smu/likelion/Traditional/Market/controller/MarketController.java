package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.domain.entity.Market;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;
import smu.likelion.Traditional.Market.service.CategoryServiceImpl;
import smu.likelion.Traditional.Market.service.MarketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MarketController {

    @Autowired
    MarketServiceImpl marketService;

    @Autowired
    CategoryServiceImpl categoryService;

    //사용자 인증 필요
    @PostMapping(value = "/markets")
    public ResponseEntity<?> createMarket(@RequestBody MarketRequestDto marketRequestDto, HttpServletRequest request){
        try{
            Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
            String role = (String) claims.get("role");
            if(role.equals("ADMIN")){
                Optional<Market> marketOptional = marketService.findByMarketName(marketRequestDto.getMarketName());
                if(marketOptional.isPresent()){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 시장 이름입니다.");
                }
                marketService.save(marketRequestDto);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets/all")
    public ResponseEntity<List<MarketReturnDto>> getMarkets(){
        try{
            List<Market> marketList = marketService.findAll();
            List<MarketReturnDto> marketReturnDtoList = new ArrayList<>();
            for (Market market : marketList){
                //String marketImageUrl = imageService.getFullPath(market.getStoreFilename());
                marketReturnDtoList.add(new MarketReturnDto(market));
            }
            return ResponseEntity.ok(marketReturnDtoList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets/{id}")
    public ResponseEntity<MarketReturnDto> getMarketById(@PathVariable("id") Long id){
        try{
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                //String marketImageUrl = imageService.getFullPath(market.getStoreFilename());
                market.setCategoryList(categoryService.findByMarketId(id));
                return ResponseEntity.ok(new MarketReturnDto(market));
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets")
    public ResponseEntity<MarketReturnDto> getMarketByName(@RequestParam("marketName") String marketName){
        try{
            Optional<Market> marketOptional = marketService.findByMarketName(marketName);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                //String marketImageUrl = imageService.getFullPath(market.getStoreFilename());
                market.setCategoryList(categoryService.findByMarketId(market.getId()));
                return ResponseEntity.ok(new MarketReturnDto(market));
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
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                //Market market = marketOptional.get();
                //imageService.deleteFile(market.getStoreFilename());
                //UploadFile uploadFile = imageService.storeFile(image);
                if(marketService.update(id, marketRequestDto)){
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                //Market market = marketOptional.get();
                marketService.delete(id);
                //imageService.deleteFile(market.getStoreFilename());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
