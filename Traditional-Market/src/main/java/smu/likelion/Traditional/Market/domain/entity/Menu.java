package smu.likelion.Traditional.Market.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private Integer menuPrice;

    @Column(name = "menu_desc")
    private String menuDesc;

    @Column(name = "image_name",nullable = false)
    private String imageName;

    @Column(name = "image_url", length = 1000, nullable = false) //여기 수정해야함
    private  String imageUrl;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getMenuList().add(this);
    }

}
