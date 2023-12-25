package Package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static String url;
    public static String firstName;
    public static String lastName;
    public static String userName;
    public static String password;
    public static WebDriverWait wait;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static int rowid, cellid, count = 0;
    public static int totalRowsForRegistration = 0;
    public static String filePath = "C:\\Users\\VA338RM\\GitImports\\SeleniumLevel1Assignment\\src\\test\\resources\\TestData.xlsx";
    public static int userCount = 1;
    public static void readExcelUtility() throws IOException {

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        wb = new XSSFWorkbook(fis);

    }

    public static void readPersonalDataFromExcel(String sheetName, int rowNum) {

        System.out.println("Sheet name is " + sheetName);
        System.out.println("Row num is " + rowNum);

        //if(sheetName.equalsIgnoreCase("RegistrationData")) {
        if (sheetName.equalsIgnoreCase("RegistrationData")) {
            readRegistrationData(sheetName, rowNum);
        } else if (sheetName.equalsIgnoreCase("LoginData")) {
            readLoginData(sheetName, rowNum);
        } else {
            System.out.println("Sheet Not Found");
        }

    }

    public static void readRegistrationData(String sheetName, int rowNum) {
        sheet = wb.getSheet(sheetName);
        totalRowsForRegistration = sheet.getPhysicalNumberOfRows() - 1; //Excluding Header row
        System.out.println("Row count in " + sheetName + " = " + totalRowsForRegistration);
        int cellCount = sheet.getRow(rowNum).getPhysicalNumberOfCells();
        System.out.println("cell count in " + sheetName + " = " + cellCount);

        firstName = sheet.getRow(rowNum).getCell(cellCount - 4).getStringCellValue();
        lastName = sheet.getRow(rowNum).getCell(cellCount - 3).getStringCellValue();
        userName = sheet.getRow(rowNum).getCell(cellCount - 2).getStringCellValue();
        password = sheet.getRow(rowNum).getCell(cellCount - 1).getStringCellValue();

    }

    public static void readLoginData(String sheetName, int rowNum) {
        System.out.println("Sheet name is " + sheetName);
        System.out.println("Row num is " + rowNum);
        System.out.println("Total No sheets = " + wb.getNumberOfSheets());
        sheet = wb.getSheet(sheetName);
        int rowC = sheet.getPhysicalNumberOfRows();
        System.out.println("roq count in " + sheetName + " = " + rowC);
        // int cellCount = sheet.getRow(rowNum).getPhysicalNumberOfCells();
        int ccellCount = sheet.getRow(rowNum).getPhysicalNumberOfCells();
        System.out.println("cell count in " + sheetName + " = " + ccellCount);

        userName = sheet.getRow(rowNum).getCell(0).getStringCellValue();
        password = sheet.getRow(rowNum).getCell(1).getStringCellValue();
    }

    public static void readUrlFromExcel() {
        sheet = wb.getSheet("URL");
        url = sheet.getRow(1).getCell(0).getStringCellValue();
    }

    public static void writeExcel(String userName, String password) throws IOException, InvalidFormatException {
        File wFile = new File(filePath);
        InputStream is = new FileInputStream(wFile);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        int noOfSheets = wb.getNumberOfSheets();
        System.out.println("No. of sheets= " + noOfSheets);
        System.out.println("usname=" + userName);
        System.out.println("pwd=" + password);
        for (int i = 0; i < noOfSheets; i++) {
            if (wb.getSheetName(i).equals("LoginData")) {
                count++;
                System.out.println("Count = "+count);
                overrideDataToExcel(userName, password, wb);
                break;
            }
        }
        if (count == 0) {
            XSSFSheet spreadsheet = wb.createSheet("LoginData");
            writeDataToExcel(userName, password, wb);
        }
    }

    public static void writeDataToExcel(String userName, String password, XSSFWorkbook wb) throws IOException {
        System.out.println("Inside writeDataToExcel");
        System.out.println("Value of row = " + rowid);
        sheet = wb.getSheet("LoginData");
        XSSFRow row = sheet.createRow(rowid);
        XSSFCell cell = row.createCell(cellid);
        cell.setCellValue(userName);
        cell = row.createCell(cellid + 1);
        cell.setCellValue(password);
        rowid++;

        FileOutputStream out = new FileOutputStream(new File(filePath));

        wb.write(out);
        out.close();
    }

    public static void overrideDataToExcel(String userName, String password, XSSFWorkbook wb) throws IOException {
        System.out.println("Inside overrideDataToExcel");
        System.out.println("Value of row = " + rowid);
        sheet = wb.getSheet("LoginData");
        int rowsInLoginData = sheet.getPhysicalNumberOfRows();
        if (rowsInLoginData <= totalRowsForRegistration) {

            if(userCount<=rowsInLoginData){
                sheet.getRow(rowid).getCell(cellid).setCellValue(userName);
                sheet.getRow(rowid).getCell(cellid + 1).setCellValue(password);
                rowid++;

                FileOutputStream out = new FileOutputStream(new File(filePath));

                wb.write(out);
                out.close();
                userCount++;
            }else{
                writeDataToExcel(userName, password, wb);
            }

        } else {
            writeDataToExcel(userName, password, wb);
        }
    }


    public static void setUp() {

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().driverVersion("120.0.6099.110").setup();
        driver = new ChromeDriver(chromeOptions);

    }

    public static void launchUrl() throws IOException {
        readExcelUtility();
        readUrlFromExcel();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        captureScreenshot();
    }


    public static void tearDown() {
        driver.quit();
    }

    public static boolean isPresent(By by) {
        //Boolean  bool = driver.findElement(by).isDisplayed();
        Boolean bool=false;
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            bool = true;
        }catch(Exception e){
            System.out.println("Element not found");

        }return bool;
    }

    public static void captureScreenshot() throws IOException {

        String formattedDate = appendTimeStamp();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File tgt = new File("C:\\Users\\VA338RM\\GitImports\\SeleniumLevel1Assignment\\Screenshots\\Snapshot_"+formattedDate+".png");
        FileUtils.copyFile(src,tgt);
    }

    public static String appendTimeStamp(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hhmmss");
        Date d = new Date();
        String formattedDate = sdf.format(d);
        return formattedDate;
    }

}
