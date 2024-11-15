package valkiria;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaginaBase {

    private WebDriver driver;
    public WebDriverWait wait;


    //Constructor
    public PaginaBase(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    //Configurar opciones del navegador: maximizar pantalla
    protected void maximizarPantalla() {
        driver.manage().window().maximize();
    }

    //Método para navegar a la URL especificada.
    protected void obtenerUrl(String url) throws InterruptedException {
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    //Método para cerrar el navegador
    protected void cerrar() {
        driver.quit();
    }

    //Método para encontrar un elemento en la página mediante el localizador especificado.
    //locator: El localizador del elemento.
    protected WebElement buscarElemento(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    //Método para ingresar texto en el elemento especificado.
    //* @param inputText El texto a ingresar.
    //* @param locator El localizador del elemento.
    //* @throws InterruptedException Si ocurre un error durante la espera.
    protected void enviarTexto(String inputText, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.buscarElemento(locator).clear();
        this.buscarElemento(locator).sendKeys(inputText);
    }

    //Método para hacer click en el elemento especificado.
    //* @param locator El localizador del elemento.
    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.buscarElemento(locator).click();
    }

    //Método para enviar una tecla al elemento especificado.
    // * @param key La tecla a enviar.
    // * @param locator El localizador del elemento.
    protected void enviarTecla(CharSequence key, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.buscarElemento(locator).sendKeys(key);
    }

    //Método para obtener el texto del elemento especificado.
    //     * @param locator El localizador del elemento.
    //     * @return El texto del elemento.
    protected String obtenerTexto(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.buscarElemento(locator).getText();
    }

}


