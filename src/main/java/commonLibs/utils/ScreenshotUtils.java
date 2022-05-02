package commonLibs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtils {
    private TakesScreenshot screenshot;

    public ScreenshotUtils(WebDriver driver) {
        screenshot = (TakesScreenshot) driver;
    }

    public void captureAndSaveScreenshot(String fileName) throws Exception {
        fileName = fileName.trim();

        File imgFile, tmpFile;

        imgFile = new File(fileName);

        if(imgFile.exists()) {
            throw  new Exception("File already exist");
        }

        tmpFile = screenshot.getScreenshotAs(OutputType.FILE);

        FileUtils.moveFile(tmpFile,imgFile);
    }
}
