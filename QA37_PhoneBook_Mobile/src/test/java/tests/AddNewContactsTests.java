package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.*;
import screens.AuthenticationScreen;
import screens.BaseScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ant@gmail.com").password("Ant12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");


    }

    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Ant")
                .lastname( "Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("6789456"+i)
                .address("NY")
                .description("The best friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastname());
    }

    @Test
    public void createContactWithEmptyName()
    {
        Contact contact = Contact.builder()
                .name("")
                .lastname( "Rex")
                .email("wow@gmail.com")
                .phone("67894562345")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");

    }

    @Test
    public void createNewContactSuccessReq()
    {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastname( "Rex")
                .phone("67894562345")
                .address("Paris")
                .description("Req")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm();
    }

    @AfterClass
    public void postCondition(){

        new ContactListScreen(driver)
                .logout();
    }
}