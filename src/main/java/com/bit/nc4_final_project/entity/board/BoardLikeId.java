package com.bit.nc4_final_project.entity.board;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BoardLikeId implements Serializable {
    private Integer board;
    private Integer user;
}
