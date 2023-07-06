package smu.likelion.Traditional.Market.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Setter //언젠간 이 놈을 반드시 없앤다.
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_desc")
    private String storeDesc;

    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @Column(name = "store_time")
    private String storeTime;

    @Column(name = "store_tel")
    private String storeTel;

    @JsonManagedReference
    @OneToMany(mappedBy = "store")
    private List<Menu> menuList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "store")
    private List<StoreImage> storeImageList = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public void changeCategory(Category category){
        this.category = category;
        category.getStoreList().add(this);
    }

    @Builder
    public Store(String storeName, String storeDesc, String storeAddress, String storeTime, String storeTel, List<Menu> menuList, List<StoreImage> storeImageList, Category category) {
        this.storeName = storeName;
        this.storeDesc = storeDesc;
        this.storeAddress = storeAddress;
        this.storeTime = storeTime;
        this.storeTel = storeTel;
        this.menuList = menuList;
        this.storeImageList = storeImageList;
        this.category = category;
    }

}
