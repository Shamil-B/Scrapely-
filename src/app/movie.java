package app;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class movie implements Parent{

    //setting the driver executable
    movie(){}
    String rating;
    String storyline;
    String movieName;
    String link ;

    @Override
    public void search(String input) {
        System.setProperty("webdriver.chrome.driver", "C:\\myvs\\webscrape\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.imdb.com");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }

        driver.findElement(By.id("suggestion-search")).sendKeys(input);
        driver.findElement(By.id("iconContext-magnify")).click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }

        driver.findElement(By.className("primary_photo")).click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }
        this.rating = driver.findElement(By.className("sc-7ab21ed2-1")).getText();
        this.storyline = driver.findElement(By.className("sc-16ede01-1")).getText();
        this.link = driver.getCurrentUrl();
        this.movieName = driver.findElement(By.className("sc-b73cd867-0")).getText();
        driver.close();




    }

    @Override
    public String[] displayResult() {
        String[] arr = {">> Movie Name:  "+this.movieName,"    Rating on IMDB:  "+this.rating+"/10",
                "    Plot:  "+this.storyline, "    Link:  "+this.link};

        return arr;

    }


}
