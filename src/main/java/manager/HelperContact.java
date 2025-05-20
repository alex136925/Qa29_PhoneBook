package manager;

import models.Contact;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm(){
        WebElement form = wd.findElement(By.xpath("//a[@href='/add']"));
        form.click();
    }

    public  void fillContactForm(Contact contact){
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        tabButton();
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        tabButton();
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        tabButton();
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        tabButton();
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        tabButton();
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
        tabButton();
    }

    public void clickSaveButton(){
        WebElement button = wd.findElement(By.xpath("//*[text()='Save']"));
        button.click();
    }

    public  boolean isRegisteredNamePresent(String name){
        try {
            wd.findElement(By.xpath("//h2[text() = '" + name + "']"));
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isRegisteredPhonePresent(String phone){
        try{
            wd.findElement(By.xpath("//h3[text()= '" + phone + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void tabButton(){
        Actions action = new Actions(wd);
        action.sendKeys(Keys.TAB).perform();
    }

    public boolean isSaveButtonClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Save']")));
            return button.isDisplayed() && button.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }


}
