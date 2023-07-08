package smu.likelion.Traditional.Market.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.domain.entity.Menu;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.StoreImage;

import java.util.List;

@Getter @Setter
public class StoreRequestDto {
    private Long storeId;
    private String storeName;
    private String storeDesc;
    private String storeAddress;
    private String storeTime;
    private String storeTel;
    private List<Menu> menuList;
    private List<StoreImage> storeImageList;
    private Long categoryId;
    //builder어떻게 쓸지 고민하기.
    public StoreRequestDto(String storeName, String storeDesc, String storeAddress, String storeTime, String storeTel, List<Menu> menuList, List<StoreImage> storeImageList,Long categoryId) {
        this.storeName = storeName;
        this.storeDesc = storeDesc;
        this.storeAddress = storeAddress;
        this.storeTime = storeTime;
        this.storeTel = storeTel;
        this.menuList = menuList;
        this.storeImageList = storeImageList;
        this.categoryId = categoryId;
    }
    public StoreRequestDto(){

    }

    public Store toEntity(Category category){
        return Store.builder()
                .storeName(this.storeName)
                .storeDesc(this.storeDesc)
                .storeAddress(this.storeAddress)
                .storeTime(this.storeTime)
                .storeTel(this.storeTel)
                .menuList(this.menuList)
                .storeImageList(this.storeImageList)
                .category(category)
                .build();
    }
}
