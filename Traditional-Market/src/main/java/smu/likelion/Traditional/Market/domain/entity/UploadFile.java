package smu.likelion.Traditional.Market.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFile {

    private String uploadFilename;

    private String storeFilename;

    public UploadFile(){
        uploadFilename = null;
        storeFilename = null;
    }
}
