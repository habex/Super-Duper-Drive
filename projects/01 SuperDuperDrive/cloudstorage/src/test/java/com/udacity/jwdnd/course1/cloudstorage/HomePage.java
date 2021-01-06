package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
       setWebDriver(driver);
    }

    public void setWebDriver(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }
}
