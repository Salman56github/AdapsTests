package Code.table;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Code.DailyStatusReport.*;

public class checkClass {

    public static void waitForElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void WebForm() throws InterruptedException, IOException {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://forms.cloud.microsoft/pages/responsepage.aspx?id=rBgnOZPUk0C5R-xLfb00T6Hq8NioNM5LkpPacy6QMW9UOElTNVhGSU5WTU1QUzcyMEsyNUlXTE9YUy4u&route=shorturl");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//small[text()='salmanm@adaps.com']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//small[text()='salmanm@adaps.com']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='Start now'])[2]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[text()='Start now'])[2]")).click();
        Thread.sleep(3000);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        driver.findElement(By.xpath("//input[@id='DatePicker0-label']")).sendKeys(date.format(pattern));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='Employee ID']/../../../../../div//input")).sendKeys("AIN192");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'In-Time')]/../../../../../div//input")).sendKeys("8:00");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Out-Time')]/../../../../../div//input")).sendKeys("17:00");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Work / Leave ')]/../../../../../div//input[@value='Working']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Project Name')]/../../../../../div//div[@aria-haspopup='listbox']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='HD Functional Testing']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Full-Time')]/../../../../../div//div[@aria-haspopup='listbox']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='Full-Time']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Hours Assigned')]/../../../../../div//div[@aria-haspopup='listbox']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='8']")).click();
        Thread.sleep(1000);
        String tempToday = "";
        for (int i = 0; i < getTodayTask("WhatIDidToday").size(); i++) {
            tempToday = tempToday + getTodayTask("WhatIDidToday").get(i) + "\n";
        }
        driver.findElement(By.xpath("//span[contains(text(),'Task 1')]/../../../../../div//textarea")).sendKeys(tempToday);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Hours Worked')]/../../../../../div//div[@aria-haspopup='listbox']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//span[text()='8'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Task 1 Status')]/../../../../../div//span[text()='"+ExtractFromTextFile("Status")+"']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        Thread.sleep(1000);
        String tempTomarro = "";
        for (int i = 0; i < getTomarrowTask("WhatIPlannedForTomorrow").size(); i++) {
            tempTomarro = tempTomarro + getTomarrowTask("WhatIPlannedForTomorrow").get(i) + "\n";
        }
        driver.findElement(By.xpath("//span[contains(text(),'Tasks for tomorrow')]/../../../../../div//textarea")).sendKeys(tempTomarro);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//button[text()='Submit']")).click();

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        WebForm();
    }
}
