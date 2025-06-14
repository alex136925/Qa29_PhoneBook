package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class ContactTests extends TestBase{

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        User user = new User()
                .setEmail("tipsytutor92@gmail.com")
                .setPassword("Ghtdfg#23");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

    }


    @Test(groups = {"smoke", "regress", "retest"})
    public  void contactSuccessAll(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("John")
                .lastName("Smith")
                .phone("88005553535")
                .email("money" + i + "@yahoo.com")
                .address("London, UK")
                .description("Nice Guy!")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isRegisteredNamePresent("John"));
        Assert.assertTrue(app.getHelperContact().isRegisteredPhonePresent("88005553535"));

    }

    @Test (dataProvider = "contactCSV", dataProviderClass = DataProviderContact.class)
    public  void contactSuccessAllFieldsCSV(Contact contact){

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isRegisteredNamePresent(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isRegisteredPhonePresent(contact.getPhone()));

    }

    @Test
    public  void contactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Jane")
                .lastName("Smith")
                .phone("88005553556")
                .email("money" + i + "@yahoo.com")
                .address("London, UK")
                .description("Not Bad")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isRegisteredNamePresent("Jane"));
        Assert.assertTrue(app.getHelperContact().isRegisteredPhonePresent("88005553556"));


    }

    @Test
    public  void contactFailWrongName(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name(" ")
                .lastName("Smith")
                .phone("88005553556")
                .email("money" + i + "@yahoo.com")
                .address("London, UK")
                .description("Not Bad")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());

    }

    @Test
    public  void contactFailWrongLastName(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Jane")
                .lastName("")
                .phone("88005553556")
                .email("money" + i + "@yahoo.com")
                .address("London, UK")
                .description("Not Bad")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());

    }

    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContact.class)
    public  void contactFailWrongPhone(Contact contact){

        logger.info("Test run with data" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());


    }

    @Test
    public  void contactFailEmptyPhone(){
        Contact contact = Contact.builder()
                .name("Samantha")
                .lastName("Smith")
                .phone("")
                .email("money2@yahoo.com")
                .address("London, UK")
                .description("Not Bad")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());


    }

    @Test
    public  void contactFailWrongEmail(){
        Contact contact = Contact.builder()
                .name("Samantha")
                .lastName("Smith")
                .phone("88005553556")
                .email("money2yahoo.com")
                .address("London, UK")
                .description("Not Bad")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));


    }

    @Test
    public  void contactFailEmptyFields(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("")
                .phone("")
                .email("")
                .address("")
                .description("")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSaveButton();
        Assert.assertTrue(app.getHelperContact().isSaveButtonClickable());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        app.getHelperContact().clickOkButton();


    }



}
