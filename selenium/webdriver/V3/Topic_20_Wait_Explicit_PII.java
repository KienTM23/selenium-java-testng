package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_20_Wait_Explicit_PII {

    WebDriver driver;
    WebDriverWait explicitWait;

    //WebDriverWait extends FluentWait<Webdriver>
    //FluentWait<T> implements Wait<T>
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
    public void beforeClass(){
        driver = new ChromeDriver();
        //ko dung implicit wait nen tat ca cac step findElement/findElements deu co time = 0
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Ajax_Loading_Visible_Combine_Text(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //wait cho date picker dc visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
        //wait thoa man dieu kien la visible = tra ve 1 element
        WebElement selectedDateText =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
        //verify cho text dc mong doi
        Assert.assertEquals(selectedDateText.getText(),"No Selected Dates to display.");
        //wait cho ngay muon click dc clickAble
        WebElement todayLink = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='13']")));
        //click len no
        todayLink.click();
        //wait cho element visible voi text cua ngay moi chon
        selectedDateText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'Wednesday, March 13, 2024')]")));
        //verify text mong doi:
        Assert.assertEquals(selectedDateText.getText(),"Wednesday, March 13, 2024");

        //=> wait visible di kem vs text luon
    }
    @Test
    public void TC_02_Ajax_Loading_Invisible_Combine_Loading_Icon(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //wait cho date picker dc visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
        //wait thoa man dieu kien la visible = tra ve 1 element
        WebElement selectedDateText =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
        //verify cho text dc mong doi
        Assert.assertEquals(selectedDateText.getText(),"No Selected Dates to display.");
        //wait cho ngay muon click dc clickAble
        WebElement todayLink = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='13']")));
        //click len no
        todayLink.click();
        //wait cho loading icon bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));
        //wait cho element visible
        selectedDateText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")));
        //verify text mong doi:
        Assert.assertEquals(selectedDateText.getText(),"Wednesday, March 13, 2024");

    }
    @Test
    public void TC_03_Go_File() {
        driver.get("https://gofile.io/uploadFiles");

        //wait cho spinner bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#mainContent i.fa-spinner"))));
        //wait cho Add File button dc clickAble
        explicitWait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("div#rowUploadButton button.uploadButton"))));

        By uploadFileBy = By.xpath("//input[@type='file']");
        //wait cho upload file presence trong DOM
        WebElement uploadFileElement =explicitWait.until(ExpectedConditions.presenceOfElementLocated(uploadFileBy));

        //load 3 files/time
        uploadFileElement.sendKeys(autoFilePath +"\n" + benFilePath+ "\n" + kevinFilePath);

        //wait cho Upload Progress hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div#rowUploadProgress div.card-body"))));

        //wait cho nhung loading progress icon bien mat = dong nghia voi viec upload thanh cong
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));

        //wait cho Download button visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h5[text()='You files have been successfully uploadeed']"))));
        Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='You files have been successfully uploadeed']")).isDisplayed());

        driver.get(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage]")).getAttribute("href"));

        //wait cho Upload Progress hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[@class='contentName' and text()='"+autoFileName+"']/parent::a/parent::div/following-sibling::/div//button[@id='contentId-download]"))));

        //Download button
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
