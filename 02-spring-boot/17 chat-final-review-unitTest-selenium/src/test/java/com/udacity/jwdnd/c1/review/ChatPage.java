package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageText;

    //@FindBy(id ="messageType")
    //private WebElement messageType;

    @FindBy(id = "submitMessage")
    private WebElement submitMessage;

    @FindBy(className = "chatMessageUsername")
    private WebElement chatMessageUsername;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sendChatMessage(String text) {
        this.messageText.sendKeys(text);
        this.submitMessage.click();
    }

    public ChatMessage getFirstMessage() {
        ChatMessage result = new ChatMessage();
        result.setMessageText(chatMessageUsername.getText());
        result.setUsername(chatMessageUsername.getText());
        return result;
    }
}
