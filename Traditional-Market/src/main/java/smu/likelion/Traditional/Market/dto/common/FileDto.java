package smu.likelion.Traditional.Market.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileDto {

    private String uploadFilename;
    private String saveFilename;

    @Builder
    public FileDto(String uploadFilename, String saveFilename) {
        this.uploadFilename = uploadFilename;
        this.saveFilename = saveFilename;
    }
}
