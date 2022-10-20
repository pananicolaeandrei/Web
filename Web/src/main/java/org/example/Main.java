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
    public static void CSVmethod(String nameList, String commentList, String dateList) {
        File file = new File("date.cvs");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer =new CSVWriter(outputfile);
            List<String[]> data = new ArrayList<>();
            data.add(new String[] {"Name","Review","Date"});
            data.add(new String[] {String.valueOf(nameList)});
            data.add(new String[] {String.valueOf(commentList)});
            data.add(new String[] {String.valueOf(dateList)});
            writer.writeAll(data);

            writer.close();

        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrei\\IdeaProjects\\Web\\Web\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/title/tt2193021/reviews?ref_=tt_urv"); // Pagina Web accesata


        // Creez elementele web care contin path-ul comentariilor / numelor / datelor
        List<WebElement> commentText = driver.findElements(By.xpath("//a[contains (@class , 'title') ]"));
        List<WebElement> clientName = driver.findElements(By.cssSelector("div.display-name-date > span.display-name-link > a"));
        List<WebElement> commentDate = driver.findElements(By.xpath("//span[@class='review-date']"));

        // liste de stocare a datelor extrase
        List<String> listComment = new ArrayList<>();
        List<String> listName = new ArrayList<>();
        List<String> listDate = new ArrayList<>();

        for (int i = 0; i < commentText.size(); i++)
        {
            listName.add(clientName.get(i).getText());
            listComment.add(commentText.get(i).getText()); // adaug in liste datele in fromat text
            listDate.add(commentDate.get(i).getText());

        }

        CSVmethod( listName.toString(), listComment.toString(), listDate.toString());



        driver.quit();
    }
}





