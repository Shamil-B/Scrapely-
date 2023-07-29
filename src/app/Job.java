package app;

import java.util.List;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Job implements Parent{

    Job(){}
    boolean found = false;
    ArrayList<String> title_list = new ArrayList<String>();
    ArrayList<String> companyName_list = new ArrayList<String>();
    ArrayList<String> location_list = new ArrayList<String>();
    ArrayList<String> level_list = new ArrayList<String>();
    ArrayList<String> date_list = new ArrayList<String>();
    ArrayList<String> link_list = new ArrayList<String>();


    @Override
    public void search(String input) {
        System.setProperty("webdriver.chrome.driver", "C:\\myvs\\webscrape\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.ethiojobs.net/");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }

        WebElement searchBar= driver.findElement(By.className("form-control"));
        searchBar.sendKeys(input);
        WebElement searchButton = driver.findElement(By.id("btn-search"));
        searchButton.click();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Error occured!");
        }

        List<WebElement> jobs = driver.findElements(By.className("listing-section"));
        for(WebElement job:jobs) {
            WebElement names = job.findElement(By.className("detailed_view"));
            String title = job.findElement(By.tagName("h2")).getText();
            this.title_list.add(title);
            String companyName = names.findElements(By.tagName("span")).get(0).getText();
            this.companyName_list.add(companyName);
            String location = names.findElements(By.tagName("span")).get(2).getText();
            this.location_list.add(location);
            String level = names.findElements(By.tagName("span")).get(4).getText();
            this.level_list.add(level);
            String date = names.findElements(By.tagName("span")).get(5).getText();
            this.date_list.add(date);
            String url = job.findElement(By.className("viewDetails")).findElement(By.tagName("a")).getAttribute("href");
            this.link_list.add(url);
        }


        driver.close();




    }
    @Override
    public String[] displayResult() {

        String[] arr = new String[this.title_list.size()];
        int index = 0;
        while(index<this.title_list.size()) {
            String extract = ">> Job title: "+this.title_list.get(index) +
                    "\n   Company Name: "+this.companyName_list.get(index) +
                    "\n   Location: "+this.location_list.get(index) +
                    "\n   Level: "+this.level_list.get(index) +
                    "\n   Link: "+this.link_list.get(index)+
                    "\n";
            arr[index] = extract;

            index++;
        }
        return arr;
    }




}

