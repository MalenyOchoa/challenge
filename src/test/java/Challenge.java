import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Challenge {
    public static final String PASSWORD_POLICY_ERROR_CSS_SELECTOR_PATH = "#root > div > div.Register__AppContainer-n765o3-0.dwYYKY > div.Register__AppContent-n765o3-2.ctlsXW > div > form > div.Register__InputsWrapper-n765o3-6.fdFAAD > div.PasswordInput__InputWrapper-sc-1szzz3q-0.kAYYOo > div.Input__InputWrapper-sc-8xbmuo-0.izlYjU > p";
    public static final String BITSO_DOMAIN = "https://bitso.com";
    private WebDriver browser;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        this.browser = new ChromeDriver();
        this.browser.get(BITSO_DOMAIN + "/register");
        this.browser.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        browser.close();
    }

    @Test
    public void ensureRegisterPasswordPolicyErrorAtLeastEightCharacters() {
        String expectedMessage = "Min. 8 characters with numbers and symbols";

        WebElement emailInput = browser.findElement(By.id("email"));
        emailInput.click();
        emailInput.sendKeys("test@test.com");

        WebElement passwordInput = browser.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.sendKeys("123");
        passwordInput.submit();

        WebElement passwordPolicyError = browser.findElement(By.cssSelector(PASSWORD_POLICY_ERROR_CSS_SELECTOR_PATH));
        TestCase.assertTrue(passwordPolicyError.getText().contains(expectedMessage));
    }

    @Test
    public void ensureRegisterPasswordPolicyErrorAtMissingSymbols() {
        String expectedMessage = "You're missing a symbol";

        WebElement emailInput = browser.findElement(By.id("email"));
        emailInput.click();
        emailInput.sendKeys("test@test.com");

        WebElement passwordInput = browser.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.sendKeys("asdfasdfasdfa");
        passwordInput.submit();

        WebElement passwordPolicyError = browser.findElement(By.cssSelector(PASSWORD_POLICY_ERROR_CSS_SELECTOR_PATH));
        TestCase.assertTrue(passwordPolicyError.getText().contains(expectedMessage));
    }

    @Test
    public void ensureRegisterPasswordPolicyErrorAtEmptyPasswordInput() {
        String expectedMessage = "The password can't have more than 3 consecutive alphanumeric characters";

        WebElement emailInput = browser.findElement(By.id("email"));
        emailInput.click();
        emailInput.sendKeys("test@test.com");

        WebElement passwordInput = browser.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.sendKeys("");
        passwordInput.submit();

        WebElement passwordPolicyError = browser.findElement(By.cssSelector(PASSWORD_POLICY_ERROR_CSS_SELECTOR_PATH));
        System.out.println(passwordPolicyError.getText());
        System.out.println(expectedMessage);

        TestCase.assertTrue(passwordPolicyError.getText().contains(expectedMessage));
    }
}