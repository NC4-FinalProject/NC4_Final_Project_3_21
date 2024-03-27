package com.bit.nc4_final_project.service.community.impl;


import com.bit.nc4_final_project.dto.board.BoardFileDTO;
import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.entity.community.Community;
import com.bit.nc4_final_project.repository.community.CommunityRepository;
import com.bit.nc4_final_project.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;


    @Override
    public void post(CommunityDTO communityDTO) {
        communityDTO.setRegDate(LocalDateTime.now().toString());

        Community community = communityDTO.toEntity();

        communityRepository.save(community);
    }

    @Override
    public CommunityDTO findById(int seq) {
        return null;
    }

    @Override
    public void modify(CommunityDTO communityDTO, List<BoardFileDTO> uBoardFileList) {

    }

    @Override
    public void deleteById(int seq) {

    }
}
