package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Actions_PII {

    WebDriver driver;
    Actions action;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

        action = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofMillis(2000));
    }


    @Test
    public void TC_01_Right_Click(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();

        //hover vao paste
        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        //verify paste dc hover
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover")).isDisplayed());
        //click vao paste
        action.click(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        //acceprt alert
        explicitWait.until(ExpectedConditions.alertIsPresent()).accept();
     }

    @Test
    public void TC_02_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));

        action.doubleClick(doubleClick).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }
    @Test
    public void TC_03_Drag_And_Drop_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");


        WebElement smallCircle = driver.findElement(By.id("draggable"));
        WebElement bigCircle = driver.findElement(By.id("droptarget"));

        action.dragAndDrop(smallCircle,bigCircle).perform();
        Assert.assertEquals(bigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
    }
    @Test
    public void TC_04_Drag_And_Drop_HTML5_Css(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement squareA = driver.findElement(By.id("column-a"));
        WebElement squareB = driver.findElement(By.id("column-b"));
    }
    @Test
    public void TC_05_Drag_And_Drop_HTML5_Xpath(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");

    }
    @AfterClass
    public void cleanBrowser()
    {
        driver.quit();
    }

}
