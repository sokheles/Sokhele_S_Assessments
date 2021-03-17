package operation;

import Common_Functions.Common_Functions;
import javafx.scene.control.RadioButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;

public class TestCases {
    public static Common_Functions cf;
    static ReadObject object = new ReadObject();

    public static Properties returnObject() throws Exception {
        Properties allObjects = object.getObjectRepository();
        return allObjects;
    }

    public static void addUser(String strTestCaseName, WebDriver webdriver, String strFName, String strLName, String strUserName, String strPassword, String strCustomer, String strRole, String strEmail, String strCell, int iterator) throws Exception {
            webdriver.navigate().to(returnObject().getProperty("strURL"));
            webdriver.manage().window().maximize();
            String stepName = "Add_User_"+iterator;
            try {
                WebElement eleAddUser = webdriver.findElement(By.xpath(returnObject().getProperty("btnAddUser")));
                if (eleAddUser.isDisplayed() && eleAddUser.isEnabled()) {
                    eleAddUser.click();
                }

                WebElement txtFirstName = webdriver.findElement(By.name(returnObject().getProperty("txtFirstName")));
                if (txtFirstName.isDisplayed() && txtFirstName.isEnabled()) {
                    txtFirstName.sendKeys(strFName);
                    webdriver.findElement(By.name(returnObject().getProperty("txtLastName"))).sendKeys(strLName);
                    webdriver.findElement(By.name(returnObject().getProperty("txtUserName"))).sendKeys(strUserName);
                    webdriver.findElement(By.name(returnObject().getProperty("txtPassword"))).sendKeys(strPassword);
                    webdriver.findElement(By.name(returnObject().getProperty("txtEmail"))).sendKeys(strEmail);
                    webdriver.findElement(By.name(returnObject().getProperty("txtCellNumber"))).sendKeys(strCell);

                    Select role = new Select(webdriver.findElement(By.name("RoleId")));
                    role.selectByVisibleText(strRole);

                    if (strCustomer.equalsIgnoreCase("Company AAA")) {
                        webdriver.findElements(By.name(returnObject().getProperty("rdbtnCustomer"))).get(0).click();
                    }else if(strCustomer.equalsIgnoreCase("Company BBB")){
                        webdriver.findElements(By.name(returnObject().getProperty("rdbtnCustomer"))).get(1).click();
                    }
             cf.takeScreenshot(strTestCaseName,stepName+"FormCompleted", "Form has been completed fully", "Pass");
                    Thread.sleep(3000);
                    webdriver.findElement(By.xpath(returnObject().getProperty("btnSave"))).click();
                    Thread.sleep(5000);

                    String tblFName = webdriver.findElement(By.xpath(returnObject().getProperty("tblFName"))).getText();
                    String tblLName = webdriver.findElement(By.xpath(returnObject().getProperty("tblFName"))).getText();
                    String tblUserName = webdriver.findElement(By.xpath(returnObject().getProperty("tblFName"))).getText();
                    if(tblFName.equalsIgnoreCase(strFName)|| tblLName.equalsIgnoreCase(strLName) || tblUserName.equalsIgnoreCase(strUserName)){
                        Thread.sleep(2000);
                        cf.takeScreenshot(strTestCaseName,stepName+"UserAdded", "User was added successfully", "Pass");
                    }
                }
            } catch (Exception e) {
                cf.takeScreenshot(strTestCaseName, stepName, e.getMessage(), "Fail");
            }



    }
}
