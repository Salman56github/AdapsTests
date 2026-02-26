package javaPractices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class suduko {

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://nine.websudoku.com/");
        driver.manage().window().maximize();
        int num = 1;
        Thread.sleep(3000);
        for (int row = 1; row <= 9; row++) {

            List<WebElement> getRow = driver.findElements(By.xpath("//table[@id='puzzle_grid']/tbody/tr[" + row + "]/td/input"));

            for (int cell = 1; cell <= 9; cell++) {
                WebElement getCell = driver.findElement(By.xpath("//table[@id='puzzle_grid']/tbody/tr[" + row + "]/td[" + cell + "]/input"));
                String cellValue = getCell.getAttribute("value");
                if (!isInteger(cellValue)){
                    for (int enter =1; enter<=9; enter++){
                        if (!cellValue.equalsIgnoreCase(String.valueOf(num))){

                        }
                    }
                }
            }


        }
    }
}
