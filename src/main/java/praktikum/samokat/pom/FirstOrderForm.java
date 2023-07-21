package praktikum.samokat.pom;

import org.openqa.selenium.*;

public class FirstOrderForm {
    private final WebDriver webDriver;
    // поле "* Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // поле "* Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // поле "* Адрес: куда привезти заказ"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // поле "* Станция метро"
    private final By stationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле "* Телефон: на него позвонит курьер"
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public FirstOrderForm(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // метод для ввода имени
    public void inputName(String name){
        webDriver.findElement(nameField).sendKeys(name);
    }

    // метод для ввода фамилии
    public void inputSurname(String surname){
        webDriver.findElement(surnameField).sendKeys(surname);
    }

    // метод для ввода адреса
    public void inputAddress(String address){
        webDriver.findElement(addressField).sendKeys(address);
    }

    // метод для выбора станции (нажимаем на поле, вводим станцию, стрелкой вниз выбираем первую по совпадению запись, нажимаем enter)
    public void selectSubwayStation(String station){
        webDriver.findElement(stationField).click();
        webDriver.findElement(stationField).sendKeys(station);
        webDriver.findElement(stationField).sendKeys(Keys.ARROW_DOWN);
        webDriver.findElement(stationField).sendKeys(Keys.ENTER);
    }

    // метод для ввода телефона
    public void inputPhoneNumber(String phoneNumber){
        webDriver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    // метод для нажатия на кнопку "Далее" (до неё нужно сперва доскроллить)
    public void clickNextButton(){
        WebElement element = webDriver.findElement(nextButton);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        webDriver.findElement(nextButton).click();
    }

}
