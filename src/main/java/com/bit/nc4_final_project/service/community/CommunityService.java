package com.bit.nc4_final_project.service.community;

import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.dto.community.CommunityTagDTO;

import java.util.List;

public interface CommunityService {

    void post(CommunityDTO communityDTO);

    CommunityDTO findById(int seq);

    void modify(CommunityDTO communityDTO, List<CommunityTagDTO> uCommunityTagList);

    void deleteById(int seq);
}
