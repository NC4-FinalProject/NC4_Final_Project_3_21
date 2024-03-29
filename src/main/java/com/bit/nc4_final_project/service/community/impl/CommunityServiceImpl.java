package com.bit.nc4_final_project.service.community.impl;


import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.dto.community.CommunityTagDTO;
import com.bit.nc4_final_project.entity.community.Community;
import com.bit.nc4_final_project.entity.community.CommunityTag;
import com.bit.nc4_final_project.repository.community.CommunityRepository;
import com.bit.nc4_final_project.service.community.CommunityService;
import jakarta.persistence.EntityNotFoundException;
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


        if (communityDTO.getTagDTOList() != null) {
            List<CommunityTag> tagList = communityDTO.getTagDTOList().stream()
                    .map(tagDTO -> {
                        CommunityTag tag = new CommunityTag();
                        tag.setContent(tagDTO.getContent());
                        tag.setCommunity(community); // CommunityTag 엔티티에 Community 엔티티를 설정합니다.
                        return tag;
                    })
                    .collect(Collectors.toList());

            community.setCommunityTags(tagList); // 이 메소드는 Community 엔티티 내에 정의해야 합니다.
        }
        community.getCommunityTags().forEach(communityTag -> System.out.println(communityTag.getContent()));

        // Community 엔티티를 저장합니다.
        communityRepository.save(community);
    }

    @Override
    public CommunityDTO findById(int seq) {
        Community community = communityRepository.findById(seq).orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + seq));


        return community.toDTO();
    }

    @Override
    public void modify(CommunityDTO communityDTO, List<CommunityTagDTO> uCommunityTagList) {

    }

    @Override
    public void deleteById(int seq) {

    }


}


