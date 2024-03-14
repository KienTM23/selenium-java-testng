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

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_18_Wait_FindElement_FindElements {

    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    //3 bien cho file



    @BeforeClass
    public void initialBrowser() {
       driver = new ChromeDriver();
//       explicitWait = new WebDriverWait(driver, Duration.ofMillis(15));
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //ca 2 ham nay(FindElement/FindElements) deu apply timeout cua implicitWait
    //implicitWait chi ap dung cho FindElement/FindElements
    //Co set timeout dung timeout
    //ko set timeout = 0

    //Tim thay element : ko can phai cho het tong thoi gian
    //ko tim thay element :
    //1 - Het ca thoi gian la ko thay
    //2 - Chua het thoi gian lai tim thay
    @Test
    public void TC_01_Find_Element() {
        //Single Element
//        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
        driver.get("https://www.facebook.com/");
        // 1 - tim thay 1 element
        // tim thay nen ko can cho het tong thoi gian
        driver.findElement(By.cssSelector("input#email"));
        // 2 - Tim thay nhieu hon 1 element
        //luon luon thao tac element dau tien trong DOM/HTML
        // tim thay nen ko can cho het tong thoi gian
        driver.findElement(By.cssSelector("input#email,input#pass"));
        // 3 - Ko tim thay element nao het
        // Cu moi nua s se tim lai 1 lan
        //tim cho den khi nao het tong timeout
        //sau khi het timeout danh fail testcase ngay tai vi tri step nay luon, ko chay qua step tiep theo nua
        // Throw ra exeption: NoSuchElement
        driver.findElement(By.cssSelector("input#selenium"));
    }
    @Test
    public void TC_02_Find_Elements() {
        driver.navigate().refresh();

        List<WebElement> elements = null;
        //Multiple  Element (1 - n)
//        List<WebElement> textboxes = driver.findElements(By.cssSelector("input"));
        // 1 - tim thay 1 element
        //luu vao list vs 1 element
        elements = driver.findElements(By.cssSelector("input#email"));
        System.out.println("Tong so luong element = "+elements.size());
        // 2 - Tim thay nhieu hon 1 element
        elements = driver.findElements(By.cssSelector("input#email,input#pass"));
        System.out.println("Tong so luong element = "+elements.size());
        //luu vao list vs nhieu element
        // 3 - Ko tim thay element nao het
        // Cu moi nua s se tim lai 1 lan
        //tim cho den khi nao het tong timeout
        //khong danh fail testcasse, van chay tiep cac step tiep theo
        //chi luu vao list la 0 element
        elements = driver.findElements(By.cssSelector("input#selenium"));
        System.out.println("Tong so luong element = "+elements.size());

        Assert.assertEquals(elements.size(),0);
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
