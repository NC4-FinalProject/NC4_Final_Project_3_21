package java.com.bit.nc4_final_project.dto.report;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReportType {
    REVIEW("리뷰", "rev"),
    USER("사용자", "user"),
    COMMUNITY("커뮤니티", "com"),
    COMMENT("댓글", "cmt");

    private final String desc;
    private final String legacyCode;

    ReportType(String desc, String legacyCode) {
        this.desc = desc;
        this.legacyCode = legacyCode;
    }

    public static ReportType ofCode(String legacyCode) {
        return Arrays.stream(ReportType.values())
                .filter(v -> v.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 신고코드 입니다."));
    }
}
