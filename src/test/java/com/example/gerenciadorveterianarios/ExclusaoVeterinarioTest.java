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

public class ExclusaoVeterinarioTest {

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
    public void testExcluirVeterinario() {
        String nome = "Dr. John Doe";
        String email = "DrJohnDoe@cardiologia.com";
        String especialidade = "Cardiologia";
        String salario = "1000";

        // Cadastro do veterinário
        cadastrarVeterinario(nome, email, especialidade, salario);

        // Verificação na página inicial
        driver.get("http://localhost:8080/home");
        verificarConteudoTabela(nome, email, especialidade, salario);

        // Exclusão do veterinário
        excluirVeterinario(nome);

        // Verificação após exclusão
        driver.get("http://localhost:8080/home");
        verificarNaoConteudoTabela(nome, email, especialidade, salario);
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

    private void excluirVeterinario(String nome) {
        // Navega para a página inicial onde a tabela está localizada
        driver.get("http://localhost:8080/home");

        // Localiza e clica no botão de exclusão do veterinário específico
        WebElement excluirButton = driver.findElement(By.xpath("//tr/td[span[contains(text(),'" + nome + "')]]/following-sibling::td/child::a[2]"));
        excluirButton.click();
    }

    private void verificarConteudoTabela(String nome, String email, String especialidade, String salario) {
        WebElement tabela = driver.findElement(By.cssSelector(".table.table-light"));
        boolean encontrado = tabela.getText().contains(nome) &&
                             tabela.getText().contains(email) &&
                             tabela.getText().contains(especialidade) &&
                             tabela.getText().contains(salario);

        assertTrue(encontrado, "Dados não encontrados na tabela.");
    }

    private void verificarNaoConteudoTabela(String nome, String email, String especialidade, String salario) {
        WebElement tabela = driver.findElement(By.cssSelector(".table.table-light"));
        boolean encontrado = tabela.getText().contains(nome) ||
                             tabela.getText().contains(email) ||
                             tabela.getText().contains(especialidade) ||
                             tabela.getText().contains(salario);

        assertTrue(!encontrado, "Dados ainda encontrados na tabela após exclusão.");
    }
}
