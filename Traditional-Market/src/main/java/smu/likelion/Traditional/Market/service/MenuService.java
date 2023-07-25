package smu.likelion.Traditional.Market.service;

import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.dto.menu.MenuRequestDto;
import smu.likelion.Traditional.Market.dto.menu.MenuReturnDto;

import java.util.List;

public interface MenuService {

    //점포생성
    //전체 점포조회
    //점포 상세 조회
    //점포 수정
    //점포 삭제
    public MenuReturnDto save(MultipartFile file, MenuRequestDto menuRequestDto);
    public List<MenuReturnDto> findByAll();
    public MenuReturnDto findById(Long id);
    public MenuReturnDto update(Long id, MenuRequestDto menuRequestDto,MultipartFile file);
    public void delete(Long id);
}
