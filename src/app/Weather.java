package app;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Weather implements Parent{

    Weather(){}
    String temperature;
    String precipitation;
    String humidity;
    String wind;
    String city;

    @Override
    public void search(String city) {
        System.setProperty("webdriver.chrome.driver", "C:\\myvs\\webscrape\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.google.com/en");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Error occured!");
        }

        WebElement r =driver.findElement(By.name("q"));
        r.sendKeys(city+" weather");
        r.sendKeys(Keys.RETURN);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }
        this.city = driver.findElement(By.id("wob_loc")).getText();
        this.temperature = driver.findElement(By.id("wob_tm")).getText();
        this.precipitation = driver.findElement(By.id("wob_pp")).getText();
        this.humidity = driver.findElement(By.id("wob_hm")).getText();
        this.wind = driver.findElement(By.id("wob_ws")).getText();


        driver.close();




    }

    @Override
    public String[] displayResult() {
        String[] arr = {">> City: "+this.city, "   Temperature: "+this.temperature+"   Degree Celsius",
                "   Precipitation: "+this.precipitation,"   Humidity: "+this.humidity, "   Wind: "+this.wind};
        return arr;

    }


}

