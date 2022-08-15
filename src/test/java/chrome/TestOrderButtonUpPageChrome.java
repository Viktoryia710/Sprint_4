package chrome;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import yandex_practicum_sprint4.OrderPage;
import yandex_practicum_sprint4.RentPage;
import yandex_practicum_sprint4.OrderPage;
import yandex_practicum_sprint4.RentPage;
import yandex_practicum_sprint4.HomePage;

@RunWith(Parameterized.class)
public class TestOrderButtonUpPageChrome {
    private final String name;
    private final String surName;
    private final String adress;
    private final String metro;
    private final String telefon;
    private final String data;
    private final int period;
    private final int color;
    private final String coment;
    WebDriver driver;

    public TestOrderButtonUpPageChrome(String name, String surName, String adress, String metro, String telefon, String data, int period, int color, String coment) {
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
                {"Юрий", "Горбунов", "Санкт-Питербург", "Минская", "+7945454545", "12.09.2022", 0, 0, "Проверка"},
                {"Максим", "Юрьев", "Москва", "Сокольники", "+7976767676", "10.09.2022", 1, 1, ""},
        };
    }

    @Test
    public void test() {
        driver = new ChromeDriver();
        // подключаемся к странице
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        RentPage objRentPage = new RentPage(driver);
        // подтвержадем сбор куки
        objHomePage.clickCookieButton();
        // ждем кликабельности кнопки
        objHomePage.waitForLoadOrderButtonUpPage();
        objHomePage.clickOrderButtonUpPage();
        //Ждем загрузки и заполняем поля
        objOrderPage.waitForLoadOrderPage();
        objOrderPage.setFieldName(name);
        objOrderPage.setFieldSurName(surName);
        objOrderPage.setFieldAdress(adress);
        objOrderPage.setFieldMetro(metro + Keys.DOWN + Keys.ENTER);
        objOrderPage.setFieldTelefon(telefon);
        objOrderPage.clickButtonNext();
        // поля с аренды
        objRentPage.waitForLoadOrderPage();
        objRentPage.setFieldData(data + Keys.ENTER);
        objRentPage.setFieldPeriod(period);
        objRentPage.setFieldColor(color);
        objRentPage.setFieldComent(coment);
        objRentPage.clickOrderButton();
        //ждем загрузки модального окна подтверждения заказа
        objRentPage.waitForLoadOrderModalHeader();
        // кликаем ок
        objRentPage.clickOkButton();
        // ищем финальное окно
        objRentPage.setOrderModalFinal();
    }

    @After
    public void test1down() {
        // Закрываем браузер
        driver.quit();
    }


}