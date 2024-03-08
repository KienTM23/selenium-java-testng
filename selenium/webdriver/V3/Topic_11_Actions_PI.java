package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_11_Actions_PI {

    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

        action = new Actions(driver);
    }


    @Test
    public void TC_01_Hover(){
        //ket hop nhieu ham vs nhau (ko can dung)
//        actions.clickAndHold().moveToElement(driver.findElement(By.id(""))).build();
        //
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement yourAgeTextbox = driver.findElement(By.id("age"));
        //hover chuot vao textbox
        action.moveToElement(yourAgeTextbox).perform();

        Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),"We ask for you age");
     }

    @Test
    public void TC_02_Hover(){
        driver.get("https://www.myntra.com");

        action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();

        action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")),"Kids Home Bath");
    }
    @Test
    public void TC_03_Click_And_Hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //khai bao va luu tru tat ca 12 elements
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        //1-> 4: click and hold vao 1 -> hover toi 4 -> nha chuot trai -> thuc thi cau lenh
        action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),4);
    }
    @Test
    public void TC_04_Click_And_Hold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //khai bao va luu tru tat ca 12 elements
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        //1, 5, 12: nhan phim ctrl xuong(chua nha ra)-> click 1,5,12 -> thuc thi cau lenh -> nha phim ctrl
        action.keyDown(Keys.CONTROL).perform();
        action.click(allNumbers.get(0)).click(allNumbers.get(4)).click(allNumbers.get(11)).perform();
        action.keyUp(Keys.CONTROL).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),3);
    }
    @AfterClass
    public void cleanBrowser()
    {
        driver.quit();
    }

}
