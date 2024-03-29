package com.bit.nc4_final_project.service.community.impl;


import com.bit.nc4_final_project.dto.board.BoardFileDTO;
import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.entity.community.Community;
import com.bit.nc4_final_project.entity.community.CommunityTag;
import com.bit.nc4_final_project.repository.community.CommunityRepository;
import com.bit.nc4_final_project.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;


    @Override
    public void post(CommunityDTO communityDTO) {
        communityDTO.setRegDate(LocalDateTime.now().toString());

        Community community = communityDTO.toEntity();


        if (communityDTO.getTagFileDTOList() != null) {
            List<CommunityTag> tagList = communityDTO.getTagFileDTOList().stream()
                    .map(tagDTO -> {
                        CommunityTag tag = new CommunityTag();
                        tag.setContent(tagDTO.getContent());
                        tag.setCommunity(community); // CommunityTag 엔티티에 Community 엔티티를 설정합니다.
                        return tag;
                    })
                    .collect(Collectors.toList());

            // 변환된 태그 리스트를 Community 엔티티에 설정합니다.
            community.setCommunityTags(tagList); // 이 메소드는 Community 엔티티 내에 정의해야 합니다.
        }

        // Community 엔티티를 저장합니다.
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
    // 기타 메소드 생략
}


