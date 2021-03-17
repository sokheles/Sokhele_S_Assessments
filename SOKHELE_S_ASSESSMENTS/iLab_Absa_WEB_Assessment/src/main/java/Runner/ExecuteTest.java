package Runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import ExcelFileReaderAndWriter.ReadAndWriteExcelFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static ExcelFileReaderAndWriter.ReadAndWriteExcelFile.createFile;
import static operation.TestCases.*;

public class ExecuteTest {

    static SimpleDateFormat sdf =  new SimpleDateFormat("dd.MM.yyyy___hh.mm.ss");
    static Date date= new Date();
    private static String resultsDir = sdf.format(date);

    public static String returnDir(){
        return resultsDir;
    }

   	public static void main(String args[]) throws Exception {

        ReadAndWriteExcelFile file = new ReadAndWriteExcelFile();
        WebDriver webdriver=null;
        String strTestCaseName="";

        File resultsDirName = new File(System.getProperty("user.dir") + "\\Results\\" + returnDir());
        resultsDirName.mkdir();

        String url = "jdbc:mysql://localhost:3306/prod_db";
        String username = "root";
        String password = "";

        System.out.println("Connecting database...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        createFile(returnDir());

        if (connection != null){
            System.out.println("Connected!");
        }else
        {
            System.out.println("Not Connected!!!");
        }

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from ilab_test_data");

        while(rs.next()) {

            strTestCaseName = rs.getString("Test_Case");
            String strBrowser = rs.getString("Browser");
            String strFName = rs.getString("First_Name");
            String strLName = rs.getString("Last_Name");
            String strUserName = rs.getString("User_Name");
            String strPassword = rs.getString("Password");
            String strCustomer = rs.getString("Customer");
            String strRole = rs.getString("Role");
            String strEmail = rs.getString("Email");
            String intCell = rs.getString("Cell_Number");
            int iterator = rs.getRow();
            try {

                    if (strBrowser.equalsIgnoreCase("IE") || strBrowser.equalsIgnoreCase("Internet Explorer")) {
                        File driverDir = new File(System.getProperty("user.dir") + "\\driver\\IEDriverServer.exe");
                        System.setProperty("webdriver.ie.driver", driverDir.getAbsolutePath());
                        webdriver = new InternetExplorerDriver();

                    } else if (strBrowser.equalsIgnoreCase("Chrome")) {
                        File driverDir = new File(System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
                        System.setProperty("webdriver.chrome.driver", driverDir.getAbsolutePath());
                        webdriver = new ChromeDriver();

                    } else {
                        cf.takeScreenshot(strTestCaseName, "Launch_Browser", "Could not launch browser: " + strBrowser, "Fail");
                    }

                    //Test Case 1
                    addUser(strTestCaseName,webdriver, strFName, strLName, strUserName, strPassword, strCustomer, strRole, strEmail, intCell, iterator);

            }catch (Exception e){
                cf.takeScreenshot(strTestCaseName, "Runner_Error", "Failed with exception: " + e.getMessage(), "Fail");
            }

            webdriver.quit();
        }

    }
}