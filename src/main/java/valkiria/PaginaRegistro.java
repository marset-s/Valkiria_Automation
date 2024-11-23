package valkiria;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaRegistro extends PaginaBase {

    public PaginaRegistro(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By btnCrearCuenta = By.xpath("(//p[normalize-space()='CREAR CUENTA'])[1]");
    private By nombre = By.name("firstName");
    private By apellido = By.name("lastName");
    private By nombreUsuario = By.name("clientName");
    private By email = By.name("email");

    private By contrasena = By.name("password");
    private By confirmarContrasena = By.name("confirmPassword");
    private By btnRegistrarse = By.xpath("(//button[normalize-space()='Registrarse'])[1]");
    private By textoRegistroExitoso = By.cssSelector("p.chakra-text.css-owepl0");
    //texto -> Registro exitoso. Serás redirigido a la página de inicio.
    private By contrasenaDistinta = By.cssSelector("div.chakra-form__error-message.css-502kp3");

    private By usuarioYaEnUso = By.id("field-:r5:-feedback");

    public void clickCrearCuenta() throws InterruptedException {
        this.click(btnCrearCuenta);
    }

    public void escribirNombre(String inputNombre) throws InterruptedException {
        this.enviarTexto(inputNombre, nombre);
    }

    public void escribirApellido(String inputApellido) throws InterruptedException {
        this.enviarTexto(inputApellido, apellido);
    }

    public void escribirUsuario(String inputUsuario) throws InterruptedException {
        this.enviarTexto(inputUsuario, nombreUsuario);
    }

    public void escribirEmail(String inputEmail) throws InterruptedException {
        this.enviarTexto(inputEmail, email);
    }

    public void escribirContrasena(String inputContrasena) throws InterruptedException {
        this.enviarTexto(inputContrasena, contrasena);
    }

    public void confirmarContrasena(String inputConfirmarContrasena) throws InterruptedException {
        this.enviarTexto(inputConfirmarContrasena, confirmarContrasena);
    }

    public void clikRegistrarse() throws InterruptedException {
        this.click(btnRegistrarse);
    }

    public String registroExitoso() throws InterruptedException {
        String res = this.obtenerTexto(textoRegistroExitoso);
        System.out.println("Resultado del mensaje de éxito: " + res);
        return res;
    }

    public String contrasenaDistinta() throws InterruptedException {
        String res = this.obtenerTexto(contrasenaDistinta);
        System.out.println("Mensaje de error: " + res);
        return res;
        //Las contraseñas no coinciden
    }

    public String usuarioEnUso() throws InterruptedException {
        String res = this.obtenerTexto(usuarioYaEnUso);
        System.out.println("Mensaje de error: " + res);
        return res;

    }
}