package valkiria;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void run() {
//        extent = ReportFactory.getInstance();
//        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE LOGIN >>>");
    }

    @BeforeEach
    public void precondiciones() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        PaginaLogin loginPage = new PaginaLogin(driver, wait);
        loginPage.obtenerUrl("http://valkirias.s3-website.us-east-2.amazonaws.com/");
    }

    @Test
    public void test_LogueoExitoso() throws InterruptedException {
        PaginaLogin paginaLogin = new PaginaLogin(driver, wait);
        paginaLogin.clickIniciarSecion();
        paginaLogin.ingresarCorreo("ser@gmail.com");
        paginaLogin.ingresarContrasena("Hola1234!");
        paginaLogin.clickEnviar();

        // Validar si el nombre mostrado tras el login es correcto
        if (paginaLogin.loginNombre().equals("¡Hola serrimar!")) {
            System.out.println("Pasó el test");
        } else {
            Assertions.fail("Fallo la validación del nombre de usuario en el login.");
        }
    }

    @Test
    public void test_LogueoMailVacio() throws InterruptedException {
        PaginaLogin paginaLogin = new PaginaLogin(driver, wait);

        paginaLogin.clickIniciarSecion();
        paginaLogin.ingresarContrasena("Hola1234!");
        paginaLogin.clickEnviar();

        if(paginaLogin.mailRequerido().equals("El email es requerido")){
            System.out.println("Pasó el test");
        } else {
            Assertions.fail("Fallo la validación del mail de usuario en el login.");
        }

    }

    @AfterEach
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit(); // Cierra todas las ventanas y libera los recursos.
        }
    }
    @AfterAll
    public static void finish() {
        System.out.println("<<< FINALIZAN LOS TEST DE LOGIN >>>");
    }
}
