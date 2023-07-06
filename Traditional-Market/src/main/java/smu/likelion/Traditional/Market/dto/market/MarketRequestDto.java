package smu.likelion.Traditional.Market.dto.market;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class MarketRequestDto {

    private String marketName;

    private String marketAddress;

    private String marketDesc;

    private MultipartFile marketImage;
}
