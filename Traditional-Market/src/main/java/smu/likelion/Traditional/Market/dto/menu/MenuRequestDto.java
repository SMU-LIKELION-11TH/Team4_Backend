package smu.likelion.Traditional.Market.dto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.Menu;
import smu.likelion.Traditional.Market.domain.Store;

@Getter
@Setter
@Builder
public class MenuRequestDto {
    private String menuName;
    private Integer menuPrice;
    private String menuDesc;
    private String imageName;
    private String imageUrl;
    private Long storeId;

    public Menu toEntity(Store store){
        return Menu.builder()
                .menuName(this.menuName)
                .menuDesc(this.menuDesc)
                .menuPrice(this.menuPrice)
                .imageName(this.imageName)
                .imageUrl(this.imageUrl)
                .store(store)
                .build();
    }
}
