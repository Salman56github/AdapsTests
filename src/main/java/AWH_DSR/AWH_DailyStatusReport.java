package AWH_DSR;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class AWH_DailyStatusReport {

    public static String ExtractFromTextFile(String PropKey) throws IOException {
        File f = new File("D:\\Perso_Docs\\AdapsAssertTest\\src\\main\\resources\\AssertsData\\DSR.txt");
        FileInputStream fin = new FileInputStream(f);
        Properties pro = new Properties();
        pro.load(fin);
        return pro.get(PropKey).toString();
    }

    public static ArrayList Todaylist;
    public static ArrayList Tomarrolist;

    public static ArrayList getTodayTask(String whichDay) throws IOException {
        if (ExtractFromTextFile(whichDay).contains(",")) {
            Todaylist = new ArrayList();
            int size = ExtractFromTextFile(whichDay).split(",").length;
            for (int i = 0; i < size; i++) {
                Todaylist.add(ExtractFromTextFile(whichDay).split(",")[i].trim());
            }
        } else {
            Todaylist = new ArrayList();
            Todaylist.add(ExtractFromTextFile(whichDay));
        }
        return Todaylist;
    }

    public static ArrayList getTomarrowTask(String whichDay) throws IOException {
        if (ExtractFromTextFile(whichDay).contains(",")) {
            Tomarrolist = new ArrayList();
            int size = ExtractFromTextFile(whichDay).split(",").length;
            for (int i = 0; i < size; i++) {
                Tomarrolist.add(ExtractFromTextFile(whichDay).split(",")[i].trim());
            }
        } else {
            Tomarrolist = new ArrayList();
            Tomarrolist.add(ExtractFromTextFile(whichDay));
        }
        return Tomarrolist;
    }

    public static void Tasks(WebDriver driver) throws IOException, InterruptedException {
        getTodayTask("WhatIDidToday");
        getTomarrowTask("WhatIPlannedForTomorrow");

        for (Object list : Todaylist) {
            driver.findElement(By.xpath("//*[@id='_r_p_']")).sendKeys(list.toString());
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id='_r_p_']/../../../button[text()='Add']")).click();
            Thread.sleep(500);
        }

        for (Object list : Tomarrolist) {
            driver.findElement(By.xpath("//*[@id='_r_r_']")).sendKeys(list.toString());
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id='_r_r_']/../../../button[text()='Add']")).click();
            Thread.sleep(500);
        }
    }

    public static void SelectDropDown(WebDriver driver, String DropDownType, String DropDownValue) {
        try {
            Thread.sleep(500);
            driver.findElement(By.xpath("//label[contains(text(),'" + DropDownType + "')]/..//div[@role='combobox']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='" + DropDownValue + "']")).click();
        } catch (Exception ignored) {

        }
    }

    public static void EnterValueIntoTextField(WebDriver driver, String SelectTimeType, String DropDownValue) {
        try {
            Thread.sleep(500);
            WebElement timeInput = driver.findElement(By.xpath("//label[contains(text(),'" + SelectTimeType + "')]/..//div/input"));
            timeInput.click();
            Thread.sleep(500);
            timeInput.clear();
            timeInput.sendKeys(DropDownValue);
        } catch (Exception ignored) {

        }
    }


    public static void main(String[] args) {
        try {
            WebDriverManager.edgedriver().setup();
            WebDriver driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get("https://adaps.ai/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign in with Microsoft']")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Sign in with Microsoft']")).click();

            String parentWindow = driver.getWindowHandle();
            Set<String> windowHandle = driver.getWindowHandles();

            for (String handle : windowHandle) {
                if (!handle.equalsIgnoreCase(parentWindow)) {
                    driver.switchTo().window(handle);
                }
            }
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//small[text()='"+ExtractFromTextFile("email")+"']")));
            if (driver.findElement(By.xpath("//small[text()='"+ExtractFromTextFile("email")+"']")).isDisplayed()) {
                Thread.sleep(3000);
                driver.findElement(By.xpath("//small[text()='"+ExtractFromTextFile("email")+"']")).click();
            }

            Thread.sleep(5000);
            driver.switchTo().window(parentWindow);
            driver.findElement(By.xpath("//h6[text()='Daily Status Report']")).click();

            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d");

            if (date.format(format).contains("0")) {
                DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd");
                System.out.println(date.format(format1));
                Thread.sleep(5000);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[normalize-space()='" + date.format(format1) + "']")));
            }
            else {

                System.out.println(date.format(format));
                Thread.sleep(5000);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[normalize-space()='" + date.format(format) + "']")));
            }

            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[text()='To *']/..//input")).sendKeys("parvathiy@adaps.com", Keys.ENTER);
            driver.findElement(By.xpath("//label[@id='_r_b_-label']/..//input")).sendKeys("charumathip@adaps.com", Keys.ENTER);
            Thread.sleep(1000);
            SelectDropDown(driver, "Time Type", "Standard");
            SelectDropDown(driver, "Full Time/Support", "Full Time");
            EnterValueIntoTextField(driver, "In Time", ExtractFromTextFile("In-Time"));
            EnterValueIntoTextField(driver, "Out Time", ExtractFromTextFile("Out-Time"));

            SelectDropDown(driver, "Project Name", ExtractFromTextFile("ProjectName"));
            EnterValueIntoTextField(driver, "Hours Assigned", ExtractFromTextFile("HoursAssigned"));
            EnterValueIntoTextField(driver, "Hours Worked", ExtractFromTextFile("HoursWorked"));
            Thread.sleep(500);
            driver.findElement(By.xpath("//button[text()='Add Project']")).click();

            Tasks(driver);
            Thread.sleep(500);
            driver.findElement(By.xpath("//button[text()='Submit DSR']")).click();

            Thread.sleep(3000);
            driver.close();

        } catch (Exception e) {
            System.out.println(e + " salman");
        }
    }
}
