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
    private String startTime;
    private String endTime;
    private String roadAddress;
    private String detailAddress;
    private String storeTel;
    private List<Menu> menuList;
    private List<StoreImage> storeImageList;
    private Long categoryId;


    public StoreReturnDto(Store store){
        this.storeId = store.getId();
        this.storeName = store.getStoreName();
        this.storeDesc = store.getStoreDesc();
        this.roadAddress = store.getRoadAddress();
        this.detailAddress = store.getDetailAddress();
        this.startTime = store.getStartTime();
        this.endTime = store.getEndTime();
        this.storeTel = store.getStoreTel();
        this.menuList = store.getMenuList();
        this.storeImageList = store.getStoreImageList();
        this.categoryId = store.getCategory().getId();

    }
}
