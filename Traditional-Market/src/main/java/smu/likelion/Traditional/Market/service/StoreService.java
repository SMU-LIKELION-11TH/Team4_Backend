package smu.likelion.Traditional.Market.service;

import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.dto.store.StoreRequestDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;

import java.util.List;

public interface StoreService {
    //메뉴생성
    //메뉴 점포조회
    //메뉴 상세 조회
    //메뉴 수정
    //메뉴 삭제
    public StoreReturnDto save(List<MultipartFile> multipartFiles, StoreRequestDto storeRequestDto);
    public List<StoreReturnDto> findAll();
    public StoreReturnDto findById(Long id);

    public List<StoreReturnDto> findByCategoryId(Long id);
    public StoreReturnDto update(Long id, StoreRequestDto storeRequestDto, List<MultipartFile> multipartFiles);
    public void delete(Long id);



}
