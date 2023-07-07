package smu.likelion.Traditional.Market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.likelion.Traditional.Market.domain.entity.Market;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.repository.MarketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService{

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public void save(MarketRequestDto marketRequestDto){
        try{
            marketRepository.save(new Market(marketRequestDto));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Market> findAll(){
        try{
            return marketRepository.findAll();
            //List<Market> marketList = marketRepository.findAll();
            //return marketList.stream().map(MarketReturnDto::new).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Market> findById(Long id){
        try {
            return marketRepository.findById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Market> findByMarketName(String marketName){
        try {
            return marketRepository.findByMarketName(marketName);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Long id, MarketRequestDto marketRequestDto){
        try {
            Optional<Market> marketOptional = marketRepository.findById(id);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                market.setMarketName(marketRequestDto.getMarketName());
                market.setMarketAddress(marketRequestDto.getMarketAddress());
                market.setMarketDesc(marketRequestDto.getMarketDesc());
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
