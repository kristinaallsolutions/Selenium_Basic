package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.FeedBackPage;
import selenium.pages.FeedBackPageSubmitted;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Task2 {
    static WebDriver driver;
    static FeedBackPage feedBackPage;
    static FeedBackPageSubmitted feedBackPageSubmitted;
    WebDriverWait wait;


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        driver.manage().window().maximize();
        feedBackPage = PageFactory.initElements(driver, FeedBackPage.class);
        feedBackPageSubmitted = PageFactory.initElements(driver, FeedBackPageSubmitted.class);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {

        feedBackPage.checkAllFieldsEmptyAndNotSelected();
        feedBackPage.checkSendBtnColor();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {

        feedBackPage.clickOnSendBtn();
        feedBackPageSubmitted.checkAllFieldsEmpty();
        feedBackPageSubmitted.checkButtonColors();

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {

        feedBackPage.enterName("KATY");
        feedBackPage.enterAge(23);
        feedBackPage.selectLanguageCheckBox("English");
        feedBackPage.selectGender("Female");
        feedBackPage.selectHowYouLikeUs("Why me");
        feedBackPage.fillTheCommentArea("Hi there");
        feedBackPage.clickOnSendBtn();

        //check fields are filled correctly
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourName(), "KATY");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourAge(), 23);
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourLanguage(), "English");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourGender(), "Female");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourOptionOfUs(), "Why me");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourComment(), "Hi there");

        feedBackPageSubmitted.checkButtonColors();

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {

        feedBackPage.enterName("KATY");
        feedBackPage.clickOnSendBtn();
        feedBackPageSubmitted.clickOnYesBtn();

        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getSubmitMessage(),
                "Thank you, KATY, for your feedback!");

        feedBackPageSubmitted.checkSubmitMessageColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {

        feedBackPage.clickOnSendBtn();
        feedBackPageSubmitted.clickOnYesBtn();
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getSubmitMessage(), "Thank you for your feedback!");

        feedBackPageSubmitted.checkSubmitMessageColor();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {

        feedBackPage.enterName("KATY");
        feedBackPage.enterAge(23);
        feedBackPage.selectLanguageCheckBox("English");
        feedBackPage.selectGender("Female");
        feedBackPage.selectHowYouLikeUs("Why me");
        feedBackPage.fillTheCommentArea("Hi there");
        feedBackPage.clickOnSendBtn();

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(feedBackPageSubmitted.getNoBtn()));
        feedBackPageSubmitted.clickOnNoBtn();

        feedBackPage.checkFormAfterNoBtn();
        feedBackPage.checkIsSelected(feedBackPage.getCheckBoxEnglish());
        feedBackPage.checkIsSelected(feedBackPage.getRadioBtnGender().get(1));
        feedBackPage.checkIsSelected(feedBackPage.getLikeUsDDoptions().get(4));

    }
}