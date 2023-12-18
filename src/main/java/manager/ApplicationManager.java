package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver driver;
    HelperUser helperUser;

    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://ilcarro.web.app/search");
        helperUser = new HelperUser(driver);
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop(){
        driver.quit();
    }


}
