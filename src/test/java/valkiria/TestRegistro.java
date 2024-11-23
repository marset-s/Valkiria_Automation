package valkiria;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestRegistro {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void run() {
//        extent = ReportFactory.getInstance();
//        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
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
    public void RegistroExitoso() throws InterruptedException {
        PaginaRegistro paginaRegistro = new PaginaRegistro(driver, wait);
        paginaRegistro.clickCrearCuenta();

        paginaRegistro.escribirNombre("teo");
        paginaRegistro.escribirApellido("lopez");
        paginaRegistro.escribirUsuario("teo.l");
        paginaRegistro.escribirEmail("teo@gmail.com");
        paginaRegistro.escribirContrasena("Hola1234!");
        paginaRegistro.confirmarContrasena("Hola1234!");
        paginaRegistro.clikRegistrarse();

        //pendiente ver como capturar el modal/ventana de registro exitoso
        if(paginaRegistro.registroExitoso().equals("Valkiria")){
            System.out.println("Pasó el test");
        }else {
            Assertions.fail("Fallo la validación de registro exitoso.");
        }
    }

    @Test
    public void usuarioYaEstaEnUso() throws InterruptedException {
        PaginaRegistro paginaRegistro = new PaginaRegistro(driver, wait);
        paginaRegistro.clickCrearCuenta();

        paginaRegistro.escribirNombre("Ana");
        paginaRegistro.escribirApellido("Mar");
        paginaRegistro.escribirUsuario("anita");
        paginaRegistro.escribirEmail("ana@gmail.com");
        paginaRegistro.escribirContrasena("Hola1234!");
        paginaRegistro.confirmarContrasena("Hola1234!");
        paginaRegistro.clikRegistrarse();

        if (paginaRegistro.usuarioEnUso().equals("anita ya está en uso.")){
            System.out.println("Pasó el test");
        }else {
            Assertions.fail("Fallo la validación de error cuando el usuario ya está en uso.");
        }

    }

    @Test
    public void confirmarConContrasenaIcorrecta() throws InterruptedException {
        PaginaRegistro paginaRegistro = new PaginaRegistro(driver, wait);
        paginaRegistro.clickCrearCuenta();

        paginaRegistro.escribirNombre("Ema");
        paginaRegistro.escribirApellido("Perz");
        paginaRegistro.escribirUsuario("emape");
        paginaRegistro.escribirEmail("emape@gmail.com");
        paginaRegistro.escribirContrasena("Hola1234!");
        paginaRegistro.confirmarContrasena("Hola124!");
        paginaRegistro.clikRegistrarse();

        if (paginaRegistro.contrasenaDistinta().equals("Las contraseñas no coinciden")){
            System.out.println("Pasó el test");
        }else {
            Assertions.fail("Fallo la validación de error cuando no coinciden las contrasenas.");
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
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }

}
