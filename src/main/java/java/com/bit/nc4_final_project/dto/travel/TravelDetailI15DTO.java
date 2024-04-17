package java.com.bit.nc4_final_project.dto.travel;

import com.bit.nc4_final_project.dto.travel.TravelDetailDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelDetailI15DTO extends TravelDetailDTO {
    private String program;
    private String eventplace;
    private String eventstartdate;
    private String eventenddate;
    private String placeinfo;
    private String usetimefestival;
    private String agelimit;
    private String playtime;
    private String spendtimefestival;
    private String eventhomepage;
    private String bookingplace;

}