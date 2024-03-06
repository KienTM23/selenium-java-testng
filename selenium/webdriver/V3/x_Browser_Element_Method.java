package webdriver.V3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class x_Browser_Element_Method {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }
    @Test
    public void TC_01_Browser(){
        //action len browser
        //open browser
        //open url
        driver.get("https://www.facebook.com/"); //hay dung
        //close browser
        driver.close(); // đóng tab hien tai nếu  có nhieu tab //it dung
        //ket hơợp vs bài Webdriver API - window và tabs
        driver.quit();  // đóng browser ko quan tam bao nhieu tab //hay dung
        //argument : tham so/doi so

        //tim 1 element tren page
        //tra ve data type la webelement
        WebElement emailTextbox = driver.findElement(By.id("email")); //hay dung

        //tìm nhiều hơn 1 element trên page
        List<WebElement> links = driver.findElements(By.xpath("//a")); //hay dung

        //trả về url của page hiện taại
        String homePageUrl = driver.getCurrentUrl(); //it dung

        //verify tuyet doi
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        //verify tương đối 1 giá trị nào đó co trong trang
        String homePageSource = driver.getPageSource(); //lay source page
        Assert.assertTrue(homePageSource.contains("welcome to our store"));

        //lay ra/tra ve title cua page hien tai
        driver.getTitle(); //it dung

        //webdrive api -windows/tabs
        // tra ve 1 id cua tab hien tai
        driver.getWindowHandle(); //it dung
        //tra ve id cua tat ca cac tab
        driver.getWindowHandles(); //it dung

        //xu ly cookie (framework)
        driver.manage().getCookies(); //it dung

        //lay log cua browser ra(framework)
        driver.manage().logs();
        //time cua viec tim element
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); //hay dung
        driver.manage().timeouts().implicitlyWait(20000,TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        //time de doan async script dc thuc thi (Javascript excutor)
        driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
        //nhu phim f11
        driver.manage().window().fullscreen();
        //hay dung maximize hon (end user)
        driver.manage().window().maximize(); //it dung

        //test GUI

        //lay ra vi tri cua browser so vs do phan giai man hinh
        Point browserPosition = driver.manage().window().getPosition();
        //set vi tri cua browser tai diem 0 x 250
        driver.manage().window().setPosition(new Point(0,250));
        //lay ra chieu rong, chieu cao cua browser
        Dimension browserSize =  driver.manage().window().getSize();
        //set browser mo vs kich thuoc nao
        //test responsive
        driver.manage().window().setSize(new Dimension(1366,768));

        //
        driver.navigate().back();
        //chuyen tiep
        driver.navigate().forward();
        //tai laij trang
        driver.navigate().refresh();
        //
        driver.navigate().to("https://www.facebook.com/"); // luu dc history tot hon

        //dung nhieu
        //bai xu l authentication alert
        driver.switchTo().alert(); //hay dung
        //
        driver.switchTo().frame(1);//hay dung

        driver.switchTo().window("");//hay dung
        //Refresh/ Back/ Forward
        //Maximize/ Minimize/ Fullscreen

        //láy dữ liệu ra từ browser: trả về 1 kiểu dữ liêu nào đó đê lưu tru lại/nắm giu du liệu đó
        //get url/ get title/ get source page / get position /get location...
        //isDisplay(), isEnabled/ isSelected/ isMultiple : Built-in/
        //User-define: ...tu viet ham...

        //các method dêể thao tác với browser thoong qua bien driver
        //by là kiểu class
        driver.findElement(By.xpath(""));
    }
    @Test
    public void TC_02_Element(){
        //hàm thao tác vs element là thông qua biến element
    }
}
