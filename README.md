# Gerenciador Veterinários

Este é um projeto Spring Boot para gerenciar veterinários, incluindo funcionalidades para listar, adicionar, atualizar e remover registros de veterinários.

## Estrutura do Projeto

- **Controller**: Contém classes que gerenciam as requisições HTTP.
  - `HomeController`: Gerencia a página inicial e as pesquisas por veterinários.
  - `VeterinarioController`: Gerencia as operações de CRUD (Create, Read, Update, Delete) para veterinários.
  
- **Entities**: Contém as entidades JPA que representam as tabelas no banco de dados.
  - `Veterinario`: Representa a tabela `tb_veterinario`.

- **Repository**: Contém os repositórios que fazem a interface com o banco de dados.
  - `VeterinarioRepository`: Interface que estende `JpaRepository` para operações básicas de CRUD e consulta personalizada.

- **Application**: Contém a classe principal para inicialização da aplicação.
  - `GerenciadorVeterinariosApplication`: Classe principal que contém o método `main` para iniciar a aplicação Spring Boot.

## Requisitos

- **Java**: JDK 11 ou superior
- **Maven**: 3.6.0 ou superior

## Configuração

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/rafael-iftm/projeto-veterinario-springboot.git
   ```

2. **Navegue até o diretório do projeto:**

   ```bash
   cd projeto-veterinario-springboot
   ```

3. **Configure o banco de dados no arquivo src/main/resources/application.properties:**

   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
   ```

## Construção e Execução

1. **Compile e construa o projeto:**

   ```bash
   mvn clean install
   ```

2. **Execute a aplicação:**

   ```bash
   mvn spring-boot:run
   ```

## Endpoints

- **GET /home**: Lista todos os veterinários ou pesquisa por nome se o parâmetro `nome` for fornecido.
- **GET /find**: Exibe o formulário de busca de veterinários.
- **GET /form**: Exibe o formulário para adicionar um novo veterinário.
- **POST /add**: Adiciona um novo veterinário.
- **GET /form/{id}**: Exibe o formulário para editar um veterinário existente.
- **POST /update/{id}**: Atualiza um veterinário existente.
- **GET /delete/{id}**: Remove um veterinário existente.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Thymeleaf (para templates HTML)
- MySQL (ou outro banco de dados relacional compatível)

