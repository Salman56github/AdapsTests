//package javaPractices;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.Set;
//
//public class Dummy {
//    public static void main(String[] args) {
//
//        WebDriverManager.chromeDriver.setup();
//        WebDriver driver = new ChromeDriver();
//        driver.goTo("www.loginPage.com");
//
//        Assert.assertEqual(driver.getTitle(),"Online Banking Login");
//
//        driver.findElement(By.xpath("//input[@placeholder='username']")).sendKeys("admin");
//
//        driver.findElement(By.xpath("//input[@placeholder='username']")).sendKeys("admin@123");
//
//        driver.findElement(By.xpath("//button[@type='button]")).click();
//
//        driver.switchToAlert().accept();
//
//        String parentwindow = driver.getWindowHandle();
//
//        driver.findElement(By.xpath("//input[@id='account details']")).click();
//
//        Set<String> windowHandles = driver.getWindowHandles();
//
//        for(String handle : windowHandles){
//
//            if(handle != parentwindow ){
//
//                driver.switchTo().Window(handle);
//
//                String accountValue = driver.findElement(By.xpath("//span[@name='account balance']")).getText();
//
//            }
//
//            driver.switchToDefaultWindow();
//
//    }
//}
