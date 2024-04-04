package com.bit.nc4_final_project.entity.community;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommunitySubscriberId implements Serializable {
    private Integer user;
    private Integer community;
}
