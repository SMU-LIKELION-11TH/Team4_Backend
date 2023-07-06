package smu.likelion.Traditional.Market.dto.market;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.Market;

@Getter
public class MarketReturnDto {

    private Long id;

    private String marketName;

    private String marketAddress;

    private String marketDesc;

    //private String marketImageBase64;

    private String marketImageUrl;

    public MarketReturnDto(Market market, String marketImageUrl){
        this.id = market.getId();
        this.marketName = market.getMarketName();
        this.marketAddress = market.getMarketAddress();
        this.marketDesc = market.getMarketDesc();
        this.marketImageUrl = marketImageUrl;
    }
}
