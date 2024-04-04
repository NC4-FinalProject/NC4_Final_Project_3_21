package com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.entity.community.Community;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_COM_RECRUITMENT")
@SequenceGenerator(
        name = "RecruitmentSeqGenerator",
        sequenceName = "T_RECRUITMENT_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruitment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "RecruitmentSeqGenerator"
    )
    @Column(name = "com_recruitment_seq")
    private Integer seq;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "com_seq")
    private Community community;

    @Transient
    private String searchCondition;
    @Transient
    private String searchKeyword;

    public RecruitmentDTO toDTO() {
        return RecruitmentDTO.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .regDate(LocalDateTime.parse(this.regDate.toString()))
                .build();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
