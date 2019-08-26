package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_Post_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    String empName = RestUtils.empName();
    String empSalary = RestUtils.empSalary();
    String empAge = RestUtils.empAge();

    @BeforeClass
    void createEmployee() throws InterruptedException {

        logger.info("*********Started TC003_Post_Employee_Record **********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", empName);
        requestParams.put("salary",empSalary);
        requestParams.put("age",empAge);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST,"/create");
    }

    @Test
    void checkResponseBody() {

        logger.info("********* Checking Response Body **********");

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empName),true);
        Assert.assertEquals(responseBody.contains(empSalary), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
    }

    @Test
    void checkStatusCode() {

        logger.info("********* Checking Status Code **********");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkStatusLine()
    {
        logger.info("********* Checking Status Line **********");
        String statusLine = response.getStatusLine(); // Gettng status Line
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    @Test
    void checkContentType()
    {
        logger.info("********* Checking Content Type **********");
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "text/html; charset=UTF-8");
    }

    @Test
    void checkServerType()
    {
        logger.info("********* Checking Server Type **********");
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "Apache");
    }

    @AfterClass
    void tearDown() {
        logger.info("*********Finished TC003_Post_Employee_Record **********");
    }

}
