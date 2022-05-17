import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;


public class MyStepdefs {
    WebDriver driver;
    public static String status = "passed";
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    // Sets up Browser
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After

    public void tearDown() {
        driver.quit();
    }

    @Given("User clicks Oscars at Awards and Events section which is under Menu")
    public void userClicksOscarsAtAwardsAndEventsSectionWhichIsUnderMenu() {
        driver.get("https://www.imdb.com/");
        driver.findElement(By.cssSelector("#imdbHeader-navDrawerOpen--desktop > .ipc-button__text")).click();
        driver.findElement(By.cssSelector(".sc-bBXqnf:nth-child(3) .ipc-list__item:nth-child(1) > .ipc-list-item__text")).click();

    }

    @And("Chooses date {string} at Event History table")
    public void choosesDateAtEventHistoryTable(String arg0) {
        driver.findElement(By.linkText(arg0)).click();
    }

    @And("Clicks on {string}")
    public void clicksOn(String arg0) {
        driver.findElement(By.linkText(arg0)).click();
    }

    @And("Takes note of Director Writer and Stars")
    public void takesNoteOfDirectorWriterAndStars() {
        vars.put("directorOscars", driver.findElement(By.xpath("//body/div[@id=\'__next\']/main[1]/div[1]/section[1]/section[1]/div[3]/section[1]/section[1]/div[3]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/ul[1]/li[1]/a[1]")).getText());
        System.out.println(vars.get("directorOscars").toString());
        vars.put("writerOscars", driver.findElement(By.xpath("//body/div[@id='__next']/main[1]/div[1]/section[1]/section[1]/div[3]/section[1]/section[1]/div[3]/div[2]/div[1]/div[3]/ul[1]/li[2]/div[1]/ul[1]/li[1]/a[1]")).getText());
        System.out.println(vars.get("writerOscars").toString());
        vars.put("starsOscars", driver.findElement(By.xpath("//body/div[@id='__next']/main[1]/div[1]/section[1]/section[1]/div[3]/section[1]/section[1]/div[3]/div[2]/div[1]/div[3]/ul[1]/li[3]/div[1]/ul[1]/li[1]/a[1]")).getText());
        System.out.println(vars.get("starsOscars").toString());
    }

    @And("Clicks on IMDB logo at the left upper side")
    public void clicksOnIMDBLogoAtTheLeftUpperSide() {
        driver.findElement(By.id("home_img_holder")).click();
    }

    @And("Types {string} to the search bar")
    public void typesToTheSearchBar(String arg0) throws InterruptedException {
        driver.findElement(By.id("suggestion-search")).click();
        driver.findElement(By.id("suggestion-search")).sendKeys(arg0);
        Thread.sleep(5500);
    }

    @And("Selects movie acording to {string}")
    public void selects(String arg0) {
        driver.findElement(By.xpath("//div[contains(text(),'"+arg0+"')]")).click();
    }

    @Then("Compares Director Writer and Stars values")
    public void comparesDirectorWriterAndStarsValues() {
        vars.put("directorMenu", driver.findElement(By.xpath("//body/div[@id=\'__next\']/main[1]/div[1]/section[1]/section[1]/div[3]/section[1]/section[1]/div[3]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/ul[1]/li[1]/a[1]")).getText());
        System.out.println(vars.get("directorMenu").toString());
        vars.put("writerMenu", driver.findElement(By.xpath("//body/div[@id='__next']/main[1]/div[1]/section[1]/section[1]/div[3]/section[1]/section[1]/div[3]/div[2]/div[1]/div[3]/ul[1]/li[2]/div[1]/ul[1]/li[1]/a[1]")).getText());
        System.out.println(vars.get("writerMenu").toString());
        vars.put("starsMenu", driver.findElement(By.xpath("//body/div[@id='__next']/main[1]/div[1]/section[1]/section[1]/div[3]/section[1]/section[1]/div[3]/div[2]/div[1]/div[3]/ul[1]/li[3]/div[1]/ul[1]/li[1]/a[1]")).getText());
        System.out.println(vars.get("starsMenu").toString());
        Assert.assertEquals(vars.get("directorOscars"),vars.get("directorMenu"));
        Assert.assertEquals(vars.get("writerOscars"),vars.get("writerMenu"));
        Assert.assertEquals(vars.get("starsOscars"),vars.get("starsMenu"));
        System.out.println(vars.get("starsOscars").toString());
    }

    @Then("Validate the visibility of all photos")
    public void validateTheVisibilityOfAllPhotos() {
        driver.findElement(By.xpath("//body/div[@id='__next']/main[1]/div[1]/section[1]/div[1]/section[1]/div[1]/div[1]/section[2]/div[1]/a[1]/h3[1]")).click();
        Integer iBrokenImageCount = 0;

        try
        {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.tagName("img"));
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list)
            {
                if (img != null)
                {
                    if (img.getAttribute("naturalWidth").equals("0"))
                    {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
        System.out.println("The page has " + iBrokenImageCount + " broken images");

        List<WebElement> elements = driver.findElements(By.cssSelector("body.fixed:nth-child(2) div.redesign div.pagecontent:nth-child(2) div.redesign:nth-child(5) div.article:nth-child(1) div.header div.media_index_pagination.leftright:nth-child(1) div:nth-child(2) > a.prevnext"));
        assert(elements.size() > 0);

        if(elements.size()>0) {
            driver.findElement(By.linkText("Next »")).click();
            try
            {
                iBrokenImageCount = 0;
                List<WebElement> image_list = driver.findElements(By.tagName("img"));
                /* Print the total number of images on the page */
                System.out.println("The page under test has " + image_list.size() + " images");
                for (WebElement img : image_list)
                {
                    if (img != null)
                    {
                        if (img.getAttribute("naturalWidth").equals("0"))
                        {
                            System.out.println(img.getAttribute("outerHTML") + " is broken.");
                            iBrokenImageCount++;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                status = "failed";
                System.out.println(e.getMessage());
            }
            status = "passed";
            System.out.println("The page has " + iBrokenImageCount + " broken images");

        }
    }

    }

