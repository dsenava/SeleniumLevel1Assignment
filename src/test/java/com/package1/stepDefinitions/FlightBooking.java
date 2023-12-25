package com.package1.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static Package.FlightBookingPage.*;
import static Package.LoginPage.inputUserNameAndPassword;

public class FlightBooking {
    @And("User is able to login to the application with username {string} and password {string}")
    public void user_is_able_to_login_to_the_application_with_username_and_password(String username, String password) throws IOException {
        inputUserNameAndPassword(username,password);
    }


    @And("User navigates to Flight booking page")
    public void user_navigates_to_flight_booking_page() throws IOException {
        navigateToFlightBookingPage();
    }

    @When("User inputs all flight details")
    public void user_inputs_all_flight_details() throws IOException {
        closeAds();
        inputFlightDetails();
    }

    @And("User clicks on Continue")
    public void user_clicks_on_continue() {
        clickOnContinue();
    }

    @Then("validate success message {string} if seats are available")
    public void validate_success_message_if_seats_are_available(String expectedMessage) throws IOException {
        validateFlightBooking(expectedMessage);
    }
}
