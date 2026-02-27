package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest{

    @Test(priority = 1)
    public void decathlonLoadAPITest(){
        RestAssured.useRelaxedHTTPSValidation();
        int statusCode = RestAssured.given().when()
                .get("https://emicalculator.net/")
                .then()
                .extract().statusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("Status Code for EMICalculator Home Page Load - "+ statusCode);
    }

    @Test(priority = 2)
    public void validateCarLoanAmountGET() {
        RestAssured.useRelaxedHTTPSValidation();

        // 1. Build Request Specification
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://emicalculator.net/")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .build();

        // 2. Execute GET request with "loan_amount" parameter
        // Note: We use the root path "/" as the calculator is on the homepage
        Response response = RestAssured.given()
                .spec(requestSpec)
                .queryParam("loan_amount", "1500000") // This sets the Car Loan Amount
                .when()
                .get("/")
                .then()
                .extract().response();

        // 3. Validation
        System.out.println("Status Code Car Loan Amount Verification: " + response.getStatusCode());

        // Check if request was successful
        Assert.assertEquals(response.getStatusCode(), 200);

        // Verify if the value 400,000 is present in the page source
        // (This confirms the "GET" method successfully passed the data to the site)
        Assert.assertTrue(response.getBody().asString().contains("1500000"));
    }

}