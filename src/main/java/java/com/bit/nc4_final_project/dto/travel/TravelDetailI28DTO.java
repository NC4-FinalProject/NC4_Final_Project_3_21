package java.com.bit.nc4_final_project.dto.travel;

import com.bit.nc4_final_project.dto.travel.TravelDetailDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelDetailI28DTO extends TravelDetailDTO {
    private String openperiod;
    private String restdateleports;
    private String accomcountleports;
    private String usefeeleports;
    private String usetimeleports;
    private String expagerangeleports;
}