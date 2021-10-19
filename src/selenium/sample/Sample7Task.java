package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
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
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }
//        tick  "Option 2"
        driver.findElement(By.cssSelector(".w3-check[value='Option 2']")).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 1']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 3']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='Option 2']")).isSelected());

//        tick  "Option 3"
        driver.findElement(By.id("vfb-6-2")).click();
//        click result
        driver.findElement(By.cssSelector(".w3-small#result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals(driver.findElement(By.id("result_checkbox")).getText(), "You selected value(s): Option 2, Option 3");
        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }
//        select  "Option 3"
        radioButtons.get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertTrue(radioButtons.get(2).isSelected());
//        select  "Option 1"
        radioButtons.get(0).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(2).isSelected());
        for (int i = 0; i < radioButtons.size(); i++) {
            if (i == 0) {
                assertTrue(radioButtons.get(i).isSelected());
            } else {
                assertFalse(radioButtons.get(i).isSelected());
            }
        }
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals(driver.findElement(By.id("result_radio")).getText(), "You selected option: Option 1");
        assertTrue(driver.findElement(By.id("result_radio")).isDisplayed());
    }

    @Test
    public void selectOption() throws Exception {
        Select dd = new Select(driver.findElement(By.id("vfb-12")));
//        select "Option 3" in Select
        dd.selectByValue("value3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", dd.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dd.selectByIndex(2);
//        check that selected option is "Option 2"
        assertEquals("value2", dd.getFirstSelectedOption().getAttribute("value"));
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals(driver.findElement(By.id("result_select")).getText(), "You selected option: Option 2");
        assertTrue(driver.findElement(By.id("result_select")).isDisplayed());

        System.out.println();
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}