package team.retum.jobis.thirdparty.paser;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.WriteFilePort;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;
import team.retum.jobis.global.exception.BadRequestException;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Component
@RequiredArgsConstructor
public class ExcelAdapter implements WriteFilePort {

    @Override
    public byte[] writeRecruitmentExcelFile(List<TeacherRecruitmentVO> recruitmentList) {
        List<String> attributes = listOf("상태", "기업명", "채용직군", "구분", "모집인원", "지원요청", "지원자", "모집시작일", "모집종료일");

        List<List<String>> dataList = recruitmentList.stream()
            .map(ph -> {
                String startDate;
                String endDate;

                if (ph.getStartDate() == null && ph.getEndDate() == null) {
                    startDate = endDate = "상시 모집";
                } else {
                    startDate = String.valueOf(ph.getStartDate());
                    endDate = String.valueOf(ph.getEndDate());
                }

                return List.of(
                    String.valueOf(ph.getRecruitStatus()),
                    ph.getCompanyName(),
                    ph.getJobCodes(),
                    String.valueOf(ph.getCompanyType()),
                    String.valueOf(ph.getTotalHiringCount()),
                    String.valueOf(ph.getRequestedApplicationCount()),
                    String.valueOf(ph.getApprovedApplicationCount()),
                    startDate,
                    endDate
                );
            })
            .toList();

        return createExcelSheet(attributes, dataList);
    }

    @Override
    public byte[] writeCompanyExcelFile(List<TeacherCompaniesVO> companyList) {
        List<String> attributes = listOf("기업명", "지역", "사업분야", "근로자수", "매출액(억)", "기업구분", "협약여부", "개인컨택", "최근 의뢰년도", "총 취업 학생수", "후기 등록");

        List<List<String>> dataList = companyList.stream()
            .map(ph -> {

                String personalContact;
                String convention;

                if (ph.getPersonalContact()) {
                    personalContact = "O";
                } else {
                    personalContact = "X";
                }

                if (ph.getConvention()) {
                    convention = "O";
                } else {
                    convention = "X";
                }

                return List.of(
                ph.getCompanyName(),
                    ph.getMainAddress(),
                    ph.getBusinessArea(),
                    String.valueOf(ph.getWorkersCount()),
                    String.valueOf(ph.getTake()),
                    String.valueOf(ph.getCompanyType()),
                    convention,
                    personalContact,
                    String.valueOf(ph.getRecentRecruitYear()),
                    String.valueOf(ph.getTotalAcceptanceCount()),
                    String.valueOf(ph.getReviewCount())
                );
            })
            .toList();
        return createExcelSheet(attributes, dataList);
    }

    private byte[] createExcelSheet(
        List<String> attributes,
        List<List<String>> datasList
    ) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row headerRow = sheet.createRow(0);
        insertDatasAtRow(headerRow, attributes, getHeaderCellStyle(workbook));

        for (int idx = 0; idx < datasList.size(); idx++) {
            List<String> datas = datasList.get(idx);
            Row row = sheet.createRow(idx + 1);
            insertDatasAtRow(row, datas, getDefaultCellStyle(workbook));
        }

        formatWorkSheet(sheet);

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            workbook.write(stream);
            return stream.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private void insertDatasAtRow(
        Row row,
        List<String> datas,
        CellStyle style
    ) {
        for (int i = 0; i < datas.size(); i++) {
            Cell cell = row.createCell(i);
            String data = datas.get(i);
            try {
                double cellValue = Double.parseDouble(data);
                cell.setCellValue(cellValue);
            } catch (NumberFormatException e) {
                cell.setCellValue(data);
            }
            cell.setCellStyle(style);
        }
    }

    private CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        setBorders(style);
        return style;
    }

    private CellStyle getDefaultCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    private void setBorders(CellStyle style) {
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    }

    private void formatWorkSheet(Sheet worksheet) {
        int lastCellNum = worksheet.getRow(0).getLastCellNum();

        worksheet.setAutoFilter(new CellRangeAddress(0, 0, 0, lastCellNum - 1));
        worksheet.createFreezePane(0, 1);

        for (int i = 0; i < lastCellNum; i++) {
            worksheet.autoSizeColumn(i);
            int width = worksheet.getColumnWidth(i);
            worksheet.setColumnWidth(i, width + 1500);
        }
    }

    public void setExcelContentDisposition(HttpServletResponse response, String fileName) {
        try {
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + ".xlsx\"");
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
