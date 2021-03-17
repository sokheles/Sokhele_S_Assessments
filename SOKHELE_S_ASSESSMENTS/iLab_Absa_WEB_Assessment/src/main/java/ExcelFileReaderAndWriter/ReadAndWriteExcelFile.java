package ExcelFileReaderAndWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndWriteExcelFile {

	public Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {

		File file = new File(filePath + "\\" + fileName);

		FileInputStream inputStream = new FileInputStream(file);
		Workbook myWorkbook = null;

		myWorkbook = new XSSFWorkbook(inputStream);

		Sheet mySheet = myWorkbook.getSheet(sheetName);
		return mySheet;
	}

	public static String createFile(String dir) {

		try {

			Workbook wb = new XSSFWorkbook();

			Sheet sheet = wb.createSheet("Run_Results");

			CellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);

			CellStyle pass = wb.createCellStyle();
			pass.setFillPattern(CellStyle.SOLID_FOREGROUND);
			pass.setFillBackgroundColor(IndexedColors.RED.getIndex());

			Font fontStyle = wb.createFont();
			fontStyle.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(fontStyle);

			Row row = sheet.createRow(0);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue("TestCase");
			cell0.setCellStyle(style);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue("Step Name");
			cell1.setCellStyle(style);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue("Description");
			cell2.setCellStyle(style);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue("Outcome");
			cell3.setCellStyle(style);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue("Screenshot");
			cell4.setCellStyle(style);

			wb.getSheet("Run_Results").autoSizeColumn(0);
			wb.getSheet("Run_Results").autoSizeColumn(1);
			wb.getSheet("Run_Results").autoSizeColumn(2);
			wb.getSheet("Run_Results").autoSizeColumn(3);
			wb.getSheet("Run_Results").autoSizeColumn(4);

			FileOutputStream out = new FileOutputStream("Results\\"+  dir+"\\Results.xlsx");
			wb.write(out);

            System.out.println("Results file created.");
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
        return dir;
    }

}