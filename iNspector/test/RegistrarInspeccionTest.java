// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class RegistrarInspeccionTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    System.setProperty("webdriver.chrome.driver", "/chromedriver");
    driver = new ChromeDriver();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void registrarInspeccion() {
    driver.get("http://localhost:8080/iNspector/");
    driver.manage().window().setSize(new Dimension(1552, 840));
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("i1");
    driver.findElement(By.name("password")).sendKeys("i1");
    driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    driver.findElement(By.cssSelector("input")).click();
    driver.findElement(By.cssSelector("form:nth-child(6) > .btn")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(3) > .btn:nth-child(2)")).click();
    driver.findElement(By.name("fecha_insp")).click();
    driver.findElement(By.name("fecha_insp")).sendKeys("2020-05-03");
    driver.findElement(By.name("nota")).click();
    {
      WebElement dropdown = driver.findElement(By.name("nota"));
      dropdown.findElement(By.xpath("//option[. = 'Favorable']")).click();
    }
    driver.findElement(By.name("nota")).click();
    driver.findElement(By.name("descripcion")).click();
    driver.findElement(By.name("descripcion")).sendKeys("Todo OK");
    driver.findElement(By.id("botonRegistrarInforme")).click();
    driver.findElement(By.cssSelector(".logo input")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".btn-danger")).click();
  }
}
