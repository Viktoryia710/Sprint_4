package yandex_practicum_sprint4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//страница с персональными данными
public class OrderPage {
    private final WebDriver driver;
    //локатор поля имя
    private final By fieldName = By.xpath(".//input[@placeholder='* Имя']");

    //локатор поля Фамилия
    private final By fieldLastName = By.xpath(".//input[@placeholder='* Фамилия']");

    //локатор адресс
    private final By fieldAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //локатор метро
    private final By fieldMetro = By.xpath(".//input[@placeholder='* Станция метро']");

    //локатор телефон
    private final By fieldPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка далее
    private final By buttonNext = By.xpath("//button[text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFieldName(String name) {
        driver.findElement(fieldName).clear();
        driver.findElement(fieldName).sendKeys(name);
    }

    public void setFieldSurName(String surName) {
        driver.findElement(fieldLastName).clear();
        driver.findElement(fieldLastName).sendKeys(surName);
    }

    public void setFieldAdress(String adress) {
        driver.findElement(fieldAdress).clear();
        driver.findElement(fieldAdress).sendKeys(adress);
    }

    public void setFieldMetro(String metro) {
        driver.findElement(fieldMetro).clear();
        driver.findElement(fieldMetro).sendKeys(metro);
    }

    public void setFieldTelefon(String phone) {
        driver.findElement(fieldPhone).clear();
        driver.findElement(fieldPhone).sendKeys(phone);
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    //метод ожидания
    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(buttonNext));
    }
}
