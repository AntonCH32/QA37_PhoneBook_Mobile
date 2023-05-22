package tests;

import config.AppiumConfig;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.BaseScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig
{
    @Test
    public void loginSuccess()
    {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("ant@gmail.com")
                .fillPassword("Ant12345$")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel()
    {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ant@gmail.com").password("Ant12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginWrongEmail()
    {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ant@gmail.com").password("Ant12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

    @Test
    public void loginWrongPassword()
    {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ant@gmail.com").password("Ant12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

    @AfterMethod
    public void posCondition() {
        new ContactListScreen(driver).logout();
    }
}
