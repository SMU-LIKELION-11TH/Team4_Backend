package smu.likelion.Traditional.Market.dto.storeimage;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.entity.StoreImage;

@Getter
public class StoreImageReturnDto {

    private Long id;

    private String storeFilename;

    private String storeImageUrl;

    public StoreImageReturnDto(StoreImage storeImage){
        this.id = storeImage.getId();
        this.storeFilename = storeImage.getStoreFilename();
        this.storeImageUrl = storeImage.getStoreImageUrl();
    }
}
