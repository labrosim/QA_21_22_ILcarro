package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
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


    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {

        logger.info("Test data --> email: " + email + " & password: " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();
        logger.info("Assert check is Element button 'Log out' presents");

    }


    @Test(dataProvider = "loginModel", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Test data --> " + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();
        logger.info("Assert check is Element button 'Log out' presents");

    }
    @Test(dataProvider = "loginFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDP(User user) {
        logger.info("Test data --> " + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();
        logger.info("Assert check is Element button 'Log out' presents");

    }

    @Test(groups = {"smoke"})
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


    @AfterMethod(alwaysRun = true)
    public void postConditions() {
        app.getHelperUser().clickOKButton();

    }
}