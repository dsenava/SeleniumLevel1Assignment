package com.package1.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

import static Package.UserRegistrationPage.*;

public class UserRegistration  {

    @Before
    public static void browserSetUp()  {
        setUp();
    }

    @After
    public static void browserTearDown()  {
        tearDown();
    }


    @Given("User launches demo application")
    public void userLaunchesDemoApplication() throws IOException {
        launchUrl();
    }

    @And("User navigates to Registration page")
    public void userNavigatesToRegistrationPage() throws IOException {
        navigateToRegisterPage();
    }


    @And("User inputs personal details from given sheetName {string} and rownumber {int}")
    public void user_inputs_personal_details_from_given_sheet_name_and_rownumber(String sheetName, Integer rowNumber) throws IOException {
        inputPersonalDetails(sheetName,rowNumber);
    }

    @When("User submits his details")
    public void userSubmitsHisDetails() throws IOException {
        submitDetails();
    }
    
    @Then("User gets registered successfully")
    public void userGetsRegisteredSuccessfully() throws IOException, InvalidFormatException {
        validateRegistration();
    }


}
