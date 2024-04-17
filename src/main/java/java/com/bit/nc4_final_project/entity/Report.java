package java.com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.converter.ReportTypeConverter;
import com.bit.nc4_final_project.dto.report.ReportDTO;
import com.bit.nc4_final_project.dto.report.ReportType;
import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_REPORT")
@SequenceGenerator(
        name = "reportSeqGenerator",
        sequenceName = "T_REPORT_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ReviewSeqGenerator"
    )
    @Column(name = "com_review_seq")
    private Integer seq;

    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime processDate;
    private Integer refId;
    @Convert(converter = ReportTypeConverter.class)
    private ReportType reportType;
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    public ReportDTO toDTO() {
        return ReportDTO.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .regDate(this.regDate.toString())
                .processDate(this.processDate.toString())
                .refId(this.refId)
                .reportType(this.reportType)
                .state(this.state)
                .build();
    }

}
