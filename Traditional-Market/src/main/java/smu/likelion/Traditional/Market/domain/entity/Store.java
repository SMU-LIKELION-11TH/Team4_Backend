package smu.likelion.Traditional.Market.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "start_time ")
    private String startTime;

    @Column(name = "end_time ")
    private String endTime;

    @Column(name = "road_address ")
    private String roadAddress;

    @Column(name = "detail_address ")
    private String detailAddress;

    @Column(name = "store_tel")
    private String storeTel;

    //@JsonManagedReference
    //@OneToMany(mappedBy = "store")
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuList = new ArrayList<>();

    //@JsonManagedReference
    //@OneToMany(mappedBy = "store")
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreImage> storeImageList = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void changeCategory(Category category){
        this.category = category;
        category.getStoreList().add(this);
    }

    public void changeUser(User user){
        this.user = user;
        user.getStoreList().add(this);
    }

    @Builder
    public Store(String storeName, String storeDesc, String startTime, String endTime,String storeTel,String roadAddress, String detailAddress, List<Menu> menuList, List<StoreImage> storeImageList, Category category) {
        this.storeName = storeName;
        this.storeDesc = storeDesc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.storeTel = storeTel;
        this.menuList = menuList;
        this.storeImageList = storeImageList;
        this.category = category;
    }


}
