package java.com.bit.nc4_final_project.converter;

import com.bit.nc4_final_project.dto.report.ReportType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class ReportTypeConverter  implements AttributeConverter<ReportType, String> {

    @Override
    public String convertToDatabaseColumn(ReportType reportType) {
        return reportType.getLegacyCode();
    }

    @Override
    public ReportType convertToEntityAttribute(String legacyCode) {
        return ReportType.ofCode(legacyCode);
    }
}
