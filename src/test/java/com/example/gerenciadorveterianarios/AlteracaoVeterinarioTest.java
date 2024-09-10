package com.example.gerenciadorveterianarios;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlteracaoVeterinarioTest {

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
        String nomeEditado = "Dr. John Doe Edited";
        String email = "DrJohnDoe@cardiologia.com";
        String especialidade = "Cardiologia";
        String salario = "1000";

        // Cadastro do veterinário
        cadastrarVeterinario(nome, email, especialidade, salario);

        // Verificação na página inicial
        driver.get("http://localhost:8080/home");
        verificarConteudoTabela(nome, email, especialidade, salario);

        // Edição do veterinário
        editarVeterinario(nome, nomeEditado);

        // Verificação após exclusão
        driver.get("http://localhost:8080/home");
        verificarConteudoTabela(nomeEditado, email, especialidade, salario);
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

    private void editarVeterinario(String nome, String nomeEditado) {
        // Navega para a página inicial onde a tabela está localizada
        driver.get("http://localhost:8080/home");

        // Localiza e clica no botão de edição do veterinário específico
        WebElement edicaoButton = driver.findElement(By.xpath("//tr/td[span[contains(text(),'" + nome + "')]]/following-sibling::td/child::a[1]"));
        edicaoButton.click();

        // Editar o nome do veterinario
        WebElement nomeEditadoInput = driver.findElement(By.cssSelector("#nome"));
        nomeEditadoInput.clear();
        nomeEditadoInput.sendKeys(nomeEditado);

        // Salva as alterações
        WebElement salvarButton = driver.findElement(By.cssSelector("button[type='submit']"));
        salvarButton.click();
    }

    private void verificarConteudoTabela(String nomeEditado, String email, String especialidade, String salario) {
        WebElement tabela = driver.findElement(By.cssSelector(".table.table-light"));
        boolean encontrado = tabela.getText().contains(nomeEditado) &&
                             tabela.getText().contains(email) &&
                             tabela.getText().contains(especialidade) &&
                             tabela.getText().contains(salario);

        assertTrue(encontrado, "Dados não encontrados na tabela.");
    }
}
