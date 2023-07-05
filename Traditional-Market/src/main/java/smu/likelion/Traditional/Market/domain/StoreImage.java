package smu.likelion.Traditional.Market.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection
    @Column(name = "store_image_List", nullable = false) //여기 수정해야함
    private List<String> storeImageNameList;

    @ElementCollection
    @Column(name = "image_name", length = 1000,nullable = false)
    private List<String> storeImageUrlList;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getStoreImageList().add(this);
    }

    @Builder
    public StoreImage(String uploadFilename, String storeFilename,List<String> storeImageNameList, List<String> storeImageUrlList, Store store) {
        this.uploadFilename = uploadFilename;
        this.storeFilename = storeFilename;
        this.storeImageNameList = storeImageNameList;
        this.storeImageUrlList = storeImageUrlList;
        this.store = store;
    }
}
