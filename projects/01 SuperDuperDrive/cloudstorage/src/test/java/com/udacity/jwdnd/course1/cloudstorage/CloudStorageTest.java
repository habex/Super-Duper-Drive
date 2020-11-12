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
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        //whatever time you think is sufficient for manually entering the data.
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout-button")));
        element.isDisplayed();

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
    public void login() {
        String username = "haben";
        String password = "fisseha";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        HomePage homePage = new HomePage(driver);


    }

    @Test
    @Order(3)
    public void notePost() throws InterruptedException {

        noteAdd();
        noteEdit();
        noteDelete();

    }

    private void noteAdd() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        Thread.sleep(500);

        NotesTabPage notesTabPage = new NotesTabPage(driver);

        Thread.sleep(500);

        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        wait.until(ExpectedConditions.visibilityOf(noteTab)).click();

        WebElement addNoteButton = driver.findElement(By.id("add-note"));
        addNoteButton.click();

        notesTabPage.postNote(driver, "First Title", "First Description");

        Thread.sleep(500);

        WebElement noteSubmit = driver.findElement(By.id("note-save"));
        noteSubmit.click();

        Thread.sleep(500);
        addNoteButton = driver.findElement(By.id("add-note"));
        addNoteButton.click();

        notesTabPage.postNote(driver, "Second Title", "Second Description");

        Thread.sleep(500);

        noteSubmit = driver.findElement(By.id("note-save"));
        noteSubmit.click();

        Thread.sleep(500);
    }

    private void noteEdit() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        Thread.sleep(500);

        NotesTabPage notesTabPage = new NotesTabPage(driver);

        Thread.sleep(500);

        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        wait.until(ExpectedConditions.visibilityOf(noteTab)).click();

        Thread.sleep(500);

        WebElement noteEdit = driver.findElement(By.id("note-edit"));
        noteEdit.click();

        notesTabPage.postNote(driver, "First Title After Edit", "First Description After Edit");

        Thread.sleep(1000);
        WebElement noteSubmit = driver.findElement(By.id("note-save"));
        noteSubmit.click();



    }


    private void noteDelete() throws InterruptedException {


        Thread.sleep(500);
        WebElement noteDelete = driver.findElement(By.id("note-delete"));
        noteDelete.click();

        Thread.sleep(1000);
    }
}
