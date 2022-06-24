import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrbitzAutomation {
    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rahim\\Downloads\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        driver.get("https://www.orbitz.com/");


        driver.findElement(By.xpath("//button[@aria-label='Going to']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("location-field-destination")));
        driver.findElement(By.id("location-field-destination")).sendKeys("Orlando", Keys.ENTER);
        driver.findElement(By.id("d1-btn")).click();
        Thread.sleep(300);


        Utility.jsClick(driver, driver.findElement(By.xpath("//td[@class='uitk-date-picker-day-number']//button[@data-day='20']")));
        Thread.sleep(300);
        Utility.jsClick(driver, driver.findElement(By.xpath("//td[@class='uitk-date-picker-day-number']//button[@data-day='24']")));
        Thread.sleep(300);
        Utility.jsClick(driver, driver.findElement(By.xpath("//button[text()='Done']")));
        Thread.sleep(300);
        Thread.sleep(300);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@data-testid='travelers-field-trigger']")));

        Utility.jsClick(driver, driver.findElement(By.xpath("//button[@data-testid='travelers-field-trigger']")));
        Thread.sleep(1000);
        Utility.jsClick(driver, driver.findElement(By.xpath("//input[@id='adult-input-0']//preceding-sibling::button")));
        Thread.sleep(1000);
        Utility.jsClick(driver, driver.findElement(By.xpath("//input[@id='child-input-0']//following-sibling::button")));
        Thread.sleep(1000);
        Utility.jsClick(driver, driver.findElement(By.xpath("//input[@id='child-input-0']//following-sibling::button")));
        Thread.sleep(1000);
        Utility.jsClick(driver, driver.findElement(By.xpath("//button[text()='Done']")));
        new Select(driver.findElement(By.id("child-age-input-0-0"))).selectByIndex(5);
        new Select(driver.findElement(By.id("child-age-input-0-1"))).selectByIndex(9);
        Thread.sleep(1000);
        Utility.jsClick(driver, driver.findElement(By.xpath("//button[text()='Done']")));
        Thread.sleep(1000);
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@data-testid='submit-button']")));
        Utility.jsClick(driver,driver.findElement(By.xpath("//button[@data-testid='submit-button']")));
        Utility.jsClick(driver,driver.findElement(By.xpath("//input[@id='popularFilter-0-FREE_BREAKFAST']")));

        Thread.sleep(1000);
        //step6
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("playback-filter-pill-mealPlan-FREE_BREAKFAST")));
        WebElement breakfastActual = driver.findElement(By.id("playback-filter-pill-mealPlan-FREE_BREAKFAST"));
        Assert.assertEquals(breakfastActual.getText(),"Breakfast included");
        Thread.sleep(1000);

        //step 7
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@class='uitk-icon uitk-pill-action-icon uitk-icon-medium']")).click();
        Thread.sleep(300);
        Utility.jsClick(driver,driver.findElement(By.xpath("//input[@id='popularFilter-0-FREE_BREAKFAST']")));
        Thread.sleep(300);
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='popularFilter-0-FREE_BREAKFAST']")).isSelected());
        Thread.sleep(5000);

        //step 8
        driver.findElement(By.xpath("(//input[@aria-valuemax='300'])[2]")).sendKeys(Keys.ARROW_LEFT);
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='uitk-pill-text']")));
        WebElement budgetActual = driver.findElement(By.xpath("//span[@class='uitk-pill-text']"));
        Assert.assertEquals(budgetActual.getText(),"Less than $270");
        Thread.sleep(2000);

        Utility.scroll(driver,0,500);
        Thread.sleep(2000);

        //step 9
        List<WebElement> numberOfResults = driver.findElements(By.xpath("//div[@class='uitk-spacing uitk-spacing-padding-block-half']"));
        int actualNumberOfResults = 50;
        Assert.assertEquals(numberOfResults.size(),actualNumberOfResults);

        for(WebElement price:numberOfResults){
            int formatPrice = Integer.parseInt(price.findElement(By.xpath(".//div[starts-with(text(), 'The price is')]")).getText().split("\\$")[1]);
            Assert.assertTrue(formatPrice <= 270);

        }

        //step 10
        Thread.sleep(300);
        Utility.jsClick(driver, driver.findElement(By.xpath("//label[@for='radio-guestRating-45']")));

        Thread.sleep(2000);
        //step 11
        List<WebElement> ratingResults = driver.findElements(By.xpath("//span[@class='uitk-text uitk-type-300 uitk-type-bold uitk-text-default-theme']"));
        for(WebElement rating:ratingResults){
            double formatRating = Double.parseDouble(rating.findElement(By.xpath("//span[@class='uitk-text uitk-type-300 uitk-type-bold uitk-text-default-theme']")).getText().split("/")[0]);
            Assert.assertTrue(formatRating >= 4.5);

        }
        //span[@class='uitk-text uitk-type-300 uitk-type-bold uitk-text-default-theme]
        Utility.scroll(driver,0,500);


        //step 12
