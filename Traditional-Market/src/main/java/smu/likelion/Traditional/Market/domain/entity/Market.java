package smu.likelion.Traditional.Market.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "markets")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "market_name")
    private String marketName;

    @NotNull
    @Column(name = "market_address")
    private String marketAddress;

    @NotNull
    @Column(name = "market_desc")
    private String marketDesc;

//    @Column(name = "upload_filename")
//    private String uploadFilename;//사용자가 업로드한 파일명
//
//    @Column(name = "store_filename")
//    private String storeFilename;//서버 내부에서 관리하는 파일명

    @OneToMany(mappedBy = "market")
    private List<Category> categoryList;

    public Market(MarketRequestDto marketRequestDto, UploadFile uploadFile){
        this.marketName = marketRequestDto.getMarketName();
        this.marketAddress = marketRequestDto.getMarketAddress();
        this.marketDesc = marketRequestDto.getMarketDesc();
//        this.uploadFilename = uploadFile.getUploadFilename();
//        this.storeFilename = uploadFile.getStoreFilename();
    }
}
