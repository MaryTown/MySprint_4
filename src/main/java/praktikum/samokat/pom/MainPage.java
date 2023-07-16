package praktikum.samokat.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;
    // верхняя кнопка "Заказать"
    private final By upOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    // нижняя кнопка "Заказать"
    private final By downOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // метод для нажатия на одну из кнопок "Заказать" в зависимости от указанной orderButton
    public void clickOnButtonOrder(By orderButton){
        WebElement element = webDriver.findElement(orderButton);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        webDriver.findElement(orderButton).click();
    }

    public By getUpOrderButton(){
        return upOrderButton;
    }
    public By getDownOrderButton(){
        return downOrderButton;
    }

    // метод для скролла до соответствующего вопроса и нажатия на него
    public void openAccordionQuestion(String question) {
        WebElement element = webDriver.findElement(By.xpath("//div[@class='accordion__heading']//div[text()='" + question + "']"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        webDriver.findElement(By.xpath("//div[@class='accordion__heading']//div[text()='" + question + "']")).click();
    }

    // метод для получаения ответа на вопрос
    public String getAnswer(String answer) {
        final Wait<WebDriver> wait = new FluentWait<>(webDriver).withMessage("Элемент не найден").withTimeout(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[@class='accordion__panel']//p[text()='" + answer + "']"))));
        return webDriver.findElement(By.xpath("//div[@class='accordion__panel']//p[text()='" + answer + "']")).getText();
    }
}
