package tests;

import models.User;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preConditions() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method --> User is logged out");
        }

    }


    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("snow"+i+"@mail.ru")
                .setPassword("Snow123654$");
        logger.info("Test data --> firstName: `Lisa` & lastName: `Snow` & email: `snow+i+@mail.ru` & password:`Snow123654$`");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRgistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Assert --> `You are logged in success`");
    }



    @Test
    public void registrationEmptyName(){
        User user = new User()
                .withFirstName("")
                .withLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp123456$");
        logger.info("Test data --> firstName: `` & lastName: `Simpson` & email: `simpson@gmail.com` & password:`Simp123456$`");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRgistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        logger.info("Assert --> `Name is required`");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert --> `Yalla Button Not Active`");

    }

    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .withFirstName("Gomer")
                .withLastName("")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp123456$");
        logger.info("Test data --> firstName: `Gomer` & lastName: `` & email: `simpson@gmail.com` & password:`Simp123456$`");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRgistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        logger.info("Assert --> `Last name is required`");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert --> `Yalla Button Not Active`");
    }

    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .withFirstName("Gomer")
                .withLastName("Simpson")
                .setEmail("simpsongmail.com")
                .setPassword("Simp123456$");
        logger.info("Test data --> firstName: `Gomer` & lastName: `Simpson` & email: `simpsongmail.com` & password:`Simp123456$`");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRgistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        logger.info("Assert --> `Wrong email format`");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert --> `Yalla Button Not Active`");
    }

    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .withFirstName("Gomer")
                .withLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp123");
        logger.info("Test data --> firstName: `Gomer` & lastName: `Simpson` & email: `simpson@gmail.com` & password:`Simp123`");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRgistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        logger.info("Assert --> `Password must contain minimum 8 symbols\\n\" +\n" +
                "                \"Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]`");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert --> `Yalla Button Not Active`");
    }




    @AfterMethod
    public void postConditions() {
        app.getHelperUser().clickOKButton();

    }


}