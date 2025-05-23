package manager;

import models.Contact;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {


    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/add']")));
        form.click();
    }

    public void fillContactForm(Contact contact) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")));
        nameInput.clear();
        nameInput.sendKeys(contact.getName());

        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }


    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Save']")));
        button.click();

        new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/add']")));
    }

    public boolean isRegisteredNamePresent(String name) {
        try {
            wd.findElement(By.xpath("//h2[text() = '" + name + "']"));
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isRegisteredPhonePresent(String phone) {
        try {
            wd.findElement(By.xpath("//h3[text()= '" + phone + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void tabButton() {
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


    public boolean isAddNewContactPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public List<WebElement> contactList() {
        return wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM'][.//h3]"));
    }

    public void removeContact() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));

        WebElement removeContact = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Remove']")));
        removeContact.click();


        wait.until(ExpectedConditions.invisibilityOf(removeContact));
    }



    public void provideContacts()  {
        Random random = new Random();
        for(int w = 0; w < 6; w++) {
            int emailInt = random.nextInt(1000) + 1000;
            int nameInt = random.nextInt(1000) + 1000;
            int phoneInt = random.nextInt(1000) + 1000;
            Contact contact = Contact.builder()
                    .name("John" + nameInt)
                    .lastName("Smith")
                    .phone("8800555353" + phoneInt)
                    .email("money" + emailInt + "@yahoo.com")
                    .address("London, UK")
                    .description("Nice Guy!")
                    .build();

            openContactForm();
            fillContactForm(contact);
            clickSaveButton();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        List<WebElement> contacts = contactList();

    }

    public boolean areContactsEmpty() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//h1[normalize-space()='No Contacts here!']"),
                    "No Contacts here!"
            ));
        } catch (TimeoutException e) {
            return false;
        }
    }


}
