package java.com.bit.nc4_final_project.document.travel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "travel_detail")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelDetail {
    private String homepage;
    private String overview;
    private String opendate;
    private String restdate;
    private String useseason;
    private String usetime;
    private String accomcount;
    private String expagerange;
    private String restdateculture;
    private String usefee;
    private String spendtime;
    private String accomcountculture;
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
    private String openperiod;
    private String restdateleports;
    private String accomcountleports;
    private String usefeeleports;
    private String usetimeleports;
    private String expagerangeleports;
}
