package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

    WebDriver driver;
//    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void initialBrowser(){
        //mở browser
        driver = new FirefoxDriver();

        //set thời gian chờ dể tìm được element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //mở app
        driver.get("https://www.facebook.com/login/");
    }


    @Test
    public void TC_01_(){
        //HTML source code
        // Tagname - Attribute - value
        // XPath : //tagname[@attribute='value']
        // Css :     tagname[attribute='value']

        //tương tác với email address textbox
        // có 8 loai locator để tìm
        driver.findElement(By.id("email")).sendKeys("kientm@gmail.com");
        sleepInSecond(3);

        driver.findElement(By.name("pass")).sendKeys("111222");
        sleepInSecond(3);

        driver.findElement(By.className("button")).isDisplayed();
        //tagname
        int inputNumber = driver.findElements(By.tagName("input")).size();

        //Linktext
        driver.findElement(By.linkText("search")).click();

        //partial linktext
        driver.findElement(By.partialLinkText("advance")).click(); // sẽ tự search linktext có advance

        //Css
        driver.findElement(By.cssSelector("input[id='name']")).sendKeys("phone");

        driver.findElement(By.cssSelector("input[name='name']")).clear();
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("phone 1");

        driver.findElement(By.cssSelector("#name")).clear();
        driver.findElement(By.cssSelector("#name")).sendKeys("phone 2");

        //XPath
        driver.findElement(By.xpath("//input[@id='description']")).sendKeys("hello");
        driver.findElement(By.xpath("//input[@name='description']")).sendKeys("hello1");
        driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/input")).sendKeys("hello2");
    }

    @Test
    public void TC_02_(){

    }

    @AfterClass
    public void cleanBrowser()
    {
        driver.quit();
    }

    public void sleepInSecond(long second){
        try {
            Thread.sleep(second * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
