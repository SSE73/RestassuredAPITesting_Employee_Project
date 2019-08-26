package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;
    String empID = RestUtils.getEmpId();

    @BeforeClass
    void getEmployeeData() throws InterruptedException {

        logger.info("*********Started TC002_Get_Single_Employee_Record **********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employee/"+empID);
    }

    @Test
    void checkResponseBody() {

        logger.info("********* Checking Response Body **********");
        String responseBody = response.getBody().asString();
        logger.info("Response Body is: "+responseBody);
        Assert.assertEquals(responseBody.contains(empID),true);
    }

    @Test
    void checkStausCode() {

        logger.info("********* Checking Status Code **********");
        int statusCode = response.getStatusCode();
        logger.info("Status Code is: "+statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkResponseTime() {

        logger.info("********* Checking Response Time **********");
        long responseTime = response.getTime();
        logger.info("Response Time is: "+responseTime);
        Assert.assertTrue(responseTime < 10000);
    }

    @Test
    void checkStatusLine() {

        logger.info("********* Checking Status Line **********");

        String statusLine = response.getStatusLine();
        logger.info("Response Status Line: "+ statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType() {

        logger.info("********* Checking Content Type **********");

        String contentType = response.getContentType();
        logger.info("Content Type: "+ contentType);
        Assert.assertEquals(contentType, "text/html; charset=UTF-8");
    }

    @Test
    void checkServerType() {

        logger.info("********* Checking Server Type**********");

        String serverType = response.header("Server");
        logger.info("Server Type: "+ serverType);
        Assert.assertEquals(serverType, "Apache");
    }

    @Test
    void checkContentEncoding() {

        logger.info("********* Checking Content Encoding **********");

        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content Encoding: "+ contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @AfterClass
    void tearDown()
    {
        logger.info("*********Finished TC002_Get_Single_Employee_Record **********");
    }


}
