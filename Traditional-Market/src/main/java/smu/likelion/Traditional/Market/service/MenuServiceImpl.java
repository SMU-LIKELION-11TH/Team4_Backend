package smu.likelion.Traditional.Market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.entity.Menu;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.dto.common.FileDto;
import smu.likelion.Traditional.Market.dto.menu.MenuRequestDto;
import smu.likelion.Traditional.Market.dto.menu.MenuReturnDto;
import smu.likelion.Traditional.Market.repository.MenuRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;
import smu.likelion.Traditional.Market.utils.FileStore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final FileStore fileStore;

    @Override
    public MenuReturnDto save(MultipartFile file , MenuRequestDto menuRequestDto) {
        try{
            Optional<Store> store = storeRepository.findById(menuRequestDto.getStoreId());
            if(store.isPresent()){
                Store store1 = store.get();

                FileDto fileDto = fileStore.storeFile(file);

                /*
                String fileSeparator = File.separator; //OS에 따라 "/"값이 다를 수 있기에 설정
                final String UPLOAD_PATH = "D:"+fileSeparator+"likelionhackathon"+fileSeparator+"Traditional-Market"+fileSeparator+"src"+fileSeparator+"main"+fileSeparator+"resources"+fileSeparator+"images"+fileSeparator;
                int idx = file.getContentType().indexOf("/"); //getcontentType : image/png 이런식이기 때문에 /기준으로 그 뒤를 substring
                String type = file.getContentType().substring(idx+1);
                String serverfilename = UUID.randomUUID().toString()+"."+type; //서버(로컬)에 저장될 파일 이름

                //local업로드 성공
                file.transferTo(new File(UPLOAD_PATH+serverfilename));

                //String path = File.separator+serverfilename+File.separator+"sample.jpg";
                 */

                //DB사진 정보 저장
                menuRequestDto.setImageName(fileDto.getUploadFilename());
                menuRequestDto.setImageUrl(fileDto.getSaveFilename());
                Menu menu = menuRequestDto.toEntity(store1);
                menuRepository.save(menu);

                return new MenuReturnDto(menu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MenuReturnDto> findByAll() {
        try{
            List<Menu> menuList = menuRepository.findAll();

            return menuList.stream().map(MenuReturnDto::new).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

    @Override
    public MenuReturnDto findById(Long id) {
        try{
            Optional<Menu> menuData = menuRepository.findById(id);
            if (menuData.isPresent()) {
                Menu menu = menuData.get(); //optional에서 객체를 빼내올 수 있음.
                MenuReturnDto menuReturnDto = new MenuReturnDto(menu);
                return menuReturnDto;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public MenuReturnDto update(Long id, MenuRequestDto menuRequestDto, MultipartFile file) {
        Optional<Menu> menuData = menuRepository.findById(id);

        /*
        String fileSeparator = File.separator; //OS에 따라 "/"값이 다를 수 있기에 설정
        final String UPLOAD_PATH = "D:"+fileSeparator+"likelionhackathon"+fileSeparator+"Traditional-Market"+fileSeparator+"src"+fileSeparator+"main"+fileSeparator+"resources"+fileSeparator+"images"+fileSeparator;
        int idx = file.getContentType().indexOf("/"); //getcontentType : image/png 이런식이기 때문에 /기준으로 그 뒤를 substring
        String type = file.getContentType().substring(idx+1);
        String serverfilename = UUID.randomUUID().toString()+"."+type; //서버(로컬)에 저장될 파일 이름

         */

        if(menuData.isPresent()){
            Menu menu = menuData.get();

            fileStore.deleteFile(menu.getImageUrl());
            FileDto fileDto = fileStore.storeFile(file);

            Optional<Store> store = storeRepository.findById(menu.getStore().getId());
            Store store1 = store.get();

            menu.setMenuName(menuRequestDto.getMenuName());
            menu.setMenuDesc(menuRequestDto.getMenuDesc());
            menu.setMenuPrice(menuRequestDto.getMenuPrice());
            menu.setImageName(fileDto.getUploadFilename());
            menu.setImageUrl(fileDto.getSaveFilename());
            menu.setStore(store1);

            /*
            try {
                file.transferTo(new File(UPLOAD_PATH+serverfilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
             */

            menuRepository.save(menu);
            return new MenuReturnDto(menu);

        }
        else {
            return null;
        }

    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if(menuOptional.isPresent()){
                Menu menu = menuOptional.get();
                fileStore.deleteFile(menu.getImageUrl());
                menuRepository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
