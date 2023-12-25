package Package;

import org.openqa.selenium.By;

import java.io.IOException;

public class LoginPage extends BaseClass {

    public static String wb_userName = "//input[@name='userName']";
    public static String wb_password = "//input[@name='password']";
    public static String wb_submit = "//input[@name='submit']";
    public static String wb_signOff = "//a[text()='SIGN-OFF']";
    public static String wb_message = "//input[@name='password']/following-sibling::span";
    public static void inputUserNameAndPassword(String sheetName,int rowNum) throws IOException {
        readLoginData(sheetName, rowNum);
        driver.findElement(By.xpath(wb_userName)).sendKeys(userName);
        driver.findElement(By.xpath(wb_password)).sendKeys(password);
        captureScreenshot();
    }

    //Method overloaded
    public static void inputUserNameAndPassword(String username,String inpassword) throws IOException {
        driver.findElement(By.xpath(wb_userName)).sendKeys(username);
        driver.findElement(By.xpath(wb_password)).sendKeys(inpassword);
        captureScreenshot();
    }

    public static void clickSubmit(){

        driver.findElement(By.xpath(wb_submit)).click();

    }

    public static void validateLogin() throws IOException {

            By signOff = By.xpath(wb_signOff);
            Boolean bool = isPresent(signOff);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "https://demo.guru99.com/test/newtours/login_sucess.php";
            if (bool = true && currentUrl.equalsIgnoreCase(expectedUrl)) {
                System.out.println("Login successful");
            } else {
                By message = By.xpath(wb_message);
                Boolean bool_message = isPresent(message);
                if (bool = true) {
                    System.out.println("Message displayed : " + driver.findElement(By.xpath(wb_message)).getText());
                }
                System.out.println("Login Unsuccessful");
            }
        captureScreenshot();
    }
}
