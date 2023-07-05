package smu.likelion.Traditional.Market.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import smu.likelion.Traditional.Market.domain.Category;
import smu.likelion.Traditional.Market.domain.Store;
import smu.likelion.Traditional.Market.dto.store.StoreRequestDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;
import smu.likelion.Traditional.Market.repository.CategoryRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    @Autowired
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public StoreReturnDto save(StoreRequestDto storeRequestDto) {
        try{
            System.out.println(storeRequestDto.getCategoryId().getClass().getName());
            Optional<Category> category = categoryRepository.findById(storeRequestDto.getCategoryId());
            System.out.println(category);
            System.out.println(storeRequestDto);
            Category category1 = category.get();
            System.out.println(category1);
            Store store = storeRequestDto.toEntity(category1);
            storeRepository.save(store);

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
                sout
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
