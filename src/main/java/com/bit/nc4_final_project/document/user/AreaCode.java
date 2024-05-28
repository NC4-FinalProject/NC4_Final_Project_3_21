package com.bit.nc4_final_project.document.user;

import jakarta.persistence.Embedded;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "area_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaCode {
    @Id
    private String id;
    private String code;
    private String name;

    @Setter
    @Embedded
    private List<SigunguCode> sigunguCodes;
}
