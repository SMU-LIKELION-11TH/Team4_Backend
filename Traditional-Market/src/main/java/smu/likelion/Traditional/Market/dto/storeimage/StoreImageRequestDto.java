package smu.likelion.Traditional.Market.dto.storeimage;

import lombok.*;
import smu.likelion.Traditional.Market.domain.Store;
import smu.likelion.Traditional.Market.domain.StoreImage;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreImageRequestDto {
    private String storeFilename; //저장될 이름
    private String storeImageUrl;
    private Store store;



    public StoreImage toEntity(){
        return StoreImage.builder()
                .storeFilename(this.storeFilename)
                .storeImageUrl(this.storeImageUrl)
                .build();
    }



}
