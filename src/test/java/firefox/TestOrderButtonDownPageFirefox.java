package firefox;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import yandex_practicum_sprint4.orderPage;
import yandex_practicum_sprint4.rentPage;
import yandex_practicum_sprint4.HomePage;


    @RunWith(Parameterized.class)
    public class TestOrderButtonDownPageFirefox {
        WebDriver driver;
        private final String name;
        private final String surName;
        private final String adress;
        private final String metro;
        private final String telefon;
        private final String data;
        private final int period;
        private final int color;
        private final String coment;

        public TestOrderButtonDownPageFirefox(String name, String surName, String adress, String metro, String telefon, String data, int period, int color, String coment) {
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
            return new Object[][] {
                    { "Иван","Иванов","Москва","Комсомольская","+7911111111","30.07.2022",0,0,"Проверка"},
                    { "Петр","Петров","Питер","Сокольники","+7922222222","31.07.2022",1,1,""},
            };
        }

        @Test
        public void test() {
            driver = new FirefoxDriver();
            // подключаемся к странице
            driver.get("https://qa-scooter.praktikum-services.ru/");
            HomePage objHomePage = new HomePage(driver);
            orderPage  objOrderPage = new orderPage(driver);
            rentPage   objRentPage = new rentPage(driver);
            // подтвержадем сбор куки
            objHomePage.clickCookieButton();
            // ждем кликабельности кнопки
            objHomePage.scrollToButtonDownPage();
            objHomePage.waitForLoadOrderButtonDownPage();
            objHomePage.clickOrderButtonDownPage();
            //Ждем загрузки и заполняем поля
            objOrderPage.waitForLoadOrderPage();
            objOrderPage.setFieldName(name);
            objOrderPage.setFieldSurName(surName);
            objOrderPage.setFieldAdress(adress);
            objOrderPage.setFieldMetro(metro+ Keys.DOWN+ Keys.ENTER);
            objOrderPage.setFieldTelefon(telefon);
            objOrderPage.clickButtonNext();
            // заполняем поля с арендой
            objRentPage.waitForLoadOrderPage();
            objRentPage.setFieldData(data+ Keys.ENTER);
            objRentPage.setFieldPeriod(period);
            objRentPage.setFieldColor(color);
            objRentPage.setFieldComent(coment);
            objRentPage.clickOrderButton();
            //ждем загрузки модального окна подтверждения заказа
            objRentPage.waitForLoadOrderModalHeader();
            // кликаем ок
            objRentPage.clickOkButton();
            //      objRentPage.setOrderModalFinal();
        }
        @After
        public void test1down() {
            // Закрываем браузер
            driver.quit();
        }
}
