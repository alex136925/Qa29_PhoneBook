package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }


    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Start test with name 'loginSuccess+'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        logger.info("Test data --> email: " +email+ " & password: " +password);
        app.getHelperUser().submitLogin();


        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }



    @Test(dataProvider = "loginFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDPF(User user){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("Test data --> " +user.toString());
        app.getHelperUser().submitLogin();


        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public  void loginWrongEmail(){
        User user = new User().setEmail("mara1gmail.com").setPassword("Roma3456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("Test data --> email: mara1gmail.com & password: Roma3456$ ");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert 'Wrong email or password' present");

    }

    @Test
    public  void loginWrongPassword(){
        User user = new User().setEmail("mara1@gmail.com").setPassword("Ro3456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("Test data --> email: mara1gmail.com & password: Ro3456$ ");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert 'Wrong email or password' present");

    }

    @Test
    public  void loginUnregisteredUser(){
        User user = new User().setEmail("fima13@gmail.com").setPassword("Roma3456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("Test data --> email: fima13@gmail.com & password: Roma3456$ ");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert 'Wrong email or password' present");

    }

}
