package chrome;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandex_practicum_sprint4.OrderPage;
import yandex_practicum_sprint4.RentPage;
import yandex_practicum_sprint4.HomePage;


@RunWith(Parameterized.class)
public class TestOrderButtonDownPageChrome {
    private final String name;
    private final String surName;
    private final String adress;
    private final String metro;
    private final String telefon;
    private final String data;
    private final int period;
    private final int color;
    private final String coment;
    private WebDriver driver;

    public TestOrderButtonDownPageChrome(String name, String surName, String adress, String metro, String telefon, String data, int period, int color, String coment) {
        this.name = name;
        this.surName = surName;
        this.adress = adress;
        this.metro = metro;
        this.telefon = telefon;
        this.data = data;
        this.period = period;
        this.color = color;
        this.coment = coment;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва", "Комсомольская", "+7911111111", "30.07.2022", 0, 0, "Проверка"},
                {"Петр", "Петров", "Питер", "Сокольники", "+7922222222", "31.07.2022", 1, 1, ""},
        };
    }

    @Test
    public void testOrderButtonDownPage() {
        driver = new ChromeDriver();
        // подключаемся к странице
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        RentPage rentPage = new RentPage(driver);
        // подтвержадем сбор куки
        homePage.clickCookieButton();
        // ждем кликабельности кнопки
        homePage.scrollToButtonDownPage();
        homePage.waitForLoadOrderButtonDownPage();
        homePage.clickOrderButtonDownPage();
        //Ждем загрузки и заполняем поля
        orderPage.waitForLoadOrderPage();
        orderPage.setFieldName(name);
        orderPage.setFieldSurName(surName);
        orderPage.setFieldAdress(adress);
        orderPage.setFieldMetro(metro + Keys.DOWN + Keys.ENTER);
        orderPage.setFieldTelefon(telefon);
        orderPage.clickButtonNext();
        //заполняем поля с арендой
        rentPage.waitForLoadOrderPage();
        rentPage.setFieldData(data + Keys.ENTER);
        rentPage.setFieldPeriod(period);
        rentPage.setFieldColor(color);
        rentPage.setFieldComent(coment);
        rentPage.clickOrderButton();
        //ждем загрузки модального окна подтверждения заказа
        rentPage.waitForLoadOrderModalHeader();
        //кликаем ок
        rentPage.clickOkButton();
        //проверяем, что заказ оформлен
        rentPage.setOrderModalFinal();

    }

    @After
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}
