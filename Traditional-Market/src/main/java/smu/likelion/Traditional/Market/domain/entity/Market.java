package smu.likelion.Traditional.Market.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "market")
    private List<Category> categoryList = new ArrayList<>();

    public Market(MarketRequestDto marketRequestDto){
        this.marketName = marketRequestDto.getMarketName();
        this.marketAddress = marketRequestDto.getMarketAddress();
        this.marketDesc = marketRequestDto.getMarketDesc();
    }
}
