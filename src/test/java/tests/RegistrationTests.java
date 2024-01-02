package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;
import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000);

        User user = new User().withFirstName("Vera").withLastName("Rainbow").
                setEmail("vera" + i + "@mail.ru").setPassword("Snow1234$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }


    @AfterMethod
    public void postCondition() {

        app.getHelperUser().clickOKButton();
    }

//    @Test
//    public void registrationWrongEmail(){
//        User user = new User().withFirstName("qwer").withLastName("ee").
//                setEmail("qwed@jcom").setPassword("Qwerty1!");
//        app.getHelperUser().openRegistrationForm();
//        app.getHelperUser().fillRegistrationForm(user);
//        app.getHelperUser().checkPolicy();
//        app.getHelperUser().submit();
//
//
//    }
//    @Test
//    public void registrationWrongPassword(){
//        User user = new User().withFirstName("qwer").withLastName("ee").
//                setEmail("qwed@j.com").setPassword("qwerty1!");
//        app.getHelperUser().openRegistrationForm();
//        app.getHelperUser().fillRegistrationForm(user);
//        app.getHelperUser().checkPolicy();
//        app.getHelperUser().submit();
//    }
//    @Test
//    public void registrationWithoutCheckBox(){
//        User user = new User().withFirstName("qwer").withLastName("ee").
//                setEmail("qwed@j.com").setPassword("Qwerty1!");
//        app.getHelperUser().openRegistrationForm();
//        app.getHelperUser().fillRegistrationForm(user);
//        app.getHelperUser().submit();
//    }
}
