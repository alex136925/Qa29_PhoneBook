package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase{



    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara1@gmail.com", "Roma3456$");
        app.getHelperUser().submitLogin();
    }

}
