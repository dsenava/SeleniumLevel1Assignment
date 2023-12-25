1.Script Launches guru99 demo url provided in excel(URL sheet)
Script fetches registration details from excel-RegistrationData sheet and feeds into Registration page.
Feature file provides sheetname and row number in Examples
Once registration is successful - Username and password is written into a Excel by creating a new sheet - LoginData
Registration Failure displays appropriate message on screen
2.	Next Step is to Login to application using the username and password provided in LoginData sheet.
Feature file provides sheetname and row number in Examples
3.	Next scenario is to Login using Invalid credentials provided in the Examples section in Feature file
4.	Next scenario is to book flight ticket by logging in using valid credential.
Here However in every case flight is unavailable so displayed same message to the user

