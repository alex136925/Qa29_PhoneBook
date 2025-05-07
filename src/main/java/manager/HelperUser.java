package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public  void  openLoginRegistrationForm(){
        WebElement loginTab = wd.findElement(By.xpath("//a[text() = 'LOGIN']"));

        loginTab.click();
    }

    public  void fillLoginRegistrationForm(String email, String password){

       // WebElement emailInput = wd.findElement(By.name("email"));
       // emailInput.click();
       // emailInput.clear();
       // emailInput.sendKeys(email);
        type(By.name("email"), email);

       // WebElement passwordInput = wd.findElement(By.xpath("//input[last()]"));
      //  passwordInput.click();
        // passwordInput.clear();
        //passwordInput.sendKeys(password);
        type(By.xpath("//input[last()]"), password);
    }

    public  void submitLogin(){
        click(By.cssSelector("button[name='login']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }
}
