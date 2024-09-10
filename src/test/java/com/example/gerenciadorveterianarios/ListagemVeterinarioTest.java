package com.example.gerenciadorveterianarios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListagemVeterinarioTest {
    
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCadastrarVeterinario() {
        // Dados para o teste
        String nome = "Dr. John Doe";
        String email = "DrJohnDoe@cardiologia.com";
        String especialidade = "Cardiologia";
        String salario = "1000";

        // Cadastro do veterinário
        cadastrarVeterinario(nome, email, especialidade, salario);

        // Verificação na página inicial
        driver.get("http://localhost:8080/home");
        verificarConteudoTabela(nome, email, especialidade, salario);

        // Consulta do veterinário
        driver.get("http://localhost:8080/find");
        consultarVeterinario(nome);
        verificarConteudoTabela(nome, email, especialidade, salario);
    }

    private void cadastrarVeterinario(String nome, String email, String especialidade, String salario) {
        driver.get("http://localhost:8080/form");

        WebElement nomeInput = driver.findElement(By.cssSelector("#nome"));
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

    private void consultarVeterinario(String nome) {
        WebElement findNomeInput = driver.findElement(By.cssSelector("#nome"));
        findNomeInput.sendKeys(nome);

        WebElement consultarButton = driver.findElement(By.cssSelector("button[type='submit']"));
        consultarButton.click();
    }

    private void verificarConteudoTabela(String nome, String email, String especialidade, String salario) {
        WebElement tabela = driver.findElement(By.cssSelector(".table.table-light"));
        boolean encontrado = tabela.getText().contains(nome) &&
                             tabela.getText().contains(email) &&
                             tabela.getText().contains(especialidade) &&
                             tabela.getText().contains(salario);

        assertTrue(encontrado, "Dados não encontrados na tabela.");
    }
}
