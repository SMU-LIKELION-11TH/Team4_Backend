package smu.likelion.Traditional.Market.dto.menu;

import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.Menu;

@Getter
@Setter
public class MenuReturnDto {
    private String menuName;
    private Integer menuPrice;
    private String menuDesc;
    private String imageUrl;
    private String imageName;
    private Long storeId;

    public MenuReturnDto(Menu menu) {
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
        this.menuDesc = menu.getMenuDesc();
        this.imageName = menu.getImageName();
        this.imageUrl = menu.getImageUrl();
        this.storeId = menu.getStore().getId();
    }
}
