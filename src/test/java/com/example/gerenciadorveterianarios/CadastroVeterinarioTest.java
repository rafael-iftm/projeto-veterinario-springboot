package com.example.gerenciadorveterianarios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadastroVeterinarioTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10); // Espera de até 10 segundos
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCadastrarVeterinario() {
        String nome = "Dr. John Doe";
        String email = "DrJohnDoe@cardiologia.com";
        String especialidade = "Cardiologia";
        String salario = "1000";

        // Cadastro do veterinário
        cadastrarVeterinario(nome, email, especialidade, salario);

        // Verificação na página inicial
        driver.get("http://localhost:8080/home");
        verificarConteudoTabela(nome, email, especialidade, salario);
    }

    private void cadastrarVeterinario(String nome, String email, String especialidade, String salario) {
        driver.get("http://localhost:8080/form");

        WebElement nomeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nome")));
        WebElement emailInput = driver.findElement(By.cssSelector("#inputEmail"));
        WebElement especialidadeInput = driver.findElement(By.cssSelector("#inputEspecialidade"));
        WebElement salarioInput = driver.findElement(By.cssSelector("#inputSalario"));
        WebElement cadastrarButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nomeInput.sendKeys(nome);
        emailInput.sendKeys(email);
        especialidadeInput.sendKeys(especialidade);
        salarioInput.sendKeys(salario);
        cadastrarButton.click();
    }

    private void verificarConteudoTabela(String nome, String email, String especialidade, String salario) {
        WebElement tabela = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table.table-light")));
        String tabelaTexto = tabela.getText();

        assertTrue(tabelaTexto.contains(nome), "Nome não encontrado na tabela.");
        assertTrue(tabelaTexto.contains(email), "Email não encontrado na tabela.");
        assertTrue(tabelaTexto.contains(especialidade), "Especialidade não encontrada na tabela.");
        assertTrue(tabelaTexto.contains(salario), "Salário não encontrado na tabela.");
    }
}
