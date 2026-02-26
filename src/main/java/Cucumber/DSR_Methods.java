package Cucumber;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

public class DSR_Methods {


    WebDriver driver;
    WebDriverWait wait;

    public String ExtractFromDsrPropertyFile(String PropKey) {
        try {
            File f = new File("D:\\Perso_Docs\\AdapsAssertTest\\src\\main\\resources\\AssertsData\\DSR.txt");
            FileInputStream fin = new FileInputStream(f);
            Properties pro = new Properties();
            pro.load(fin);
            return pro.get(PropKey).toString();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList Todaylist;
    public static ArrayList Tomarrolist;

    public ArrayList getTodayTask(String whichDay) throws IOException {
        if (ExtractFromDsrPropertyFile(whichDay).contains(",")) {
            Todaylist = new ArrayList();
            int size = ExtractFromDsrPropertyFile(whichDay).split(",").length;
            for (int i = 0; i < size; i++) {
                Todaylist.add(ExtractFromDsrPropertyFile(whichDay).split(",")[i].trim());
            }
        } else {
            Todaylist = new ArrayList();
            Todaylist.add(ExtractFromDsrPropertyFile(whichDay));
        }
        return Todaylist;
    }

    public ArrayList getTomarrowTask(String whichDay) throws IOException {
        if (ExtractFromDsrPropertyFile(whichDay).contains(",")) {
            Tomarrolist = new ArrayList();
            int size = ExtractFromDsrPropertyFile(whichDay).split(",").length;
            for (int i = 0; i < size; i++) {
                Tomarrolist.add(ExtractFromDsrPropertyFile(whichDay).split(",")[i].trim());
            }
        } else {
            Tomarrolist = new ArrayList();
            Tomarrolist.add(ExtractFromDsrPropertyFile(whichDay));
        }
        return Tomarrolist;
    }


    public void GetWebForm() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://forms.cloud.microsoft/pages/responsepage.aspx?id=rBgnOZPUk0C5R-xLfb00T6Hq8NioNM5LkpPacy6QMW9UOElTNVhGSU5WTU1QUzcyMEsyNUlXTE9YUy4u&route=shorturl");
    }

    public void WaitForElementToBeClickable(By by) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void CheckMultipleEmail(String email) throws InterruptedException {
        Thread.sleep(15000);
        WebElement element = driver.findElement(By.xpath("//div[text()='Pick an account']"));
        if (element.isDisplayed()) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//small[text()='" + email + "']")).click();
        }
    }

    public void WaitForElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void WaitForElement(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement findElement_By(By by) {
        return driver.findElement(by);
    }

    public void ClickElement(WebElement element) {
        element.click();
    }

    public void MoveToElement(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).perform();
    }

    public String DateFormatter(String format) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(format);
        return date.format(pattern);
    }

    public void StartNow() {
        By button = By.xpath("(//div[text()='Start now'])[2]");
        WaitForElementToBeClickable(button);
        ClickElement(findElement_By(button));
    }

    public void Fill(String FieldName, String text) {
        By element = By.xpath("//span[contains(text(),'" + FieldName + "')]/../../../../../div//input");
        WaitForElementToBeClickable(element);
        findElement_By(element).sendKeys(text);
    }

    public void FillTextArea(String FieldName, String text){
        By element = By.xpath("//span[contains(text(),'" + FieldName + "')]/../../../../../div//textarea");
        WaitForElementToBeClickable(element);
        findElement_By(element).sendKeys(text);
    }

    public void SelectWorkingDay(String text) {
        By element = By.xpath("//span[contains(text(),'Work / Leave ')]/../../../../../div//input[@value='" + text + "']");
        MoveToElement(findElement_By(element));
        findElement_By(element).click();
    }



    public void FillDropDown(String DD_Name, String text) {
        By element = By.xpath("//span[contains(text(),'" + DD_Name + "')]/../../../../../div//div[@aria-haspopup='listbox']");
        ClickElement(findElement_By(element));
        WebElement opt = findElement_By(By.xpath("//span[text()='" + text + "']"));
        MoveToElement(opt);
        ClickElement(opt);
    }

    public void SelectStatus(String DDName, String Value){
        By element = By.xpath("//span[contains(text(),'"+DDName+"')]/../../../../../div//input[@value='"+Value+"']");
        MoveToElement(findElement_By(element));
        findElement_By(element).click();
    }


}
