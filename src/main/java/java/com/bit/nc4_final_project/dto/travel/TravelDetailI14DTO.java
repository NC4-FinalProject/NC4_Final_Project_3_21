package java.com.bit.nc4_final_project.dto.travel;

import com.bit.nc4_final_project.dto.travel.TravelDetailDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelDetailI14DTO extends TravelDetailDTO {
    private String restdateculture;
    private String usefee;
    private String spendtime;
    private String accomcountculture;

}