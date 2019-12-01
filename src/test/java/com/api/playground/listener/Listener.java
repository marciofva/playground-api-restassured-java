package com.api.playground.listener;

import java.util.logging.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		Logger.getGlobal().info("===================     Start Test < " + result.getMethod().getMethodName() + " > ===================\n");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Logger.getGlobal().info("===================     Test Passed  < " + result.getMethod().getMethodName() + " > ===================\n");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Logger.getGlobal().severe("===================     Test failed  < " + result.getMethod().getMethodName() + " > ===================\n");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Logger.getGlobal().warning("===================     Test Skipped  < " + result.getMethod().getMethodName() + " > ===================\n");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {		
	}

	@Override
	public void onStart(ITestContext context) {
		Logger.getGlobal().info("===================     Start Suite  < " + context.getName() + " > ===================\n");
	}

	@Override
	public void onFinish(ITestContext context) {
		Logger.getGlobal().info("===================     Finish Suite  < " + context.getName() + " > ===================\n");
	}
}
