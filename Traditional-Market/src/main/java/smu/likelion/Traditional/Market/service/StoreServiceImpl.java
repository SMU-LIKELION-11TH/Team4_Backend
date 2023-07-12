package smu.likelion.Traditional.Market.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.config.auth.AuthUtil;
import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.StoreImage;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.dto.common.FileDto;
import smu.likelion.Traditional.Market.dto.store.StoreRequestDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;
import smu.likelion.Traditional.Market.dto.user.UserRequestDto;
import smu.likelion.Traditional.Market.repository.CategoryRepository;
import smu.likelion.Traditional.Market.repository.StoreImageRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;
import smu.likelion.Traditional.Market.repository.UserRepository;
import smu.likelion.Traditional.Market.util.ExceptionUtil;
import smu.likelion.Traditional.Market.util.FileStore;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    @Autowired
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final StoreImageRepository storeImageRepository;
    private final UserRepository userRepository;
    private final FileStore fileStore;

    @Override
    public StoreReturnDto save(List<MultipartFile> multipartFiles,StoreRequestDto storeRequestDto) {
        try{
            //store 저장하는 로직
            Optional<Category> category = categoryRepository.findById(storeRequestDto.getCategoryId());
            Category category1 = category.get();
            Store store = storeRequestDto.toEntity(category1);
            storeRepository.save(store);
            store.updateUser(findUser(AuthUtil.getAuthUser()));
            //이미지 저장하는 로직
            saveImages(multipartFiles, store);
            storeRepository.save(store);

            /*
            int len = multipartFiles.size();
            for(int i = 0; i< len; i++){
                MultipartFile file = multipartFiles.get(i);
                int idx = file.getContentType().indexOf("/"); //getcontentType : image/png 이런식이기 때문에 /기준으로 그 뒤를 substring
                String type = file.getContentType().substring(idx+1);
                String serverfilename = UUID.randomUUID().toString()+"."+type; //서버(로컬)에 저장될 파일 이름

                Optional<Store> storeOptional = storeRepository.findByStoreName(storeRequestDto.getStoreName());
                Store store1 = storeOptional.orElseThrow(() -> new IllegalArgumentException("Store not found"));
                //local업로드 성공
                file.transferTo(new File(UPLOAD_PATH+serverfilename));
                //DB에 정보 저장하고 그거를 이제 불러와야하는데
                //StoreImagerequestDto를 만들어서 그거를 DB에 저장
                System.out.println(store1);
//                StoreImageRequestDto storeImageRequestDto = StoreImageRequestDto.builder()
//                        .storeFilename(serverfilename)
//                        .storeImageUrl(UPLOAD_PATH+serverfilename)
//                        .store(store1.get())
//                        .build();
//                StoreImageRequestDto storeImageRequestDto;
//                storeImageRequestDto = new StoreImageRequestDto(serverfilename,UPLOAD_PATH+serverfilename,store1);
//                //storeImageRequestDto.setStore(store1.get());
//                //storeImageRequestDto.setStoreImageUrl(UPLOAD_PATH+serverfilename);
//                //storeImageRequestDto.setStoreFilename(serverfilename);
//                //이게 논리적으로는 맞지만 로직적으로는 아닌듯?
//
//                storeImageRepository.save(storeImageRequestDto.toEntity());
                StoreImage storeImage = StoreImage.builder()
                        .storeFilename(serverfilename)
                        .storeImageUrl(UPLOAD_PATH+serverfilename)
                        .store(store1)
                        .build();
                storeImageRepository.save(storeImage);  //왜 됨..?

            }*/

//[image1 [asdasdas] image [asdasd]]
            return new StoreReturnDto(store);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StoreReturnDto> findAll(){
        try{
            Sort sort = Sort.by(Sort.Order.desc("averageStars"),
                                Sort.Order.desc("countReviews"));


            List<Store> storeList = storeRepository.findAll(sort);

            return storeList.stream().map(StoreReturnDto::new).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
    @Override
    public StoreReturnDto findById(Long id) {
        try{
            Optional<Store> storeData = storeRepository.findById(id);
            if (storeData.isPresent()) {
                Store store = storeData.get(); //optional에서 객체를 빼내올 수 있음.
                StoreReturnDto storeReturnDto = new StoreReturnDto(store);
                return storeReturnDto;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StoreReturnDto> findByCategoryId(Long id){
        try {
            Sort sort = Sort.by(Sort.Order.desc("averageStars"),
                    Sort.Order.desc("countReviews"));

            List<Store> storeList = storeRepository.findAllByCategoryId(id,sort);
            if(storeList != null){
                return storeList.stream().map(StoreReturnDto::new).collect(Collectors.toList());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //@PreAuthorize("hasRole('CEO') and (findUser(AuthUtil.getAuthUser()) == storeRepositoy.findById(#storeRequestDto.getId()).get().getUser())")
    @Override
    public StoreReturnDto update(Long id, StoreRequestDto storeRequestDto,List<MultipartFile> multipartFiles ) {
        try {
            Optional<Store> storeData = storeRepository.findById(id);
            System.out.println(storeData);
            //String fileSeparator = File.separator; //OS에 따라 "/"값이 다를 수 있기에 설정
            //final String UPLOAD_PATH = "D:"+fileSeparator+"likelionhackathon"+fileSeparator+"Traditional-Market"+fileSeparator+"src"+fileSeparator+"main"+fileSeparator+"resources"+fileSeparator+"images"+fileSeparator;
            if(storeData.isPresent()){
                Store store = storeData.get();

                Optional<Category> category = categoryRepository.findById(store.getCategory().getId());
                Category category1 = category.get();
//                store.setStoreName(storeRequestDto.getStoreName());
//                store.setStoreDesc(storeRequestDto.getStoreDesc());
//                store.setStoreTel(storeRequestDto.getStoreTel());
//                store.setStartTime(storeRequestDto.getStartTime());
//                store.setEndTime(storeRequestDto.getEndTime());
//                store.setRoadAddress(storeRequestDto.getRoadAddress());
//                store.setDetailAddress(storeRequestDto.getDetailAddress());
//                store.setCategory(category1);
//
                store.update(storeRequestDto.getStoreName(), storeRequestDto.getStoreDesc(), storeRequestDto.getStartTime(), storeRequestDto.getEndTime(), storeRequestDto.getStoreTel(), storeRequestDto.getRoadAddress(), store.getDetailAddress());

                List<StoreImage> storeImageList = storeImageRepository.findByStore_Id(store.getId());
                Iterator<StoreImage> it = storeImageList.iterator();
                while(it.hasNext()){
                    StoreImage storeImage = it.next();
                    Long imageId = storeImage.getId();
                    storeImageRepository.deleteById(imageId);
                    fileStore.deleteFile(storeImage.getStoreImageUrl());
                }
                saveImages(multipartFiles, store);

                storeRepository.save(store);
                store.updateImage(storeImageRepository.findByStore_Id(store.getId()));
                //store.setStoreImageList();

                /*
                for(int i = 0; i < multipartFiles.size(); i++){
                    MultipartFile file = multipartFiles.get(i);
                    int idx = file.getContentType().indexOf("/"); //getcontentType : image/png 이런식이기 때문에 /기준으로 그 뒤를 substring
                    String type = file.getContentType().substring(idx+1);
                    String serverfilename = UUID.randomUUID().toString()+"."+type; //서버(로컬)에 저장될 파일 이름


                    StoreImage storeImage = storeImageList.get(i);//1개 가져오기
                    file.transferTo(new File(UPLOAD_PATH+serverfilename));

                    storeImage.setStoreFilename(serverfilename);
                    storeImage.setStoreImageUrl(UPLOAD_PATH+serverfilename);
                    storeImage.setStore(store);

                    storeImageRepository.save(storeImage);

                }
                */

                return new StoreReturnDto(store);

            }else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void delete(Long id) {
        try {
            List<StoreImage> storeImageList = storeImageRepository.findByStore_Id(id);
            Iterator<StoreImage> it = storeImageList.iterator();
            while(it.hasNext()){
                fileStore.deleteFile(it.next().getStoreImageUrl());
            }
            storeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImages(List<MultipartFile> multipartFiles, Store store){
        if(multipartFiles != null){
            List<FileDto> fileDtoList = fileStore.storeFiles(multipartFiles);
            Iterator<FileDto> it = fileDtoList.iterator();
            while(it.hasNext()){
                FileDto fileDto = it.next();
                StoreImage storeImage = StoreImage.builder()
                        .storeFilename(fileDto.getUploadFilename())
                        .storeImageUrl(fileDto.getSaveFilename())
                        .store(store)
                        .build();
                storeImageRepository.save(storeImage);
            }
        }
    }
    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.id(email, User.class.getName()));
    }
}
