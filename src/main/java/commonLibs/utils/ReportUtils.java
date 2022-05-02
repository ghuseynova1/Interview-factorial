package commonLibs.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ReportUtils {
    ExtentHtmlReporter htmlReport;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public ReportUtils(String htmlReportFile) {
        htmlReportFile = htmlReportFile.trim();
        htmlReport = new ExtentHtmlReporter(htmlReportFile);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReport);
    }

    public void createATestCase(String testCaseName) {
        extentTest = extentReports.createTest(testCaseName);
    }

    public void addTestLog(Status status, String comment) {
        extentTest.log(status,comment);
    }

    public void attachScreenshotToReport (String fileName) throws Exception {
        extentTest.addScreenCaptureFromPath(fileName);
    }

    public void flushReport() {
        extentReports.flush();
    }
}
