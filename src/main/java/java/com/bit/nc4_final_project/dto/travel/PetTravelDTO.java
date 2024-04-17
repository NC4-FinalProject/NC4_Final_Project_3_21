package java.com.bit.nc4_final_project.dto.travel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PetTravelDTO {
    private String id;
    private String contentid;
    private String petTursmInfo;
    private String acmpyTypeCd;
    private String acmpyNeedMtr;
    private String acmpyPsblCpam;
    private String etcAcmpyInfo;
    private String relaPosesFclty;
    private String relaFrnshPrdlst;
    private String relaPurcPrdlst;
    private String relaAcdntRiskMtr;
    private String relaRntlPrdlst;
}
