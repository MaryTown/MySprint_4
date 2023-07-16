package praktikum.samokat;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.samokat.pom.FirstOrderForm;
import praktikum.samokat.pom.MainPage;
import praktikum.samokat.pom.SecondOrderForm;
import praktikum.samokat.pom.constants.Urls;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class TestScooterOrder {

    private static WebDriver webDriver;

    private final By orderButtonMainPage;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;

    public TestScooterOrder(By orderButtonMainPage, String name, String surname, String address, String station,
                            String phoneNumber, String date, String period, String colour, String comment) {
        this.orderButtonMainPage = orderButtonMainPage;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    @Before
    public void setDriver(){
        webDriver = new ChromeDriver();
        webDriver.get(Urls.url);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    static MainPage objectMainPage = new MainPage(webDriver);
    static By upOrderButton = objectMainPage.getUpOrderButton();
    static By downOrderButton = objectMainPage.getDownOrderButton();

    @Parameterized.Parameters
    public static Object[][] orderParameters() {
        return new Object[][]{
                {upOrderButton, "Иван", "Пупкин", "Москва, ул. Тверская-Ямская, д.16", "Маяковская", "79808008990", "19.07.2023", "двое суток", "чёрный жемчуг", "блаблабла"},
                {downOrderButton, "Илья", "Глупенький", "Москва, ул. Чертановская, д.2", "Чертановская", "79996660906", "23.07.2023", "пятеро суток", "серая безысходность", "комментарии излишне"},
        };
    }

    @Test
    public void fillingOrderTest() {
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.clickOnButtonOrder(orderButtonMainPage);

        FirstOrderForm firstOrderForm = new FirstOrderForm(webDriver);
        firstOrderForm.inputName(name);
        firstOrderForm.inputSurname(surname);
        firstOrderForm.inputAddress(address);
        firstOrderForm.selectSubwayStation(station);
        firstOrderForm.inputPhoneNumber(phoneNumber);
        firstOrderForm.clickNextButton();

        SecondOrderForm secondOrderForm = new SecondOrderForm(webDriver);
        secondOrderForm.inputDeliveryDate(date);
        secondOrderForm.selectRentalPeriod(period);
        secondOrderForm.selectColour(colour);
        secondOrderForm.inputComment(comment);
        secondOrderForm.clickOrderButton();

        secondOrderForm.clickYesButton();
        String textPopUp = secondOrderForm.receivePopUpWithSuccessfulOrder();
        MatcherAssert.assertThat("Всплывающее окно с сообщением об успешном создании заказа не появилось", textPopUp, startsWith("Заказ оформлен"));

    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
