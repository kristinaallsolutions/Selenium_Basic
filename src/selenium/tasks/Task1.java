package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;

import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement txt1 = driver.findElement(By.id("numb"));
        txt1.sendKeys("qwdjknqwe");
        WebElement btn = driver.findElement(By.className("w3-orange"));
        btn.click();

        WebElement err = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", err.getText());



    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement txt1 = driver.findElement(By.id("numb"));
        txt1.sendKeys("2");
        WebElement btn = driver.findElement(By.className("w3-orange"));
        btn.click();

        WebElement err = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too small", err.getText());

    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
        WebElement txt1 = driver.findElement(By.id("numb"));
        txt1.sendKeys("111");
        WebElement btn = driver.findElement(By.className("w3-orange"));
        btn.click();

        WebElement err = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", err.getText());
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement txt1 = driver.findElement(By.id("numb"));
        txt1.sendKeys("81");
        WebElement btn = driver.findElement(By.className("w3-orange"));
        btn.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 81 is 9.00", alert.getText());
        alert.accept();
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement txt1 = driver.findElement(By.id("numb"));
        txt1.sendKeys("55");
        WebElement btn = driver.findElement(By.className("w3-orange"));
        btn.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 55 is 7.42", alert.getText());
        alert.accept();
    }
}