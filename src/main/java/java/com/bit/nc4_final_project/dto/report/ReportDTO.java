package java.com.bit.nc4_final_project.dto.report;

import com.bit.nc4_final_project.dto.report.ReportType;
import com.bit.nc4_final_project.entity.Report;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReportDTO {
    private Integer seq;
    private String title;
    private String content;
    private String regDate;
    private String processDate;
    private Integer refId;
    private ReportType reportType;
    private Boolean state;

    public Report toEntity() {
        return Report.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .regDate(LocalDateTime.parse(this.regDate))
                .processDate(LocalDateTime.parse(this.processDate))
                .refId(this.refId)
                .reportType(this.reportType)
                .state(this.state)
                .build();
    }
}
