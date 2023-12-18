package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

        @Test
    public void loginSuccess(){
        app.getHelperUser().openLogIn();
        app.getHelperUser().fillLoginForm("qwe@d.com", "Qwerty23!");
        app.getHelperUser().submitLogin();

            Assert.assertTrue(app.getHelperUser().isLogged());
        }

}
