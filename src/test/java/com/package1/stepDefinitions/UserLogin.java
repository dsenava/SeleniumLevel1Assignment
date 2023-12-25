package com.package1.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static Package.LoginPage.*;
public class UserLogin {



    @And("User inputs username and password from given sheetName {string} and rownumber {int}")
    public void user_inputs_username_and_password_from_given_sheet_name_and_rownumber(String sheetName, Integer rowNum) throws IOException {
        inputUserNameAndPassword(sheetName,rowNum);
    }


    @When("User clicks on Login")
    public void userClicksOnLogin() {
        clickSubmit();
    }

    @Then("User lands into the Homepage successfully and validate the user login")
    public void userLandsIntoTheHomepageSuccessfullyAndValidateTheUserLogin() throws IOException {
        validateLogin();
    }


    @And("User inputs invalid username {string} and password {string}")
    public void user_inputs_invalid_username_and_password(String username, String inpassword) throws IOException {
        inputUserNameAndPassword(username,inpassword);
    }

    @Then("User login is unsuccessful")
    public void user_login_is_unsuccessful() throws IOException {
        validateLogin();
    }
}
