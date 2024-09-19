package TestComponents;

import Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener{
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result){
        test =extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique thread id
    }

    @Override
    public void onTestSuccess(ITestResult result){
        extentTest.get().log(Status.PASS,"Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result){
        extentTest.get().fail(result.getThrowable());

        try {
            driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filePath = null;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(),driver);
        }catch (IOException e){
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        //Screenshot attach to report
    }

    @Override
    public void onTestSkipped(ITestResult result){

    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){

    }
    @Override
    public void onStart(ITestContext context){

    }
    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }
}
