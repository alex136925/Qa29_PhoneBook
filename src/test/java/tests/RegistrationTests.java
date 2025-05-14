package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
@BeforeMethod
public void preCondition(){
    if (app.getHelperUser().isLogged()){
        app.getHelperUser().logout();
    }
}

@Test
public void registrationSuccess(){
    int i = new Random().nextInt(1000)+1000;
    User user = new User().setEmail("don" + i + "@gmail.com").setPassword("Ddon12345$");
    app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm(user);
    app.getHelperUser().submitRegistration();
    Assert.assertTrue(app.getHelperUser().isLogged());
    Assert.assertTrue(app.getHelperUser().isNoContactsDisplayed());
}

    @Test(description = "Bug report #23456 - Fixed")
    public void registrationWrongEmail() {
        User user = new User().setEmail("dongmail.com").setPassword("Ddon12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        app.getHelperUser().clickOkButton();
    }


    @Test
    public void registrationWrongPassword() {
        User user = new User().setEmail("don" + "@gmail.com").setPassword("Ddon");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        app.getHelperUser().clickOkButton();
    }

    @Test
    public void registrationExistUser() {
        User user = new User().setEmail("mara1@gmail.com").setPassword("Roma3456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        app.getHelperUser().clickOkButton();

    }


    }






