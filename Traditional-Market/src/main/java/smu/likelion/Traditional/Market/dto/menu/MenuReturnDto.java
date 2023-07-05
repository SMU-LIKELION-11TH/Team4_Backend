package smu.likelion.Traditional.Market.dto.menu;

import smu.likelion.Traditional.Market.domain.Menu;
import smu.likelion.Traditional.Market.domain.Store;

public class MenuReturnDto {
    private String menuName;
    private Integer menuPrice;
    private String menuDesc;
    private byte[] menuImage;
    private Long storeId;

    public MenuReturnDto(Menu menu) {
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
        this.menuDesc = menu.getMenuDesc();
        this.menuImage = menu.getMenuImage();
        this.storeId = menu.getStore().getId();
    }
}
