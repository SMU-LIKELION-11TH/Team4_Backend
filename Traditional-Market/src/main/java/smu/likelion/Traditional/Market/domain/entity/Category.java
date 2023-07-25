package smu.likelion.Traditional.Market.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import smu.likelion.Traditional.Market.dto.category.CategoryRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    @Column(name="category_name")
    private String categoryName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToMany(mappedBy = "category")
    private List<Store> storeList = new ArrayList<>();

    @Builder
    public Category(CategoryRequestDto categoryRequestDto, Market market){
        this.categoryName = categoryRequestDto.getCategoryName();
        this.market = market;
    }
}
