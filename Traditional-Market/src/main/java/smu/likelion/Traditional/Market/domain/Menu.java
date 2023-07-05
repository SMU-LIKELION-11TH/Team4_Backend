package smu.likelion.Traditional.Market.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
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

    @Lob
    @Column(name = "menu_Image", length = 1000, nullable = false) //여기 수정해야함
    private byte[] menuImage;
    private  String imageName;
    private  String imagetype;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getMenuList().add(this);
    }

}
