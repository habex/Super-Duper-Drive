import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;


public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
       /* WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();*/

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/animal");

        WebElement inputField = driver.findElement(By.id("animalText"));
        inputField.sendKeys("Dog");
        inputField = driver.findElement(By.id("adjective"));
        inputField.sendKeys("Doggy");

        for (int i = 0; i <5; i++) {
            //Thread.sleep(5000);
            System.out.println("Sumbit");
            inputField.submit();

            List<WebElement> trainingResult =
                    driver.findElements(By.className("trainingMessage"));
            System.out.println("trainingResults.size() = " + trainingResult.size());
        }

        // Then get the element by the class conclusionMessage and print it
        WebElement conclusionResult = driver.findElement(By.className("conclusionMessage"));
        System.out.println("conclusionResult.getText() = " + conclusionResult.getText());

        Thread.sleep(5000);
        driver.quit();
    }
}
