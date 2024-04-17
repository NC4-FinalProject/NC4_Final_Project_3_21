package com.bit.nc4_final_project.service.community.impl;


import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.dto.community.CommunitySubscriberDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.community.Community;
import com.bit.nc4_final_project.entity.community.CommunitySubscriber;
import com.bit.nc4_final_project.entity.community.CommunityTag;
import com.bit.nc4_final_project.repository.community.CommunityRepository;
import com.bit.nc4_final_project.repository.community.CommunitySubscriberRepository;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunitySubscriberRepository communitySubscriberRepository;

    @Override
    public void post(CommunityDTO communityDTO) {
        communityDTO.setRegDate(LocalDateTime.now().toString());
        User user = communityDTO.getUser().toEntity();
        log.info(">>> user Seq : " + user.getUserName());
        Community community = communityDTO.toEntity(user);

        log.info(">> tag dto size : " + communityDTO.getTags().size());
        if (communityDTO.getTags() != null) {
            List<CommunityTag> tagList = communityDTO.getTags().stream()
                    .map(tagDTO -> {
                        CommunityTag tag = new CommunityTag();
                        tag.setContent(tagDTO.getContent());
                        tag.setCommunity(community); // CommunityTag 엔티티에 Community 엔티티를 설정합니다.
                        return tag;
                    })
                    .collect(Collectors.toList());

            community.setCommunityTags(tagList); // 이 메소드는 Community 엔티티 내에 정의해야 합니다.
        }
        community.getCommunityTags().forEach(communityTag -> log.info(">> communityTag.getContent() : " + communityTag.getContent()));

        // Community 엔티티를 저장합니다.
        communityRepository.save(community);
        log.info("save complete");
    }

    @Override
    public CommunityDTO findBySeq(int seq) {
        log.info(">> seq " + seq);
        Community community = communityRepository.findBySeq(seq);
        log.info(">> community " + community.getSeq());
        log.info(">> community " + community.getName());
        log.info(">> community " + community.getMember());
        log.info(">> community " + community.getRegDate());
        log.info(">> community " + community.getDescription());
        log.info(">> community " + community.getUser());
        UserDTO userDTO = userRepository.findBySeq(1).toDTO();
        return community.toDTO(userDTO);
    }

    @Override
    public CommunityDTO modify(CommunityDTO communityDTO, User user) {
        Community community = communityDTO.toEntity(user);

        List<CommunityTag> communityTagList = communityDTO.getTags().stream().map(communityTagDTO -> communityTagDTO.toEntity(community)).toList();

        community.setCommunityTags(communityTagList);

        communityRepository.save(community);

        return communityDTO;
    }

    @Override
    public void deleteById(int seq) {

    }

    @Override
    public CommunitySubscriberDTO subscribe(int seq, User user) {
        CommunitySubscriber communitySubscriber = communitySubscriberRepository.save(CommunitySubscriber.builder()
                .community(Community.builder()
                        .seq(seq)
                        .build())
                .user(user)
                .build());

        return communitySubscriber.toDTO();
    }

    @Override
    public void cancelSubscribe(int seq, User user) {
        communitySubscriberRepository.delete(CommunitySubscriber.builder()
                .community(Community.builder()
                        .seq(seq)
                        .build())
                .user(user)
                .build());
    }


}


