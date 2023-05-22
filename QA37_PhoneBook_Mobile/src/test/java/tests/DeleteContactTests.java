package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DeleteContactTests extends AppiumConfig {

    @BeforeClass
    public void PreCondition()
    {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ant@gmail.com").password("Ant12345$").build())
                .submitLogin();

    }

    @Test
    public void deleteFirstContact()
    {
        new ContactListScreen(driver)
                .deleteFirstContact()
                .isListSizeLessThenOne();
    }

    @Test
    public void deleteAllContact()
    {
        new ContactListScreen(driver)
                .deleteAllContacts();

    }
}
