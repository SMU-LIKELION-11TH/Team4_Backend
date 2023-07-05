package smu.likelion.Traditional.Market.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "storeimages")
public class StoreImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "upload_filename", nullable = false)
    private String uploadFilename;

    @Column(name = "store_filename", nullable = false)
    private String storeFilename;

    @Lob
    @Column(name = "menu_Image", length = 1000, nullable = false) //여기 수정해야함
    private byte[] storeImage;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getStoreImageList().add(this);
    }

    @Builder
    public StoreImage(String uploadFilename, String storeFilename, byte[] storeImage, Store store) {
        this.uploadFilename = uploadFilename;
        this.storeFilename = storeFilename;
        this.storeImage = storeImage;
        this.store = store;
    }
}
