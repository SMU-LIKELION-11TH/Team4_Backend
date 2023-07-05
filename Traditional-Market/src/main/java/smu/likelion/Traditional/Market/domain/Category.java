package smu.likelion.Traditional.Market.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category_name",nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Store> storeList = new ArrayList<>();

    @Builder
    public Category(String categoryName, List<Store> storeList) {
        this.categoryName = categoryName;
        this.storeList = storeList;
    }
}
