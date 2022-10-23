package org.example;


import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void CSVmethod(String reviewList) {
        File file = new File("date.cvs");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            List<String[]> data = new ArrayList<>();
            data.add(new String[]{" Name ", " Review ", " Date "});
            data.add(new String[]{String.valueOf(reviewList)});
            writer.writeAll(data);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\panan\\IdeaProjects\\Web\\Web\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/title/tt7286456/reviews?ref_=tt_urv"); // Pagina Web accesata


        WebElement hideSpoilers = driver.findElement(By.xpath("//span[@class='lister-widget-sprite lister-checkbox']"));
        hideSpoilers.click();
        Thread.sleep(1000);


        // click pe butonul "Show more comments"
        for (int i = 0; i <= 2; i++) {
            driver.findElement(By.xpath("//*[@id=\"load-more-trigger\"]")).click();
            Thread.sleep(1000);
        }


        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='text show-more__control clickable']"));
        for (WebElement element : elements) {
            element.click();
            Thread.sleep(1000);
        }


        // Creez elementele web care contin path-ul comentariilor / numelor / datelor
        List<WebElement> commentText = driver.findElements(By.xpath("//div[@class='text show-more__control']"));
        List<WebElement> commentName = driver.findElements(By.cssSelector("div.display-name-date > span.display-name-link > a"));
        List<WebElement> commentRating = driver.findElements(By.xpath("//span[@class='rating-other-user-rating']"));


        // lista de stocare a datelor extrase
        List<String> listComment = new ArrayList<>();


        for (int i = 0; i < commentText.size(); i++) {
                // adaug in liste datele in fromat text
                listComment.add(commentName.get(i).getText().concat(" | ").concat(commentText.get(i).getText()).concat("\n"));
        }


        CSVmethod(listComment.toString());


        driver.quit();
    }
}





