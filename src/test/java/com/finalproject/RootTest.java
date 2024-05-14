package com.finalproject;

import com.finalproject.Pages.AddToCart;
import com.finalproject.Pages.EditCartPage;
import com.finalproject.Pages.Filter;
import com.finalproject.Pages.Search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class RootTest {
    public static final String Web_Driver = "webdriver.chrome.driver";
    public static final String Path_Driver = "src/driver/chromedriver.exe";
    public static final String Base_URL = "https://yerevanmobile.am/";

    private WebDriver webDriver;
    protected Search search;
    protected Filter filter;
    protected AddToCart addToCart;
    protected EditCartPage editCartPage;

    @BeforeClass
    public void initialize() {
        System.setProperty(Web_Driver, Path_Driver);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        loadBaseURL();
    }

    @BeforeMethod
    public void loadBaseURL() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(Base_URL);
        search = new Search(webDriver);
        filter = new Filter(webDriver);
        addToCart = new AddToCart(webDriver);
        editCartPage = new EditCartPage(webDriver);
    }

    @AfterClass
    public void cleanup() {
        webDriver.quit();
    }
}