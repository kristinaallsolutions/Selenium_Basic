package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void enterNumberWithInt() throws Exception {
        WebElement numb = driver.findElement(By.id("number"));
        String newNumber = "123";
        String originalNumber = "5";
        WebElement resultsButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResultsButton = driver.findElement(By.id("clear_result_button_number"));

//         TODO:
//        enter a number under "Number"
        numb.clear();
        numb.sendKeys(newNumber);

//        check that button is not clickable "Clear Result"
        assertFalse(clearResultsButton.isEnabled());

//        check that text is not displayed
        WebElement text = driver.findElement(By.id("result_number"));
        assertFalse(text.isDisplayed());

//        click on "Result" button
        resultsButton.click();

//        check that text is displayed
        assertTrue(text.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals(text.getText(), "You entered number: \"" + newNumber + "\"");

//        check that the button "Clear Result" is clickable now
        assertTrue(clearResultsButton.isEnabled());

//        click on "Clear Result"
        clearResultsButton.click();

//        check that the text is still (""), but it is not displayed
        assertEquals(text.getText(), "");
        assertFalse(text.isDisplayed());
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is still (""), but it is not displayed
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
//        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));
//        verify that current url is homepage
        assertEquals("https://kristinek.github.io/site/", driver.getCurrentUrl());
    }
}