package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestBase;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }

    }

    @Test
    public void loginSuccess1() {
        logger.info("Start test with name `loginSuccess`");
        logger.info("Test data --> email: `marga@gmail.com` & password: `Mmar123456$`");
        User user = new User().setEmail("marga@gmail.com").setPassword("Mmar123456$");
/*
        User user1 = new User();
        user1.setEmail();
        user.setEmail("marga@gmail.com");
        user.setPassword("Mmar123456$");
*/

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();
        logger.info("Assert check is Element button 'Log out' presents");

    }


    @Test
    public void loginSuccess() {

        logger.info("Test data --> email: `marga@gmail.com` & password: `Mmar123456$`");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marga@gmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();
        logger.info("Assert check is Element button 'Log out' presents");

    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test data --> email: `marga@gmail.com` & password: `Mmar123456$`");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marga@gmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();
        logger.info("Assert check is Element button 'Log out' presents");

    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test data --> email: `margagmail.com` & password: `Mmar123456$`");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margagmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check is alert with error text `It'snot look like email`");
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test data --> email: `marga@gmail.com` & password: `Mmar123`");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marga@gmail.com", "Mmar123");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check is alert with error text `Login or Password incorrect`");
    }


    @Test
    public void loginWrongUnregisteredUser() {
        logger.info("Test data --> email: `luck@gmail.com` & password: `Luck123456$`");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luck@gmail.com", "Luck123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check is alert with error text `Login or Password incorrect`");
    }


    @AfterMethod
    public void postConditions() {
        app.getHelperUser().clickOKButton();

    }
}