package org.example;


import com.opencsv.CSVWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void CSVmethod(String nameList) {
        File file = new File("date.cvs");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"Name", "Review", "Date"});
            data.add(new String[]{String.valueOf(nameList)});
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


        // click pe butonul "Show more comments"
        for (int i = 0; i <= 5; i++) {
            driver.findElement(By.xpath("//*[@id=\"load-more-trigger\"]")).click();
            Thread.sleep(2000);
        }


        // Creez elementele web care contin path-ul comentariilor / numelor / datelor
        List<WebElement> commentText = driver.findElements(By.xpath("//a[contains (@class , 'title') ]"));
        List<WebElement> commentName = driver.findElements(By.cssSelector("div.display-name-date > span.display-name-link > a"));
        List<WebElement> commentDate = driver.findElements(By.xpath("//span[@class='review-date']"));

        // lista de stocare a datelor extrase
        List<String> listComment = new ArrayList<>();


        for (int i = 0; i < commentText.size(); i++) {
            // adaug in liste datele in fromat text
            listComment.add(commentName.get(i).getText().concat(" | ").concat(commentText.get(i).getText()).concat(" | ").concat(commentDate.get(i).getText()).concat("\n"));

        }

        CSVmethod(listComment.toString());


        driver.quit();
    }
}





