package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver driver) {
        super(driver);
    }

    public void openLogIn() {

        //driver.findElement(By.xpath("//a[text()=' Log in ']")).click();
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        WebElement emailInput = driver.findElement(By.id("email"));
        type(By.id("email"), email);
        WebElement passwordInput = driver.findElement(By.id("password"));
        type(By.id("password"), password);

    }

    //Overloading
    public void fillLoginForm(User user) {
        WebElement emailInput = driver.findElement(By.id("email"));
        type(By.id("email"), user.getEmail());
        WebElement passwordInput = driver.findElement(By.id("password"));
        type(By.id("password"), user.getPassword());

    }



    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }


    public void clickOKButton() {
        if (isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public void logOut() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public String getErrorText() {
        return driver.findElement(By.cssSelector("div.error")).getText();
    }


    ////***********REGISTRATION*******************
    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));

    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //click(By.cssSelector("label[for='terms-of-use']"));

        // variant 2

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector('#terms-of-use').click();");
    }
    public void checkPolicyXY(){
        WebElement label = driver.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int w = rect.getWidth();
        int xOffSet = -w/2;
        Actions actions = new Actions(driver);
        actions.moveToElement(label, xOffSet, 0).click().release().perform();
    }
    public void login(User user){
        openLogIn();
        fillLoginForm(user);
        clickOKButton();
    }
}
