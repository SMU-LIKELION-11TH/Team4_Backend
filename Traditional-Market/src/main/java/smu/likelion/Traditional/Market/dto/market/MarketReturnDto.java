package smu.likelion.Traditional.Market.dto.market;

import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.Category;
import smu.likelion.Traditional.Market.domain.Market;

import java.util.List;

@Getter @Setter
public class MarketReturnDto {

    private Long id;

    private String marketName;

    private String marketAddress;

    private String marketDesc;

    //private String marketImageBase64;

    private String marketImageUrl;

    private List<Category> categoryList;

    public MarketReturnDto(Market market, String marketImageUrl){
        this.id = market.getId();
        this.marketName = market.getMarketName();
        this.marketAddress = market.getMarketAddress();
        this.marketDesc = market.getMarketDesc();
        this.marketImageUrl = marketImageUrl;
        //this.categoryList = market.getCategoryList();
    }
}
