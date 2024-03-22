package com.bit.nc4_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "area_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaCode {
    @Id
    private String id;
    private Integer code;
    private String name;
}
