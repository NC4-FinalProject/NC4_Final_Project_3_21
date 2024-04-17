package java.com.bit.nc4_final_project.document.travel;

import com.bit.nc4_final_project.dto.travel.PetTravelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pet_travel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetTravel {
    @Id
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

    public PetTravelDTO toDTO() {
        return PetTravelDTO.builder()
                .id(this.id)
                .contentid(this.contentid)
                .petTursmInfo(this.petTursmInfo)
                .acmpyTypeCd(this.acmpyTypeCd)
                .acmpyNeedMtr(this.acmpyNeedMtr)
                .acmpyPsblCpam(this.acmpyPsblCpam)
                .etcAcmpyInfo(this.etcAcmpyInfo)
                .relaPosesFclty(this.relaPosesFclty)
                .relaFrnshPrdlst(this.relaFrnshPrdlst)
                .relaPurcPrdlst(this.relaPurcPrdlst)
                .relaAcdntRiskMtr(this.relaAcdntRiskMtr)
                .relaRntlPrdlst(this.relaRntlPrdlst)
                .build();
    }
}