//        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='uitk-spacing uitk-spacing-margin-blockstart-three']")));
        List<WebElement> hotels = driver.findElements(By.xpath("//li[@class='uitk-spacing uitk-spacing-margin-blockstart-three']"));
        Thread.sleep(5000);
        WebElement lastHotel = hotels.get(hotels.size()-1);
        String hotelName = lastHotel.findElement(By.xpath("./child::*")).getText();
        double hotelRating = Double.parseDouble(lastHotel.findElement(By.xpath(".//span[contains(text(), 'out of 5')]//preceding-sibling::span")).getText().split("/")[0]);


        System.out.println(hotelName);
        System.out.println(hotelRating);
        Thread.sleep(2000);
        lastHotel.click();
        //span[@class='uitk-spacing uitk-spacing-padding-inlineend-one']/child::*


        // step 13
        Thread.sleep(2000);
        String firstWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if(!windowHandle.equals(firstWindowHandle)){
                driver.switchTo().window(windowHandle);
            }
        }


        Assert.assertEquals(hotelName, driver.getTitle());

        String newWindowHotel= driver.findElement(By.xpath("//h1[@class='uitk-heading-3']")).getText();
        double newWindowRating = Double.parseDouble(driver.findElement(By.xpath("//h3[@class='uitk-heading-5 uitk-spacing uitk-spacing-padding-blockend-three']")).getText().split("/")[0]);
        Assert.assertEquals(newWindowRating, hotelRating);
        Assert.assertEquals(newWindowHotel, hotelName);
        //step 14
        driver.close();
        driver.switchTo().window(firstWindowHandle);

        //step 15

        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href='/']")).click();
        Thread.sleep(2000);

        //step 16
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//iframe[starts-with(@src, 'https://vac.vap.expedia.com/')]")));
        WebElement helpButton = driver.findElement(By.xpath("//iframe[starts-with(@src, 'https://vac.vap.expedia.com/')]"));
        Thread.sleep(2000);
//
        helpButton.click();
        Thread.sleep(2000);
        driver.switchTo().frame(helpButton);

        Thread.sleep(5000);

        //step 17
        String expectedVirtualMessage = "Hi, I'm your Virtual Agent";
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@data-test-id='chat-text-message'])[1]")));
        String currentVirtualMessage = driver.findElement(By.xpath("(//div[@data-test-id='chat-text-message'])[1]")).getText().split(" \uD83D\uDC4B")[0];
        Assert.assertEquals(currentVirtualMessage,expectedVirtualMessage);

        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@id='vac-close-button']")).click();

        Thread.sleep(5000);
        driver.switchTo().defaultContent();

        //step 18
        String[] expectedDropdownOptions = {"Stays", "Flights", "Packages", "Cars", "Cruises", "Things to do", "Deals", "Groups & meetings", "Travel Blog"};
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='gc-custom-header-tool-bar-shop-menu']//button")));
        WebElement dropdown = driver.findElement(By.xpath("//div[@id='gc-custom-header-tool-bar-shop-menu']//button"));
        Thread.sleep(5000);
        dropdown.click();
        Thread.sleep(5000);
        List<WebElement> dropDownOptions = driver.findElements(By.xpath("(//div[@class='uitk-list'])[1]//a"));
        for(int i = 0;i<dropDownOptions.size();i++){
            Assert.assertEquals(dropDownOptions.get(i).getText(),expectedDropdownOptions[i]);
        }

        driver.close();




    }
}
