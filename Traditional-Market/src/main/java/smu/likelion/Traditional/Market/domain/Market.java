package smu.likelion.Traditional.Market.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "market_image")
    private String marketImage;

    @OneToMany(mappedBy = "market")
    private List<Category> categoryList;
}
