package Package;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class UserRegistrationPage extends BaseClass {
    public static String wb_registerLink = "//a[text()='REGISTER']";
    public static String wb_firstName = "//input[@name='firstName']";
    public static String wb_lastName = "//input[@name='lastName']";
    public static String wb_userName = "//input[@id='email']";
    public static String wb_password = "//input[@name='password']";
    public static String wb_confirmPassword = "//input[@name='confirmPassword']";
    public static String wb_submit = "//input[@name='submit']";
    public static String wb_registrationConfirmText = "(//td[@valign='top'])[3]/descendant::a[5]/..";
    public static void navigateToRegisterPage() throws IOException {

        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_registerLink))).click();
        captureScreenshot();
    }

    public static void inputPersonalDetails(String sheetName,int rowNum) throws IOException {

        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_firstName)));
        readPersonalDataFromExcel(sheetName,rowNum);
        driver.findElement(By.xpath(wb_firstName)).sendKeys(firstName);
        driver.findElement(By.xpath(wb_lastName)).sendKeys(lastName);
        driver.findElement(By.xpath(wb_userName)).sendKeys(userName);
        driver.findElement(By.xpath(wb_password)).sendKeys(password);
        driver.findElement(By.xpath(wb_confirmPassword)).sendKeys(password);

        captureScreenshot();
    }



    public static void submitDetails() throws IOException {

        driver.findElement(By.xpath(wb_submit)).click();
    }
    public static void validateRegistration() throws IOException, InvalidFormatException {

        wait = new WebDriverWait(driver,10);
        WebElement content = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(wb_registrationConfirmText))));
        System.out.println("Confirmation text="+content.getText());

        if((content.getText()).contains("Thank you for registering. You may now sign-in")){
            System.out.println("Registration successful");
            writeExcel(userName,password);
        }else{
            System.out.println("Registration Unsuccessful");
        }

        captureScreenshot();

    }


}
