package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CloudStorageTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    private String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    public void testUserSignupLogin() {
        String username = "haben";
        String password = "fisseha";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("HABEN", "SOLOMON", username, password);

    }

    @Test
    @Order(2)
    public void login() throws InterruptedException {

        Thread.sleep(1000);

        String username = "haben";
        String password = "fisseha";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        HomePage homePage = new HomePage(driver);

    }

    @Test
    @Order(3)
    public void tests() throws InterruptedException {

        noteTests();
        Thread.sleep(2000);
        credentialTest();

    }

    private void noteTests() throws InterruptedException {

        NotesTabPage notesTabPage = new NotesTabPage(driver);

        noteAdd(notesTabPage);
        noteEdit(notesTabPage);
        noteDelete(notesTabPage);
    }

    public void credentialTest() throws InterruptedException {

        CredentialsTabPage credentialsTabPage = new CredentialsTabPage(driver);

        credentialAdd(credentialsTabPage);
        credentialEdit(credentialsTabPage);
        credentialDelete(credentialsTabPage);

    }

    private void noteAdd(NotesTabPage notesTabPage) throws InterruptedException {

        Thread.sleep(2000);

        notesTabPage.noteTab.click();

        notesTabPage.addNoteButton.click();

        notesTabPage.postNote("First Title", "* First Description 01 \n* First Description 02 \n* First Description 03 \n* First Description 04 \n* First Description 05 \n");

        Thread.sleep(2000);
        notesTabPage.noteSubmit.click();

        notesTabPage.addNoteButton.click();

        notesTabPage.postNote("Second Title", "- Second Description 01 \n- Second Description 02 \n- Second Description 03 \n- Second Description 04 \n");

        Thread.sleep(2000);
        notesTabPage.noteSubmit.click();
    }

    private void noteEdit(NotesTabPage notesTabPage) throws InterruptedException {

        Thread.sleep(2000);
        notesTabPage.noteTab.click();

        Thread.sleep(2000);
        notesTabPage.noteEdit.click();

        notesTabPage.postNote("First Title After Edit", "First Description After Edit");

        Thread.sleep(2000);
        notesTabPage.noteSubmit.click();


    }

    private void noteDelete(NotesTabPage notesTabPage) throws InterruptedException {

        Thread.sleep(2000);
        notesTabPage.noteDelete.click();
        Thread.sleep(2000);

    }

    private void credentialAdd(CredentialsTabPage credentialsTabPage) throws InterruptedException {

        credentialsTabPage.navCredential.click();

        credentialsTabPage.addCredential.click();

        credentialsTabPage.credentialNote("https://www.youtube.com", "User One", "abc123");

        Thread.sleep(2000);
        credentialsTabPage.credentialSave.click();

        credentialsTabPage.addCredential.click();

        credentialsTabPage.credentialNote("https://www.google.com/", "User Two", "!@#ABC");

        Thread.sleep(2000);
        credentialsTabPage.credentialSave.click();

    }

    private void credentialEdit(CredentialsTabPage credentialsTabPage) throws InterruptedException {
        credentialsTabPage.navCredential.click();

        Thread.sleep(2000);
        credentialsTabPage.credentialEdit.click();

        credentialsTabPage.credentialNote("https://www.udacity.com", "Student", "987654321");

        Thread.sleep(2000);
        credentialsTabPage.credentialSave.click();
    }

    private void credentialDelete(CredentialsTabPage credentialsTabPage) throws InterruptedException {

        Thread.sleep(2000);
        credentialsTabPage.credentialDelete.click();
        Thread.sleep(2000);

    }


}
