package smu.likelion.Traditional.Market.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "storeimages")
public class StoreImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_filename", nullable = false)
    private String storeFilename; //저장될 이름

    @Column(name = "image_url", length = 1000,nullable = false)
    private String storeImageUrl;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getStoreImageList().add(this);
    }

    @Builder
    public StoreImage(String storeFilename, String storeImageUrl, Store store) {
        this.storeFilename = storeFilename;
        this.storeImageUrl = storeImageUrl;
        this.store = store;
    }
}
