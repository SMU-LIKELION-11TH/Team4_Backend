package smu.likelion.Traditional.Market.dto.store;

import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.domain.entity.Menu;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.StoreImage;
import smu.likelion.Traditional.Market.dto.menu.MenuReturnDto;
import smu.likelion.Traditional.Market.dto.storeimage.StoreImageReturnDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class StoreRequestDto {
    private Long storeId;
    private String storeName;
    private String storeDesc;
    private String startTime;
    private String endTime;
    private String roadAddress;
    private String detailAddress;
    private String storeTel;
    private List<MenuReturnDto> menuList;
    private List<StoreImageReturnDto> storeImageList;
    private Long categoryId;
    //builder어떻게 쓸지 고민하기.
    public StoreRequestDto(String storeName, String storeDesc, String startTime,String endTime, String roadAddress,String detailAddress, String storeTel, List<Menu> menuList, List<StoreImage> storeImageList,Long categoryId) {
        this.storeName = storeName;
        this.storeDesc = storeDesc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.storeTel = storeTel;
        this.menuList = menuList.stream().map(MenuReturnDto::new).collect(Collectors.toList());
        this.storeImageList = storeImageList.stream().map(StoreImageReturnDto::new).collect(Collectors.toList());
        this.categoryId = categoryId;
    }
    public StoreRequestDto(){

    }

    public Store toEntity(Category category){
        return Store.builder()
                .storeName(this.storeName)
                .storeDesc(this.storeDesc)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .roadAddress(this.roadAddress)
                .detailAddress(this.detailAddress)
                .storeTel(this.storeTel)
                //.menuList(this.menuList)
                //.storeImageList(this.storeImageList)
                .category(category)
                .build();
    }
}
