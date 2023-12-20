package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver driver) {
        super(driver);
    }
    public void openLogIn(){

        //driver.findElement(By.xpath("//a[text()=' Log in ']")).click();
        click(By.xpath("//a[text()=' Log in ']"));
    }
    public void fillLoginForm(String email, String password){
        WebElement emailInput = driver.findElement(By.id("email"));
        type(By.id("email"), email);
        WebElement passwordInput = driver.findElement(By.id("password"));
        type(By.id("password"), password);

    }
    //Overloading
    public void fillLoginForm(User user){
        WebElement emailInput = driver.findElement(By.id("email"));
        type(By.id("email"), user.getEmail());
        WebElement passwordInput = driver.findElement(By.id("password"));
        type(By.id("password"), user.getPassword());

    }
    public void submitLogin(){
        click(By.xpath("//*[@type='submit']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }


    public void clickOKButton() {
        if(isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public void logOut() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public String getErrorText() {
        return driver.findElement(By.cssSelector("div.error")).getText();
    }


}
