package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void deleteEmployee() throws InterruptedException {

        logger.info("********* Started TC005_Delete_Employee_Record **********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET, "/employees");

        JsonPath jsonPathEvaluator = response.jsonPath();

        String empID = jsonPathEvaluator.get("[0].id");
        response = httpRequest.request(Method.DELETE, "/delete/"+empID); //Pass ID to delete record

    }

    @Test
    void checkResponseBody(){

        logger.info("********* Checking Response Body **********");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
    }

    @Test
    void checkStatusCode()
    {
        logger.info("********* Checking Status Code **********");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkStatusLine()
    {
        logger.info("********* Checking Status Line **********");
        String statusLine = response.getStatusLine();
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
    void teatDown() {
        logger.info("********* Finished TC005_Delete_Employee_Record **********");
    }

}
