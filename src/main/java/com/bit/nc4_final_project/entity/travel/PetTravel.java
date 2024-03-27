package com.bit.nc4_final_project.entity.travel;

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
