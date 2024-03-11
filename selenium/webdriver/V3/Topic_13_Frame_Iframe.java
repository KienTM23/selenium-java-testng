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

public class Topic_13_Frame_Iframe {

    WebDriver driver;



    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

    }
    //tren 1 page khi nhung page khac vao :
    //neu cung domain : Frame (it pho bien)
    // Khac domain : Iframe

    @Test
    public void TC_01_Kyna(){
        //A
        driver.get("https://kyna.vn/");
        //Switch vao frame/iframe truoc roi ms thao tac len element thuoc frame/iframe do
//        driver.switchTo().frame(0);
        //A->B
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains()@src,'facebook.com/kyna.vn']")));
        //B
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText(),"167k likes");
        //B->A
        driver.switchTo().defaultContent();
        //A->C
        //switch vao iframe chat truoc
        driver.switchTo().frame("cs_chat_iframe");
        //C
        //click vao chat de bat popup len
        driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();

        driver.findElement(By.cssSelector("input.input_name")).sendKeys("Tim cook");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0654789321");
        driver.findElement(By.cssSelector("textareap[name='messsage']")).sendKeys("Iframe");

        //C -> A
        driver.switchTo().defaultContent();

        String keyword = "Excel";
        //A
        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
        driver.findElement(By.cssSelector("button#search-button")).click();
        //verify cource name chua tu khoa vua nhap
        List<WebElement> listCourseName = driver.findElements(By.cssSelector("div.content>h4"));
        for (WebElement cource  :listCourseName){
            Assert.assertTrue(cource.getText().contains("Excel"));
        }
     }

    @Test
    public void TC_02_Blog(){
        //A (automationfc.com)
        driver.get("https://automationfc.com/2020/02/18/tranning-online-automation-testing/");

        //A -> B (Youtube.com)
        driver.switchTo().frame("video-2679-1_youtube_iframe");

        //B
        driver.findElement(By.cssSelector("button.ytp-large-play-button")).click();
        //B -> A
        driver.switchTo().defaultContent();
        // A -> C(Facebook.com)
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page iframe")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText(),"3000 likes");
    }

    @Test
    public void TC_03_HDFC(){
        //A
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        //A-> B
        driver.switchTo().frame("login_page");
        //B
        driver.findElement(By.name("fldLoginUserid")).sendKeys("automationtest");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        //
        Assert.assertTrue(driver.findElement(By.id("fldPasswordDisId")).isDisplayed());
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
