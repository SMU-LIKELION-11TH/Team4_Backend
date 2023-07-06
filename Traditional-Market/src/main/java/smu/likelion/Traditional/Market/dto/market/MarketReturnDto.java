package smu.likelion.Traditional.Market.dto.market;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.Market;

@Getter
public class MarketReturnDto {

    private Long id;

    private String marketName;

    private String marketAddress;

    private String marketDesc;

    private String marketImage;

    public MarketReturnDto(Market market){
        this.id = market.getId();
        this.marketName = market.getMarketName();
        this.marketAddress = market.getMarketAddress();
        this.marketDesc = market.getMarketDesc();
        //this.marketImage = market.getMarketImage();
    }
}
