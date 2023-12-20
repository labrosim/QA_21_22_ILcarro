package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logOut();
        }
    }
    @Test
    public void loginSuccess1(){
        //User user = new User();
        //user.setEmail("qwe@d.com");
        //user.setPassword("Qwerty23!");
        User user = new User().setEmail("qwe@d.com").setPassword("Qwerty23!");



        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");


    }

        @Test
    public void loginSuccess(){
        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm("qwe@d.com", "Qwerty23!");
        app.getHelperUser().submitLogin();

            Assert.assertTrue(app.getHelperUser().isLogged());
            Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");


        }

    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm("qwe@d.com", "Qwerty23!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");


    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm("qwed.com", "Qwerty23!");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm("qwe@d.com", "Qwer");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }
    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm("111qwe@d.com", "LLQwerty23!");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }


@AfterMethod
    public void postCondition(){

        app.getHelperUser().clickOKButton();
}
}
