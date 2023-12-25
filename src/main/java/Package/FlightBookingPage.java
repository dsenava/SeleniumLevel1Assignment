package Package;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class FlightBookingPage extends BaseClass {

    public static String wb_flights = "//a[text()='Flights']";
    public static String wb_TripTypeRound = "//input[@value='roundtrip']";
    public static String wb_passengerDropDown = "//select[@name='passCount']";
    public static String wb_departFromDropDown = "//select[@name='fromPort']";
    public static String wb_departureMonthDropDown = "//select[@name='fromMonth']";
    public static String wb_departureDayDropDown = "//select[@name='fromDay']";
    public static String wb_arrivalDropDown = "//select[@name='toPort']";
    public static String wb_arrivalMonthDropDown = "//select[@name='toMonth']";
    public static String wb_arrivalDayDropDown = "//select[@name='toDay']";
    public static String wb_serviceClassFirst = "//input[@value='First']";
    public static String wb_airlinePreferenceDropDown = "//select[@name='airline']";
    public static String wb_continueButton = "//input[@name='findFlights']";
    public static String wb_flightSeatStatus = "//img[@src='images/home.gif']/ancestor::tr[2]/descendant::td[3]/p/font";
    public static String wb_closeAd = "//div[@id='dismiss-button']/div/span";
    public static String wb_closeAdSymbol = "//div[@id='ad_position_box']/descendant::div[@id='dismiss-button']/div";
    public static Select sel;
    public static String frame1 = "//iframe[@title='3rd party ad content']";
    public static String frame2 = "//iframe[@id='ad_iframe']";
    public static String random = "//div[@id='ad_position_box']";
    public static void navigateToFlightBookingPage() throws IOException {
        driver.findElement(By.xpath(wb_flights)).click();
        captureScreenshot();
    }

    public static void inputFlightDetails() throws IOException {

        wait = new WebDriverWait(driver,10);
        //click on Trip Type radio button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_TripTypeRound))).click();

        //select number of passengers
        WebElement passengerCount = driver.findElement(By.xpath(wb_passengerDropDown));
        sel = new Select(passengerCount);
        sel.selectByValue("2");

        //Choose Departure From
        WebElement departFrom = driver.findElement(By.xpath(wb_departFromDropDown));
        sel = new Select(departFrom);
        sel.selectByVisibleText("London");

        //choose Departure Month
        WebElement departOnMonth = driver.findElement(By.xpath(wb_departureMonthDropDown));
        sel = new Select(departOnMonth);
        sel.selectByIndex(6);

        //choose Departure Day
        WebElement departOnDay = driver.findElement(By.xpath(wb_departureDayDropDown));
        sel = new Select(departOnDay);
        sel.selectByValue("10");

        //choose arrival
        WebElement arrivalTo = driver.findElement(By.xpath(wb_arrivalDropDown));
        sel = new Select(arrivalTo);
        sel.selectByValue("Paris");

        //choose arrival month
        WebElement arrivalMonth = driver.findElement(By.xpath(wb_arrivalMonthDropDown));
        sel = new Select(arrivalMonth);
        sel.selectByIndex(6);

        //choose arrival day
        WebElement arrivalDay = driver.findElement(By.xpath(wb_arrivalDayDropDown));
        sel = new Select(arrivalDay);
        sel.selectByVisibleText("15");

        //choose service class
        driver.findElement(By.xpath(wb_serviceClassFirst)).click();

        //choose airline
        WebElement airline = driver.findElement(By.xpath(wb_airlinePreferenceDropDown));
        sel = new Select(airline);
        sel.selectByVisibleText("Unified Airlines");

        captureScreenshot();
    }

    public static void clickOnContinue(){
        driver.findElement(By.xpath(wb_continueButton)).click();
    }

    public static void closeAds() throws IOException {

        captureScreenshot();
        wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(frame1)));
        WebElement wb_frame1 = driver.findElement(By.xpath(frame1));
        driver.switchTo().frame(wb_frame1);

        closeSymbol();
        closeButton();
    }
    public static void closeButton(){
            try {
                System.out.println("Inside second frame");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(frame2)));
                WebElement wb_frame2 = driver.findElement(By.xpath(frame2));
                driver.switchTo().frame(wb_frame2);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wb_closeAd))).click();
                driver.switchTo().parentFrame();
                driver.switchTo().defaultContent();
            }catch (Exception e) {
                System.out.println("closeButton Element Not Found");
            }
    }
    public static void closeSymbol(){
        try {
            System.out.println("NOT Inside second frame");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wb_closeAdSymbol))).click();
            driver.switchTo().parentFrame();
        }catch(Exception e){
            System.out.println("closeSymbol Element Not found");
        }
    }

    public static void validateFlightBooking(String expectedMessage) throws IOException {
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_flightSeatStatus)));
        String messageDisplayed = driver.findElement(By.xpath(wb_flightSeatStatus)).getText();
        if(messageDisplayed.equalsIgnoreCase(expectedMessage)){
            System.out.println(messageDisplayed);
        }else{
            System.out.println("Flights could not booked as "+messageDisplayed);
        }
        captureScreenshot();
    }
}
