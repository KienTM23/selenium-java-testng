package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Topic_14_Window_Tab {

    WebDriver driver;


    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();

    }
    //tren 1 page khi nhung page khac vao :
    //neu cung domain : Frame (it pho bien)
    // Khac domain : Iframe

    @Test
    public void TC_01_Naukri_Tab_ID() {
        //A
        driver.get("https://www.naukri.com");
        //get ID page
        String homePageWindowId = driver.getWindowHandle();
        System.out.println(driver.getCurrentUrl());
        //click vao Jobs link(trang B)
        driver.findElement(By.xpath("//[@title='Search Jobs']")).click();
        //hanh vi no tu nhay qua tran JObs
        //nhung cai driver van o trang truoc do
        switchToWindowByID(homePageWindowId);
        //sau khi switch qua
        System.out.println(driver.getCurrentUrl());
        //hien tai driver dang o B

        //switch qua A
        driver.switchTo().window(homePageWindowId);
    }

    @Test
    public void TC_02_Naukri_Tab_Title() {
        //A
        driver.get("https://www.naukri.com");
        //click vao Jobs link(trang B)
        driver.findElement(By.xpath("//[@title='Search Jobs']")).click();
        sleep(3);
        System.out.println("nauriki.com"+driver.getCurrentUrl());

        switchToWindowByLink("browse-jobs");
        System.out.println("job-browser"+driver.getCurrentUrl());

        //ve trang A
        switchToWindowByTitle("Jobs - Recruitment");
        //click register button /link
        driver.findElement(By.cssSelector("a#register_layer']")).click();

        switchToWindowByLink("registration/createAccount");
        System.out.println("jregistration/createAccount"+driver.getCurrentUrl());
    }
    @Test
    public void TC_03_Cambridge_Dictionary_Tab_Title() {
        //Home
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Dang nhap']"));
        sleep(3);

        switchToWindowByTitle("Login");

        //login
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form]//input[@name='username']/following-sibling::span")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form]//input[@name='password']/following-sibling::span")).getText(),"This field is required");

        driver.findElement(By.xpath("//form[@id='gigya-login-form]//input[@name='username']")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.xpath("//form[@id='gigya-login-form]//input[@name='password']")).sendKeys("automat00000****");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        //business no tu close di va nhay ve trang truoc do
        //driver van o trang Login

        //switch ve trang home
        switchToWindowByTitle("Cambridge Dictionary");
        sleep(3);
        //verify login thanh cong
        Assert.assertEquals(driver.findElement(By.cssSelector("header@header span.cdo-username")).getText(),"Automation FC");
    }
    //dunng cho nhieu tab/window
    public void switchToWindowByTitle(String expectedTitle){
        //lay het tat ca cac ID dang co
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            //switch vao truoc roi ms kiem tra dieu kien sau
            driver.switchTo().window(id);
            //lay title cua page do ra
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)){
                //thoa ma dk dung cai page/tab/window minh can
                break;
            }
        }
    }
    public void switchToWindowByLink(String expectedRelativeLink){
        //lay het tat ca cac ID dang co
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            //switch vao truoc roi ms kiem tra dieu kien sau
            driver.switchTo().window(id);
            //lay title cua page do ra
            String actualLink = driver.getTitle();
            if (actualLink.equals(expectedRelativeLink)){
                //thoa ma dk dung cai page/tab/window minh can
                break;
            }
        }
    }
    public void switchToWindowByID(String currentWindowID) {
        //lay het tat ca cac ID dang co
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(currentWindowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows){
            if (!runWindows.equals(parentID)){
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() ==1){
            return true;
        }else {
            return false;
        }
    }
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
