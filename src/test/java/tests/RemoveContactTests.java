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

    @BeforeClass(alwaysRun = true)
    public  void preCondition(){
        User user = new User().setEmail("tipsytutor92@gmail.com").setPassword("Ghtdfg#23");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        app.getHelperContact().provideContacts();

    }


//    @Test
//    public  void removeFirstContact(){
//        List<WebElement> contacts = app.getHelperContact().contactList();
//       Assert.assertTrue(contacts.size() > 0, "No contacts found before deletion test.");
//    WebElement firstContact = contacts.get(0);
//    firstContact.click();
//    app.getHelperContact().removeContact();
//    }

    @Test(groups = {"smoke"})
    public  void removeFirstContact2(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
    }



//    @Test
//    public void removeAllContacts() {
//        while (true) {
//            List<WebElement> contacts = app.getHelperContact().contactList();
//
//            if (contacts.isEmpty()) break;
//
//            WebElement contact = contacts.get(0);
//            contact.click();
//            app.getHelperContact().removeContact();
//
//            // Wait until the number of contacts is less than before
//            app.getHelperContact().waitForContactCountToDecrease(contacts.size());
//        }
//
//        Assert.assertTrue(app.getHelperContact().areContactsEmpty());
//    }



    @Test (dependsOnMethods = {"removeFirstContact2"})
    public  void removeAllContacts2(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperUser().isNoContactsDisplayed());

    }


}



