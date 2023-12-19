package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }
    public void type(By locator, String text){
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        if(element != null){
            element.sendKeys(text);
        }

    }
    public void click(By locator){
        WebElement element = driver.findElement(locator);
        element.click();
    }
    public boolean isElementPresent(By locator){
        List<WebElement> list = driver.findElements(locator);
        return list.size() > 0;
    }
    public String getMessage() {
        pause(8000);
        return driver.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
