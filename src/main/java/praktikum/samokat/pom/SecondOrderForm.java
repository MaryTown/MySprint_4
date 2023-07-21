package praktikum.samokat.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SecondOrderForm {
    private final WebDriver webDriver;
    // поле "* Когда привезти самокат"
    private final By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // поле "* Срок аренды"
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    // поле "Комментарий для курьера"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // кнопка "Заказать" (внизу экрана)
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // кнопка "Да" в поп-апе "Хотите оформить заказ?"
    private final By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");

    // всплывающее окно с уведомлением об успешном оформлении
    private final By PopUpWithSuccessfulOrder = By.className("Order_ModalHeader__3FDaJ");

    public SecondOrderForm(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // метод для ввода даты Когда привезти самокат (ввести дату и затем выбрать соответствующую дату в календаре кнопкой Enter)
    public void inputDeliveryDate(String date){
        webDriver.findElement(deliveryDateField).sendKeys(date);
        webDriver.findElement(deliveryDateField).sendKeys(Keys.ENTER);
    }

    // метод для выбора срока аренды
    public void selectRentalPeriod(String period) {
        webDriver.findElement(rentalPeriodField).click();
        webDriver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']")).click();
    }

    // метод для выбора цвета самоката
    public void selectColour(String colour) {
        webDriver.findElement(By.xpath("//label[@class='Checkbox_Label__3wxSf'][text() = '" + colour + "']")).click();
    }

    // метод для ввода комментария
    public void inputComment(String comment){
        webDriver.findElement(commentField).sendKeys(comment);
    }

    // метод для нажатия на кнопку "Заказать"
    public void clickOrderButton(){
        webDriver.findElement(orderButton).click();
    }

    // метод для нажатия на кнопку "Да"
    public void clickYesButton(){
        webDriver.findElement(yesButton).click();
    }

    public String receivePopUpWithSuccessfulOrder() {
        return webDriver.findElement(PopUpWithSuccessfulOrder).getText();
    }
}
