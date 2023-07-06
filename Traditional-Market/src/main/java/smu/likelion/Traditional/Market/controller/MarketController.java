package smu.likelion.Traditional.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.Market;
import smu.likelion.Traditional.Market.domain.UploadFile;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;
import smu.likelion.Traditional.Market.service.ImageServiceImpl;
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
    ImageServiceImpl imageService;

    //사용자 인증 필요
    @PostMapping(value = "/markets", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createMarket(@RequestPart MarketRequestDto marketRequestDto, @RequestPart MultipartFile image){
        try{
            UploadFile uploadFile = imageService.storeFile(image);
            marketService.save(marketRequestDto, uploadFile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/markets")
    public ResponseEntity<List<MarketReturnDto>> getMarkets(){
        try{
            List<Market> marketList = marketService.findAll();
            List<MarketReturnDto> marketReturnDtoList = new ArrayList<>();
            for (Market market : marketList){
                String marketImageUrl = imageService.getFullPath(market.getStoreFilename());
                marketReturnDtoList.add(new MarketReturnDto(market, marketImageUrl));
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
                String marketImageUrl = imageService.getFullPath(market.getStoreFilename());
                return ResponseEntity.ok(new MarketReturnDto(market, marketImageUrl));
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //사용자 인증 필요
    @PutMapping("/markets/{id}")
    public ResponseEntity<?> updateMarket(@PathVariable("id") Long id, @RequestPart MarketRequestDto marketRequestDto, @RequestPart MultipartFile image){
        try {
            Optional<Market> marketOptional = marketService.findById(id);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                imageService.deleteFile(market.getStoreFilename());
                UploadFile uploadFile = imageService.storeFile(image);
                if(marketService.update(id, marketRequestDto, uploadFile)){
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
                Market market = marketOptional.get();
                imageService.deleteFile(market.getStoreFilename());
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
