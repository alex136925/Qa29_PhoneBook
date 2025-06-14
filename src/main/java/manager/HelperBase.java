package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(text);
    }


    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public  boolean isElementPresent(By locator){
        List<WebElement>  list = wd.findElements(locator);
        return list.size()>0;
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        if (alert!= null && alert.getText().contains(message)){
            //alert.accept(); --> click OK
            //alert.dismiss(); --> click cancel
            //alert.sendKeys("hello"); --> type into alert
            alert.accept();
            return  true;
        }
        return  false;
    }

    public void clickOkButton() {
        try {
            WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(text())='Ok']")
            ));
            okButton.click();
        } catch (Exception e) {
            System.out.println("OK button not found or not clickable: " + e.getMessage());
        }
    }

    public  void clearNew(WebElement element){
        element.sendKeys("");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void getScreen(String link){
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
       File tmp =  takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
