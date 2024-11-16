package valkiria;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaLogin extends PaginaBase{

    //Constructor
    public PaginaLogin(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By btnIniciarSecion = By.xpath("(//p[normalize-space()='INICIAR SESIÓN'])[1]");
    private By correo = By.name("email");
    private By contrasena = By.name("password");
    private By btnEnviar = By.xpath("(//button[normalize-space()='Iniciar Sesión'])[1]");
    private  By usuarioLogiado = By.xpath("(//p[@class='chakra-text css-1eq6kar'])[1]");

    private  By mailRequerido = By.xpath("(//div[@id='field-:rc:-feedback'])[1]");

    /**
     * Hace click en "botón iniciar seción".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickIniciarSecion() throws InterruptedException {
        this.click(btnIniciarSecion);
    }

    /** Completa el campo de email.
     * @param mail el email del usuario.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void ingresarCorreo(String mail) throws InterruptedException {
        this.enviarTexto(mail, correo);
    }

    /** Completa la contraseña del operador.
     * @param pass contraseña del operador.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void ingresarContrasena(String pass) throws InterruptedException {
        this.enviarTexto(pass, contrasena);
    }

    /**
     * Hace click en "Enviar".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickEnviar() throws InterruptedException {
        this.click(btnEnviar);
    }

    /** Obtiene el nombre del usuario logueado.
     * @return El texto del usuario.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String loginNombre() throws InterruptedException {
        System.out.println("El nombre del usuario es: " + this.obtenerTexto(usuarioLogiado));
        return this.obtenerTexto(usuarioLogiado);
    }

    /** Obtiene mensaje de mail obligatorio.
     * @return El mensaje de obligatorio.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String mailRequerido() throws InterruptedException {
        System.out.println("Se valida mensaje de Mail Obligatorio: " + this.obtenerTexto(mailRequerido));
        return this.obtenerTexto(mailRequerido);
    }

}
