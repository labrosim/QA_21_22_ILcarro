package manager;

import com.google.common.io.Files;
import models.Car;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public boolean isErrorDisplayed(String message){
        String text = wd.findElement(By.cssSelector("div.ng-star-inserted")).getText();
        return text.equals(message);

    }

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.cssSelector("#year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.cssSelector("#class"), car.getCarClass());
        type(By.cssSelector("#serialNumber"), car.getCarRegNumber());
        type(By.cssSelector("#price"), car.getPrice() + "");
        type(By.cssSelector("#about"), car.getAbout());


    }

    private void select(By locator, String options) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(options);

        //Gas
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }


    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"1/25/2024", "1/28/2024"
        String[] from = dateFrom.split("/"); //["1"]["25"]["2024"]

        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        click(By.xpath("//div[text()=' " + to[1] + " ']"));
    }

    private void typeCity(String city) {
        type(By.id("city"), city);

        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        //"1/20/2024", "5/18/2024"
        LocalDate now = LocalDate.now();
        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfYear();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println(from);

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonth(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonth(diffMonth);
        }

        //"//div[text()=' " + from[1] + " ']"
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
//        typeCity(city);
//        click(By.id("dates")); // 1/05/2024-2/01/2025  12-5=7 0+1  8  1+12-5 8
//
//        LocalDate now = LocalDate.now();
//        int year = now.getYear();
//        int month = now.getMonthValue();
//
//        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
//        LocalDate to = LocalDate.parse(dateTo,DateTimeFormatter.ofPattern("M/d/yyyy"));
//        int diffYear = from.getYear()-year;
//        if(diffYear>0){
//            clickNextYear(diffYear);
//        }
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();

        int year = now.getYear();
        int month = now.getMonthValue();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonth = from.getMonthValue() + (from.getYear() - year) * 12 - month;
        if (diffMonth > 0) {
            clickNextMonth(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() + (to.getYear() - from.getYear()) * 12 - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonth(diffMonth);
        }

        //"//div[text()=' " + from[1] + " ']"
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }




    private void clickNextMonth(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label ='Next month']"));
        }
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        type(By.id("dates"), dataFrom + " - " + dataTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
    }
}