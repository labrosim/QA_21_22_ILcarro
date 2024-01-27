package tests;



import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeClass(alwaysRun = true)
    public void preConditions(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
            logger.info("Before class --> User is logged in");
        }

    }

    @Test(dataProvider = "carSuccess", dataProviderClass = DataProviderCar.class)
    public void addNewCarSuccessAll(Car car){
        int i = new Random().nextInt(1000)+1000;
//        Car car = Car.builder()
//                .location("Tel Aviv, Israel")
//                .manufacture("Mazda")
//                .model("M3")
//                .year("2022")
//                .fuel("Petrol")
//                .seats(4)
//                .carClass("C")
//                .carRegNumber("678-900-"+i+"2")
//                .price(50)
//                .about("Very nice car")
//                .build();
        logger.info("Test data --> location: " + car.getLocation() + " & manufacture: " + car.getManufacture()
                + " & model: " +car.getModel() +" & year: " +car.getYear() + " & fuel: " +car.getFuel()
                + " & seats: " +car.getSeats()+ " & carClass: " +car.getCarClass() + " & price: " +car.getPrice()
                        + " & about: " + car.getAbout());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("C:\\Users\\Alex\\Documents\\GitHub\\QA_21_22_ILcarro\\02-bugatti-cd-nardo-testing.jpg");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("added successful"));
        logger.info("Assert --> `added successful`");
        Assert.assertEquals(app.getHelperUser().getMessage(),car.getManufacture()
                +" "+car.getModel()
                + " added successful");

    }


    @Test(groups = {"smoke", "regress", "retest"})
    public void addNewCarReqSuccessA(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-"+i)
                .price(50)
                .build();
        logger.info("Test data --> location: `Tel Aviv, Israel` & manufacture: `Mazda` & model: `M3` & " +
                "year: `2022` & fuel: `Petrol` & seats: `4` & carClass: `C` & carRegNumber: `678-900-+i` & price: `50` & " +
                "about: `Very nice car`");
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().getMessage().contains("added successful"));
        logger.info("Assert --> `added successful`");
        Assert.assertEquals(app.getHelperUser().getMessage(),car.getManufacture()
                +" "+car.getModel()
                + " added successful");
    }
@AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.getHelperCar().returnToHome();
}


}