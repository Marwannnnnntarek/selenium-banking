package banking.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

public class Utils {

    public static final String FIREFOX_PATH = "C:\\Users\\Marwan\\Downloads\\geckodriver-v0.36.0-win64\\geckodriver.exe";
    public static final String BASE_URL = "https://www.demo.guru99.com/V4/";
    public static final String EXPECTED_TITLE = "Guru99 Bank Manager HomePage";
    public static final String EXCEL_FILE_PATH = "C:\\Users\\Marwan\\Downloads\\Login_data.xlsx";
    public static final String SHEET_NAME = "Sheet1";

    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() throws IOException, InvalidFormatException {
        return ExcelUtils.getExcelData(EXCEL_FILE_PATH, SHEET_NAME);
    }
}
