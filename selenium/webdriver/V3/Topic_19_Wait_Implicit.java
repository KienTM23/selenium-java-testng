package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_19_Wait_Implicit {

    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    //3 bien cho file


//    @BeforeClass
//    public void initialBrowser() {
//        //chay dau tien de khoi tao browser
//        driver = new ChromeDriver();
//        //1
//        driver.get("https://opensource-demo.orangehrmlive.com/");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        //2
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/");
//        //3
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://opensource-demo.orangehrmlive.com/");
//        //4
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        //4 cai nhu nhau deu Pass, 3 cai step nay ko lien quan den nhau
//    }
    //khi set implicitWait roi thi no se apply cho tat ca cac step FindElement/FindElements tu do tro di
    //beforeClass chay 1 lan dau tien cho tat ca cac testcase
    //beformeMethod chay truoc moi testcase
    //sau nay khi build framework : driver sẽ chaạy từ class nay qua class khác thi chi can set implicitWait 1 lần duy nhat
    //
//    @BeforeMethod
//    public void beforeMethod(){
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
    By startButton = By.cssSelector("div#start>button");
    By loadingIcon = By.cssSelector("div#loading");
    By helloText = By.cssSelector("div#finish>h4");
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

    }
    //Bussiness cua cac step
    //1-mo page ra
    //2-click start button
    //3-loading icon hien thi va bien mat sau 5s
    //4-sau 5s thi cai hello text hien thi
    //bien mat va hien thi (Invisible va visible (explicit wait))
    //implicit: ngam dinh nen ko co di vs trang thai nao cua element ca
    //find dc element trong khoang time nao do ko thoi
    //wait ngam dinh: neu nhu ko hieu co che wait cua implicit se rat kho hieu tai sao cac testcase kia pass/fail
    @Test
    public void TC_01_Not_Set(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(startButton).click();

        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_02_Less_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(startButton).click();

        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_03_More_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        driver.findElement(startButton).click();

        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_04_More_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(startButton).click();

        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_05_More_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.findElement(startButton).click();

        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
//    @Test
//    public void TC_01_Find_Element() {//test method
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        //dc gan lai gia tri moi cho cac step tiep theo , dung tiep cho cac case tiep theo
//        //Textbox
//        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
//        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
//        //button
//        driver.findElement(By.id("btnLogin")).click();
//
//        Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
//
//        driver.get("htttps://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
//
//        Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
//    }


    @AfterClass
    public void cleanBrowser() {
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
