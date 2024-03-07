package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Topic_07_Defaut_Dropdown {

    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    Actions actions;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();

        //JavascriptExecutor/ WebDriverWait/Action...
        jsExecutor = (JavascriptExecutor) driver;
//        explicitWait =  new WebDriverWait(driver,30);
        actions = new Actions(driver);
    }


    //    @Test
    public void TC_01_Rode() {
        driver.get("https://www.rode.com/wheretobuy");
        //khoi tao khi su dung (element xuat hien)
        //khoi tao select de thao tac vs element
        select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
        //index
//        select.selectByIndex(2);
        //value
//        select.selectByValue("Argentina");
        //(han che dung/ko dung index vs value )
        //text
//        select.selectByVisibleText("Belarus");
        //ko support multiple select
        Assert.assertFalse(select.isMultiple());
        //select gia tri vietnam
        select.selectByVisibleText("Vietnam");
        //verify vietnam selected success
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

        driver.findElement(By.cssSelector("input#search_loc_submit")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "32");

        List<WebElement> storeName = driver.findElements(By.cssSelector("div#search_results div.store_name"));
        //verify tong so luong store name = 32
        Assert.assertEquals(storeName.size(), 32);

        for (WebElement store : storeName) {
            System.out.println(store.getText());
        }
    }

    @Test
    public void TC_02_NopCommerce() {
        String firstName = "Kevin";
        String lastName = "Ken";
        String date = "8";
        String month = "May";
        String year = "2024";
        String emailAdress = "kevin" + getRandomNumber() + "@gmail.com";
        String password = "215463";
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        //date
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        select.selectByVisibleText(date);
        //month
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText(month);
        //year
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText(year);

        driver.findElement(By.id("Email")).sendKeys(emailAdress);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector("a.ico-account")).click();

        //page html render lai
        //verify
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);

        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);

        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAdress);

    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    @AfterClass
    public void cleanBrowser() {
//        driver.quit();
    }

}
