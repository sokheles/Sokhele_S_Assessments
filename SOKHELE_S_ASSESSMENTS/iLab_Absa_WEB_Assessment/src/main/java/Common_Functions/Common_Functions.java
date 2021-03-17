package Common_Functions;
import ExcelFileReaderAndWriter.ReadAndWriteExcelFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Runner.ExecuteTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Common_Functions {

public static String directory = ExecuteTest.returnDir();

    public static void takeScreenshot(String testCaseName,String stepName,String Description,String results) throws Exception {
        Rectangle allScreenBounds = getAllScreenBounds();
        Robot robot;

               try {
            robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);
            ImageIO.write(screenShot, "jpg", new File(System.getProperty("user.dir")+"\\Results\\"+directory+"\\"+stepName+".jpg"));
        } catch (AWTException e) {
            System.err.println("Something went wrong starting the robot");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Something went wrong writing files");
            e.printStackTrace();
        }

        addResults(testCaseName,stepName,Description, results);
    }

    private static Rectangle getAllScreenBounds() {
        Rectangle allScreenBounds = new Rectangle();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        int farx = 0;
        int fary = 0;
        for (GraphicsDevice screen : screens) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();

            if (allScreenBounds.x > screenBounds.x) {
                allScreenBounds.x = screenBounds.x;
            }
            if (allScreenBounds.y > screenBounds.y) {
                allScreenBounds.y = screenBounds.y;
            }

            if (farx < (screenBounds.x + screenBounds.width)) {
                farx = screenBounds.x + screenBounds.width;
            }
            if (fary < (screenBounds.y + screenBounds.height)) {
                fary = screenBounds.y + screenBounds.height;
            }
            allScreenBounds.width = farx - allScreenBounds.x;
            allScreenBounds.height = fary - allScreenBounds.y;
        }
        return allScreenBounds;
    }

    public static void addResults(String testCaseName,String stepName,String Description, String status) throws Exception {

        ReadAndWriteExcelFile file = new ReadAndWriteExcelFile();
        String resultsFile = System.getProperty("user.dir")+"\\Results\\"+directory;
        Sheet resultsSheet = file.readExcel(resultsFile,"Results.xlsx" , "Run_Results");

        int rowCount = resultsSheet.getLastRowNum();

        try {
            FileInputStream input = new FileInputStream(resultsFile+"\\Results.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet sheet = workbook.getSheetAt(0);
            CreationHelper createHelper = workbook.getCreationHelper();
            Cell cell = null;

            XSSFRow sheetrow = sheet.getRow(rowCount+1);

            CellStyle styles = workbook.createCellStyle();
            styles.setBorderBottom(CellStyle.BORDER_THIN);
            styles.setBorderTop(CellStyle.BORDER_THIN);
            styles.setBorderRight(CellStyle.BORDER_THIN);
            styles.setBorderLeft(CellStyle.BORDER_THIN);

            XSSFCellStyle pass = workbook.createCellStyle();
            pass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            pass.setBorderBottom(CellStyle.BORDER_THIN);
            pass.setBorderTop(CellStyle.BORDER_THIN);
            pass.setBorderRight(CellStyle.BORDER_THIN);
            pass.setBorderLeft(CellStyle.BORDER_THIN);

            if(sheetrow == null) {
                sheetrow = sheet.createRow(rowCount + 1);

                cell = sheetrow.createCell(0);
                cell.setCellValue(testCaseName);
                cell.setCellStyle(styles);

                cell = sheetrow.createCell(1);
                cell.setCellValue(stepName);
                cell.setCellStyle(styles);

                cell = sheetrow.createCell(2);
                cell.setCellValue(Description);
                cell.setCellStyle(styles);

                cell = sheetrow.createCell(3);
                if(status.equalsIgnoreCase("PASS")) {
                    cell.setCellValue("PASS");
                    pass.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
                    cell.setCellStyle(pass);

                }else if(status.equalsIgnoreCase("FAIL")){
                    pass.setFillForegroundColor(IndexedColors.RED.getIndex());
                    cell.setCellValue("FAIL");
                    cell.setCellStyle(pass);

                }else{
                    cell.setCellValue("UNKNOWN");
                    pass.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                    cell.setCellStyle(pass);
                }

                CellStyle hlink_style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font hlink_font = workbook.createFont();
                hlink_font.setUnderline(org.apache.poi.ss.usermodel.Font.U_SINGLE);
                hlink_font.setColor(IndexedColors.BLUE.getIndex());
                hlink_style.setFont(hlink_font);
                hlink_style.setBorderBottom(CellStyle.BORDER_THIN);
                hlink_style.setBorderTop(CellStyle.BORDER_THIN);
                hlink_style.setBorderRight(CellStyle.BORDER_THIN);
                hlink_style.setBorderLeft(CellStyle.BORDER_THIN);

                cell = sheetrow.createCell(4);
                cell.setCellValue("ScreenShot");
                cell.setCellStyle(styles);
                Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_FILE);
                link.setAddress(stepName + ".jpg");
                cell.setHyperlink(link);
                cell.setCellStyle(hlink_style);

                input.close();
                FileOutputStream outFile = new FileOutputStream(new File(resultsFile+"\\Results.xlsx"));
                workbook.write(outFile);
                outFile.close();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
