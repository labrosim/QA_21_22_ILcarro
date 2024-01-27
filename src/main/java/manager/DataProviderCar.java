package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCar {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> carSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Car.builder()
                        .location("Tel Aviv, Israel")
                        .manufacture("Mazda")
                        .model("M3")
                        .year("2022")
                        .fuel("Petrol")
                        .seats(4)
                        .carClass("C")
                        .carRegNumber("678-900-582")
                        .price(50)
                        .about("Very nice car")
                        .build()
        });
        list.add(new Object[]{
                Car.builder()
                        .location("Tel Aviv, Israel")
                        .manufacture("Mazda")
                        .model("M3")
                        .year("2022")
                        .fuel("Petrol")
                        .seats(4)
                        .carClass("C")
                        .carRegNumber("678-900-698")
                        .price(50)
                        .build()
        });
        return list.iterator();
    }
}
