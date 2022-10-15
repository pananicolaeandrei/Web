package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/title/tt2193021/reviews?ref_=tt_urv");
        System.out.println(driver.getTitle());
        //WebElement webElement = driver.findElement(By.xpath("//*[@id=\"unic-b\"]/div/div/div/div[3]/div[1]/button[2]"));
        //webElement.click();




        List<WebElement> commentText = driver.findElements(By.xpath("//a[contains (@class , 'title') ]"));
        List<String> listComment = new ArrayList<>();
        List<WebElement> clientName = driver.findElements(By.cssSelector("div.display-name-date > span.display-name-link > a"));
        List<String> listName = new ArrayList<>();
        List<WebElement> commentDate = driver.findElements(By.xpath("//span[@class='review-date']"));
        List<String> listDate = new ArrayList<>();

        for( int i=0;i<commentText.size();i++){
            listName.add(clientName.get(i).getText());
            listComment.add(commentText.get(i).getText());
            listDate.add(commentDate.get(i).getText());
            System.out.println("Name:" + listName.get(i) + " " + "Review:" + " " + listComment.get(i) + " " + "Data:" + listDate.get(i) );
            System.out.println();



        }






        driver.quit();
    }





}
