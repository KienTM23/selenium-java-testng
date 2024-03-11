package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_12_Random_Popup {

    WebDriver driver;
    Actions action;


    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

        action = new Actions(driver);
        driver.get("https://ngoaingu24h.vn/");
    }


    @Test
    public void TC_01_Random_In_DOM(){
        //step 1
        driver.get("https://vnk.edu.vn/");

        //step 2
        //neu popup hien thi thi co the thao tac vs popup roi close no -> qua step tiep theo
        if (driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()){
            //close popup
            driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
            sleep(2);
            //expected popup ko con hien thi nua
            Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
        }
        //neu ko hien thi thi qua step tiep theo luon
        //step 3 : can click link lien he
        driver.findElement(By.xpath("//a[@title='Liên he']")).click();
        //step 4: verify trang lien he thanh cong
        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());
     }

    @Test
    public void TC_02_Random_Not_In_DOM(){
        //step 1
        driver.get("https://dehieu.vn/");
        //lan dau khi mo page len thi popup se co trong DOM nhung chua hien thi
        //step 2
        List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
        //neu popup hien thi thi co the thao tac vs popup roi close no -> qua step tiep theo
        if (popupContent.size() > 0){
            //thao tac vs popup
            driver.findElement(By.cssSelector("input#popup-name")).sendKeys("hkt");
            driver.findElement(By.cssSelector("input#popup-email")).sendKeys("hkt@gmail.com");
            driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987654321");
            //close popup
            driver.findElement(By.cssSelector("button#close-popup")).click();

            //ko dung cach nay de verify popup ko hien thi nua
            //khi ko hien thi nua(close) thi element ko con trong DOM, ko con trong DOM thi ko findElement
            //expected popup ko con hien thi nua
//            Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
            //
            Assert.assertEquals(driver.findElements(By.cssSelector("div.popup-content")),0);
        }else {
            //neu nhu setting cho app vao ngay xxx nao do se hien popup de chay chuong trinh marketing - co popup
            //sau thoi gian nay setting ko hien thi popup nua: (ko co trong DOM ngay tu dau luon)
        }
        //neu ko hien thi thi qua step tiep theo luon
        //step 3 : can click link lien he
        driver.findElement(By.xpath("//a[text()='Tat ca khoa hoc']")).click();
        //step 4: verify trang search cource thanh cong
        Assert.assertTrue(driver.findElement(By.cssSelector("input#search-courses")).isDisplayed());
    }


    @AfterClass
    public void cleanBrowser()
    {
        driver.quit();
    }
    public void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
