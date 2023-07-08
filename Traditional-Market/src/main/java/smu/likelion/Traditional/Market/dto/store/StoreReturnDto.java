package smu.likelion.Traditional.Market.dto.store;

import lombok.*;
import smu.likelion.Traditional.Market.domain.entity.Menu;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.StoreImage;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreReturnDto {
    private Long storeId;
    private String storeName;
    private String storeDesc;
    private String storeAddress;
    private String storeTime;
    private String storeTel;
    private List<Menu> menuList;
    private List<StoreImage> storeImageList;
    private Long categoryId;


    public StoreReturnDto(Store store){
        this.storeId = store.getId();
        this.storeName = store.getStoreName();
        this.storeDesc = store.getStoreDesc();
        this.storeAddress = store.getStoreAddress();
        this.storeTime = store.getStoreTime();
        this.storeTel = store.getStoreTel();
        this.menuList = store.getMenuList();
        this.storeImageList = store.getStoreImageList();
        this.categoryId = store.getCategory().getId();

    }
}
