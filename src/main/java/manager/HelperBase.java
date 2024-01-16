package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null) {
            System.out.println("hello");
            element.sendKeys(text);
        }


    }

    public String getElementAttribute(By locator, String attributeName) {
        WebElement element = wd.findElement(locator);
        return element.getAttribute(attributeName);
    }


    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

    }

    public void submit() {
        wd.findElement(By.xpath("//button[@type='submit']"))
                .click();
    }

    public String getMessage() {

//        WebElement el = wd.findElement(By.cssSelector(".dialog-container>h2"));
//        String text = el.getText();
//        return text;
        //pause(5000);

        return
                wd.findElement(By.cssSelector(".dialog-container>h2"))
                        .getText();

    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    boolean isElementPresent(By locator) {
        //return wd.findElements(locator).size()>0;
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;

    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));

        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;
    }
}