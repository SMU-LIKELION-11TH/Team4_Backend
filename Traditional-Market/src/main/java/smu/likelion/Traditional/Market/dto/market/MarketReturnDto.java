package smu.likelion.Traditional.Market.dto.market;

import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.Market;
import smu.likelion.Traditional.Market.dto.category.CategoryReturnDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class MarketReturnDto {

    private Long id;

    private String marketName;

    private String marketAddress;

    private String marketDesc;

    private List<CategoryReturnDto> categoryList;


    public MarketReturnDto(Market market){
        this.id = market.getId();
        this.marketName = market.getMarketName();
        this.marketAddress = market.getMarketAddress();
        this.marketDesc = market.getMarketDesc();
        this.categoryList = market.getCategoryList().stream().map(CategoryReturnDto::new).collect(Collectors.toList());
    }
}
