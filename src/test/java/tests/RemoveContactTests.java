package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class RemoveContactTests extends TestBase{

    @BeforeClass
    public  void preCondition(){
        User user = new User().setEmail("tipsytutor92@gmail.com").setPassword("Ghtdfg#23");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        app.getHelperContact().provideContacts();

    }


    @Test
    public  void removeFirstContact(){
        List<WebElement> contacts = app.getHelperContact().contactList();
        Assert.assertTrue(contacts.size() > 0, "No contacts found before deletion test.");
    WebElement firstContact = contacts.get(0);
    firstContact.click();
    app.getHelperContact().removeContact();
    }



    @Test (dependsOnMethods = "removeFirstContact")
    public void removeAllContacts() {
        while (true) {
            List<WebElement> contacts = app.getHelperContact().contactList();
            if (contacts.isEmpty()) break;

            contacts.get(0).click();
            app.getHelperContact().removeContact();
        }
        Assert.assertTrue(app.getHelperContact().areContactsEmpty());
    }



}



