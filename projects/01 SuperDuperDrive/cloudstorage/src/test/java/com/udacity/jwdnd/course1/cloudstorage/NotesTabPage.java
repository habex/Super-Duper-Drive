package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesTabPage {

    @FindBy(id = "nav-notes-tab")
    private WebElement navNote;

    @FindBy(id = "add-note")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

   // @FindBy(id = "note-description")
    //private List<WebElement> noteDescription;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "note-save")
    private WebElement noteSubmit;

    public NotesTabPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getNavNote() {
        return navNote;
    }

    public void setNavNote(WebElement navNote) {
        this.navNote = navNote;
    }

    public WebElement getAddNoteButton() {
        return addNoteButton;
    }

    public void setAddNoteButton(WebElement addNoteButton) {
        this.addNoteButton = addNoteButton;
    }

    public WebElement getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(WebElement noteTitle) {
        this.noteTitle = noteTitle;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(WebElement noteDescription) {
        this.noteDescription = noteDescription;
    }

    public WebElement getNoteSubmit() {
        return noteSubmit;
    }

    public void setNoteSubmit(WebElement noteSubmit) {
        this.noteSubmit = noteSubmit;
    }

    public void postNote(WebDriver driver, String title, String description) throws InterruptedException {
        noteTitle.clear();
        noteDescription.clear();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
    }
}
