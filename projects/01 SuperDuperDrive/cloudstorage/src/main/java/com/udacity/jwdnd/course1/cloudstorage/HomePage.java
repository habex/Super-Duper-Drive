package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
