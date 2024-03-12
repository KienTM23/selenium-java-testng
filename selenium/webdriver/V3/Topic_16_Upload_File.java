package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

public class Topic_16_Upload_File {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    //3 bien cho file
    String autoFileName = "autotest.png";
    String benFileName= "ben.PNG";
    String kevinFileName = "kevin.PNG";

    //File.separator <==> \\ or / : tu tim cho window or Mac
    String uploadFileFolderPath = projectPath+ File.separator +"uploadFiles" + File.separator;
    String autoFilePath = uploadFileFolderPath + autoFileName;
    String benFilePath = uploadFileFolderPath + benFileName;
    String kevinFilePath = uploadFileFolderPath + kevinFileName;


    @BeforeClass
    public void initialBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
    }

    @Test
    public void TC_01_One_File_One_Time() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //selenium sendkey method
        //ko co click vao button add file de bat open file dialog len, chi sendkey dc input

        //Cach 1:
        WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        uploadFile.sendKeys(autoFilePath);
        uploadFile.sendKeys(benFilePath);
        uploadFile.sendKeys(kevinFilePath);
        // => ko dung cach 1, vi no bi update cho element
        //Cach 2:
        By uploadFileBy = By.xpath("//input[@type='file']");
        driver.findElement(uploadFileBy).sendKeys(autoFilePath);
        driver.findElement(uploadFileBy).sendKeys(benFilePath);
        driver.findElement(uploadFileBy).sendKeys(kevinFilePath);
        sleep(3);
        //verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+autoFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+benFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+kevinFileName+"']")).isDisplayed());

        //click to Upload button at each file
        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement button: uploadButtons){
            button.click();
            sleep(2);
        }

        //verify file uploaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+autoFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+benFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+kevinFileName+"']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File_One_Time() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFileBy = By.xpath("//input[@type='file']");
        //load 3 files/time
        driver.findElement(uploadFileBy).sendKeys(autoFilePath +"\n" + benFilePath+ "\n" + kevinFilePath);
        sleep(3);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+autoFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+benFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+kevinFileName+"']")).isDisplayed());

        //click to Upload button at each file
        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement button: uploadButtons){
            button.click();
            sleep(2);
        }

        //verify file uploaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+autoFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+benFileName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+kevinFileName+"']")).isDisplayed());
    }

    @Test
    public void TC_03_Go_File() {
        driver.get("https://gofile.io/uploadFiles");

        By uploadFileBy = By.xpath("//input[@type='file']");
        //load 3 files/time
        driver.findElement(uploadFileBy).sendKeys(autoFilePath +"\n" + benFilePath+ "\n" + kevinFilePath);
        sleep(3);

        Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='You files have been successfully uploadeed']")).isDisplayed());

        driver.get(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage]")).getAttribute("href"));

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+autoFileName+"']/parent::a/parent::div/following-sibling::/div//button[@id='contentId-download]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+benFileName+"']/parent::a/parent::div/following-sibling::/div//button[@id='contentId-download]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+kevinFileName+"']/parent::a/parent::div/following-sibling::/div//button[@id='contentId-download]")).isDisplayed());
        //play button
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+autoFileName+"']/parent::a/parent::div/following-sibling::/div//button[contains(@class,'contentPlay')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+benFileName+"']/parent::a/parent::div/following-sibling::/div//button[contains(@class,'contentPlay')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+kevinFileName+"']/parent::a/parent::div/following-sibling::/div//button[contains(@class,'contentPlay')]")).isDisplayed());
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
