package com.employeeapi.utilities;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils extends TestBase {

    public static String empName() {

        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("SSESEV"+generatedString);
    }

    public static String empSalary() {

        String generatedString = RandomStringUtils.randomNumeric(5);
        return generatedString;
    }

    public static String empAge() {
        String generatedString = RandomStringUtils.randomNumeric(2);
        return generatedString;
    }

    public static String getEmpId() {

        RequestSpecification httpRequest;
        Response response;

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET, "/employees");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        //Capture id
        String empID = jsonPathEvaluator.get("[0].id");

//        String empID="66803"; //Hard coded - Input for Get Details of Single Employee & update employee 57527

        return empID;

    }

}
