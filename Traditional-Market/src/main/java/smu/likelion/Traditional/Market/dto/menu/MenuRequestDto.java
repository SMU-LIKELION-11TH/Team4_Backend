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
    private byte[] menuImage;
    private Long storeId;

    public Menu toEntity(Store store){
        return Menu.builder()
                .menuName(this.menuName)
                .menuDesc(this.menuDesc)
                .menuPrice(this.menuPrice)
                .menuImage(this.menuImage)
                .store(store)
                .build();
    }
}
