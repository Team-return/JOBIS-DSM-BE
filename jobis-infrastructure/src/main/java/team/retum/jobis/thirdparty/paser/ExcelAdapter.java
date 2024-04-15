package team.retum.jobis.thirdparty.paser;

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
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Component
@RequiredArgsConstructor
public class ExcelAdapter implements WriteFilePort {

    @Override
    public byte[] writeRecruitmentExcelFile(List<TeacherRecruitmentVO> recruitmentList) {

        List<String> attributes = listOf("상태", "기업명", "채용직군", "구분", "모집인원", "지원요청", "지원자", "모집시작일", "모집종료일");

        List<List<String>> dataList = recruitmentList.stream()
            .map(ph -> List.of(
                String.valueOf(ph.getRecruitStatus()),
                ph.getCompanyName(),
                ph.getJobCodes(),
                String.valueOf(ph.getCompanyType()),
                String.valueOf(ph.getTotalHiringCount()),
                String.valueOf(ph.getRequestedApplicationCount()),
                String.valueOf(ph.getApprovedApplicationCount())
            ))
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
}
