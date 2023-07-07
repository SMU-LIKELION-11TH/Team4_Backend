package smu.likelion.Traditional.Market.service;

import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.entity.UploadFile;

import java.io.IOException;

public interface ImageService {

    public String getFullPath(String filename);

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException;

    public void deleteFile(String filename);
}
