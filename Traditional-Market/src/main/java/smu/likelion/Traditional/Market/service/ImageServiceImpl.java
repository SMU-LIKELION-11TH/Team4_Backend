package smu.likelion.Traditional.Market.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.entity.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    //프로젝트 루트 경로 불러오기
    private final String rootPath = System.getProperty("user.dir");

    //프로젝트 루트 경로의 images 디렉토리
    private final String fileDir = rootPath + "/images/";

    @Override
    public String getFullPath(String filename) {
        if(filename == null){
            return null;
        }
        return fileDir + filename;
    }

    @Override
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return new UploadFile();
        }

        //original name에서는 확장자만 가져옴. uuid로 새로운 이름을 붙여줌
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        //파일 경로 + storeFilename에 저장
        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(originalFilename, storeFilename);
    }

    public void deleteFile(String filename){
        String path = getFullPath(filename);
        if(path != null){
            File file = new File(getFullPath(filename));
            file.delete();
        }
    }

    //확장자 추출
    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
