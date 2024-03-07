package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_08_Custom_Dropdown {

    WebDriver driver;

    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        //wait cho cac trang thai cua element : visible: phai thay dc tren giao dien bao gom (presence)/presence/invisible/ staleness
        explicitWait = new WebDriverWait(driver, Duration.ofMillis(20));
        //
        jsExecutor = (JavascriptExecutor) driver;
        //wait cho viec tim element
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }


//    @Test
    public void TC_01_JQuerry() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//        driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-icon")).click();
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
//        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
        selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","1");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"1");
        selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","15");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"15");
        selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","19");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"19");


    }

    @Test
    public void TC_02_Angular() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        //example test
        selectItemInCustomDropdownList("i.dropdown","div.item>span.text","Matt");
        String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='province'] span .ng-value label \").innerText");
        Assert.assertEquals(actualText,"Matt");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public void selectItemInCustomDropdownList(String parentLocator,String childLocator, String expectedTextItem) {
        //        + Step 1: click vào 1 element cho nó xổ hết các item
        driver.findElement(By.cssSelector(parentLocator)).click();
        sleep(2);
//        + sttep 2: chờ cho các item load hết ra thành công (lay dc locator chua het cac item)
//        + wait 8 cai
//        + presence 19 cai
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
//        + step 3: tìm item cần chọn

        //lay het tat ca element (item) ra sau do duyet qua tung item
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
        // duyet qua tung item gettext cua item ra
        for (WebElement item : allItems) {
            String actualText = item.getText();
            //neu text = text minh muon (item can click vao)
            if (actualText.equals(expectedTextItem)) {
//              .B1: item cần chọn nằm trong vùng nhìn thấy thì ko còn scroll tìm tiếp
//              .B2: ngược lại thì cuộn chuột đến item đó
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);",item);
                sleep(2);
//        + step 4: click vào item
                item.click();
                sleep(2);
                break;
            }
        }
    }

    public void enterToCustomDropdownList(String parentLocator,String childLocator, String expectedTextItem) {
        //        + Step 1: phai lay den the input (textbox) de sendkey vao
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
        sleep(2);
//        + sttep 2: chờ cho các item load hết ra thành công (lay dc locator chua het cac item)
//       +Locator chua het tat ca cac item
//        + Locator phai den node cuoi cung chua text
//        + wait 8 cai
//        + presence 19 cai
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
//        + step 3: tìm item cần chọn

        //lay het tat ca element (item) ra sau do duyet qua tung item
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
        // duyet qua tung item gettext cua item ra
        for (WebElement item : allItems) {
            String actualText = item.getText();
            //neu text = text minh muon (item can click vao)
            if (actualText.equals(expectedTextItem)) {
//              .B1: item cần chọn nằm trong vùng nhìn thấy thì ko còn scroll tìm tiếp
//              .B2: ngược lại thì cuộn chuột đến item đó
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);",item);
                sleep(2);
//        + step 4: click vào item
                item.click();
                sleep(2);
                break;
            }
        }
    }
    public void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
