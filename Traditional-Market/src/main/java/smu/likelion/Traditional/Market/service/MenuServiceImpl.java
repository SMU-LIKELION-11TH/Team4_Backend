package smu.likelion.Traditional.Market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.Category;
import smu.likelion.Traditional.Market.domain.Menu;
import smu.likelion.Traditional.Market.domain.Store;
import smu.likelion.Traditional.Market.dto.menu.MenuRequestDto;
import smu.likelion.Traditional.Market.dto.menu.MenuReturnDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;
import smu.likelion.Traditional.Market.repository.MenuRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    @Override
    public MenuReturnDto save(MultipartFile file , MenuRequestDto menuRequestDto) {

        try{
            Optional<Store> store = storeRepository.findById(menuRequestDto.getStoreId());
            Store store1 = store.get();
            Menu menu = menuRequestDto.toEntity(store1);
            menuRepository.save(menu);

            return new MenuReturnDto(menu);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MenuReturnDto> findByAll() {
        return null;
    }

    @Override
    public MenuReturnDto findById(Long id) {
        return null;
    }

    @Override
    public MenuReturnDto update(Long id, MenuRequestDto menuRequestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            menuRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
