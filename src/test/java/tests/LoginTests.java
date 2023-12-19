package tests;

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
@AfterMethod
    public void postCondition(){
    app.getHelperUser().clickOKButton();
}
}
