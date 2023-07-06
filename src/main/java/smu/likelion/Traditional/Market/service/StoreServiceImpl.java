package smu.likelion.Traditional.Market.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.Category;
import smu.likelion.Traditional.Market.domain.Store;
import smu.likelion.Traditional.Market.domain.StoreImage;
import smu.likelion.Traditional.Market.dto.store.StoreRequestDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;
import smu.likelion.Traditional.Market.dto.storeimage.StoreImageRequestDto;
import smu.likelion.Traditional.Market.repository.CategoryRepository;
import smu.likelion.Traditional.Market.repository.StoreImageRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    @Autowired
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final StoreImageRepository storeImageRepository;

    @Override
    public StoreReturnDto save(List<MultipartFile> multipartFiles,StoreRequestDto storeRequestDto) {
        try{
            String fileSeparator = File.separator; //OS에 따라 "/"값이 다를 수 있기에 설정
            final String UPLOAD_PATH = "D:"+fileSeparator+"likelionhackathon"+fileSeparator+"Traditional-Market"+fileSeparator+"src"+fileSeparator+"main"+fileSeparator+"resources"+fileSeparator+"images"+fileSeparator;
            Optional<Category> category = categoryRepository.findById(storeRequestDto.getCategoryId());
            Category category1 = category.get();
            Store store = storeRequestDto.toEntity(category1);
            System.out.println(category1);
            System.out.println(store);
            storeRepository.save(store);

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
            }

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
            List<Store> storeList = storeRepository.findAll();

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
    public StoreReturnDto update(Long id, StoreRequestDto storeRequestDto) {
        try {
            Optional<Store> storeData = storeRepository.findById(id);
            System.out.println(storeData);
            if(storeData.isPresent()){
                Store store = storeData.get();

                Optional<Category> category = categoryRepository.findById(store.getCategory().getId());
                Category category1 = category.get();
                System.out.println(category1);
                System.out.println(storeRequestDto.getStoreAddress());
                System.out.println(storeRequestDto.getStoreName());

//                Store Editstore = store.builder()
//                        .storeName("새로운 store이름입니다.")
//                        .storeDesc(storeRequestDto.getStoreDesc())
//                        .storeAddress("새로운 address입니다.")
//                        .storeTime(storeRequestDto.getStoreTime())
//                        .storeTel(storeRequestDto.getStoreTel())
//                        .storeImageList(storeRequestDto.getStoreImageList())
//                        .menuList(storeRequestDto.getMenuList()) //얘 수정을 해야함 이유 : 아까 민수형이 말했 듯이 store전체를 가져오는게 아니라 일부만 가져오는 것임.
//                        .category(category1)
//                        .build();
//                store객체를 꺼내오고 거기에 데
                store.setStoreName("새로운 store이름입니다.");
                store.setStoreDesc(storeRequestDto.getStoreDesc());
                store.setStoreTel(storeRequestDto.getStoreTel());
                store.setStoreTime(storeRequestDto.getStoreTime());
                store.setStoreAddress("새로운 store주소 입니다.");
                store.setCategory(category1);


                storeRepository.save(store);
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
            storeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
