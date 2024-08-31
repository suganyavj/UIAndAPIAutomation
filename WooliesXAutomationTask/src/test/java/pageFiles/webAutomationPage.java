package pageFiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class webAutomationPage {

    WebDriver driver;
    public void launch()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        System.setProperty("webdriver.chrome.driver","//Users//Suganya//Desktop//WooliesXAutomationTask//src//chromedriver");
//        driver = new ChromeDriver();
    }
    public void get_cookie_data() throws InterruptedException {
       driver.get("https://www.google.com/");
       Thread.sleep(5);
       Set<Cookie> cookies =  driver.manage().getCookies();
        System.out.println("Size: " + cookies.size());
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "\n" + cookie.getPath()
                    + "\n" + cookie.getDomain() + "\n" + cookie.getValue()
                    + "\n" + cookie.getExpiry());
        }
        //driver.quit();
    }

    public void login() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(2);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2);
        driver.findElement(By.id("login-button")).click();
    }

    public void getNames(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getText());
        }
        String salary = driver.findElement(By.xpath("//div[text()='Cierra']/following-sibling::div[4]")).getText();
        System.out.println("Salary:"+salary);
    }
    public void getSalary() throws InterruptedException {
        driver.get("https://demoqa.com/webtables");
        Thread.sleep(2);
        String salary = driver.findElement(By.xpath("//div[text()='Cierra']/following-sibling::div[4]")).getText();
        System.out.println("Salary:"+salary);
    }

    public void alerts() throws InterruptedException {

        driver.get("https://demoqa.com/alerts");
        driver.manage().window().maximize();
        Thread.sleep(10);
        WebElement we = driver.findElement(By.xpath("//button[@id='alertButton']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", we);

        //driver.switchTo().frame("google_ads_iframe_/21849154601,22343295815/Ad.Plus-970x250-1_0");
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='alertButton']")));


        //driver.findElement(By.xpath("//button[@id='alertButton']")).click();
        Thread.sleep(500);
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Thread.sleep(500);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='confirmButton']")));
        WebElement we1 = driver.findElement(By.xpath("//button[@id='confirmButton']"));
        js.executeScript("arguments[0].click();", we1);
        Thread.sleep(500);
        Alert alert1 = driver.switchTo().alert();
        Thread.sleep(500);
        alert1.dismiss();

        Thread.sleep(500);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='promtButton']")));
        WebElement we2 = driver.findElement(By.xpath("//button[@id='promtButton']"));
        js.executeScript("arguments[0].click();", we2);
        Thread.sleep(500);
        Alert alert2 = driver.switchTo().alert();
        Thread.sleep(500);
        alert2.sendKeys("Hello");
        Thread.sleep(1000);
        alert2.accept();
        Thread.sleep(500);
        String text = driver.findElement(By.xpath("//span[contains(text(),'You entered')]")).getText();
        System.out.println(text);
    }

    public void dragAndDrop() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        driver.manage().window().maximize();
        Thread.sleep(500);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        js.executeScript("window.scrollBy(0,400)");

      WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']")) ;
      WebElement drop = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']//p"));
      Actions action = new Actions(driver);
      action.dragAndDrop(drag,drop).build().perform();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']//p[contains(text(),'Dropped')]")));
      String text = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']//p[contains(text(),'Dropped')]")).getText();
      System.out.println(text);
    }
}
