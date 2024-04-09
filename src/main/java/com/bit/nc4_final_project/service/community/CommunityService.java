package com.bit.nc4_final_project.service.community;

import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.dto.community.CommunitySubscriberDTO;
import com.bit.nc4_final_project.dto.community.CommunityTagDTO;
import com.bit.nc4_final_project.entity.User;

import java.util.List;

public interface CommunityService {

    void post(CommunityDTO communityDTO);

    CommunityDTO findBySeq(int seq);

    CommunityDTO modify(CommunityDTO communityDTO, User user);

    void deleteById(int seq);

    CommunitySubscriberDTO subscribe(int seq, User user);

    void cancelSubscribe(int seq, User user);
}
