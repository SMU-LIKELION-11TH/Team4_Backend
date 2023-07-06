package smu.likelion.Traditional.Market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.likelion.Traditional.Market.domain.Market;
import smu.likelion.Traditional.Market.domain.UploadFile;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;
import smu.likelion.Traditional.Market.repository.MarketRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarketServiceImpl implements MarketService{

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public void save(MarketRequestDto marketRequestDto, UploadFile uploadFile){
        try{
            marketRepository.save(new Market(marketRequestDto, uploadFile));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    @Override
    public List<MarketReturnDto> findAll(){
        try{
            List<Market> marketList = marketRepository.findAll();
            return marketList.stream().map(MarketReturnDto::new).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public Optional<Market> findById(Long id){
        try {
            return marketRepository.findById(id);
            /*
            Optional<Market> market = marketRepository.findById(id);
            if(market.isPresent()){
                return market.get();
            }*/
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Long id, MarketRequestDto marketRequestDto, UploadFile uploadFile){
        try {
            Optional<Market> marketOptional = marketRepository.findById(id);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                market.setMarketName(marketRequestDto.getMarketName());
                market.setMarketAddress(marketRequestDto.getMarketAddress());
                market.setMarketDesc(marketRequestDto.getMarketDesc());
                market.setUploadFilename(uploadFile.getUploadFilename());
                market.setStoreFilename(uploadFile.getStoreFilename());
                marketRepository.save(market);
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(Long id){
        try {
            marketRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
