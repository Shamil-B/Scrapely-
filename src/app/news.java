package app;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class news implements Parent{

    news(){}
    ArrayList<String> news_list = new ArrayList<String>();
    ArrayList<String> links_list = new ArrayList<String>();
    boolean found = false;
int x = 5;
    @Override
    public void search(String input) {
        System.setProperty("webdriver.chrome.driver", "C:\\myvs\\webscrape\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.news24.com");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }

        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).sendKeys(input);
        driver.findElement(By.id("searchBtn")).click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }

        List<WebElement> list = driver.findElements(By.className("article-item__title"));
        int i = 0;
        for(WebElement item: list) {
            String txt = item.findElement(By.tagName("span")).getText();
            this.news_list.add(txt);
            String url = driver.findElements(By.className("article-item")).get(i).findElement(By.tagName("a")).getAttribute("href");
            this.links_list.add(url);
            this.found = true;
            i++;

        }

        driver.close();
    }


    @Override
    public String[] displayResult() {
        int size = this.news_list.size();
        int i = 0;

        if (!this.found){
            String[] arr = {"Search not found..."};
            return  arr;

        }
        String[] arr = new String[size];
        while(i<size) {
            String newsExtract = ">> "+this.news_list.get(i) + "  |...for more use the link below... \n "+this.links_list.get(i)+ "\n";
            arr[i] = newsExtract;
            i++;
        }

        return arr;
    }


}
