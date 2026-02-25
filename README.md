# Projeto Spring Boot

Este é um projeto de exemplo de uma API REST desenvolvida com Spring Boot, demonstrando conceitos fundamentais de desenvolvimento backend em Java. O projeto inclui autenticação JWT, persistência de dados com JPA e MySQL, e uma arquitetura em camadas bem estruturada.

## Descrição

A aplicação é uma API RESTful que permite gerenciar produtos e usuários, com autenticação segura via JWT. Inclui funcionalidades básicas de CRUD para produtos, registro e login de usuários, além de endpoints simples para mensagens de saudação.

Este projeto foi desenvolvido como parte do aprendizado inicial em Spring Boot, focando em boas práticas de desenvolvimento, segurança e arquitetura de software.

## Tecnologias Utilizadas 

- **Java 21**: Linguagem de programação principal
- **Spring Boot 3.5.10**: Framework para desenvolvimento de aplicações Java
- **Spring Data JPA**: Para persistência de dados
- **Spring Security**: Para autenticação e autorização
- **JWT (JSON Web Tokens)**: Para autenticação stateless
- **MySQL**: Banco de dados relacional
- **Maven**: Gerenciamento de dependências e build
- **JUnit**: Para testes unitários

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 21 ou superior
- MySQL Server (versão 8.0 ou superior)
- Maven (ou use o wrapper incluído no projeto)

## Instalação e Execução

### 1. Clonagem do Repositório

```bash
git clone https://github.com/seu-usuario/meu-primeiro-springboot.git
cd meu-primeiro-springboot
```

### 2. Configuração do Banco de Dados

Certifique-se de que o MySQL está rodando localmente na porta 3306. O projeto está configurado para usar:

- Host: localhost
- Porta: 3306
- Banco: db_spring
- Usuário: root
- Senha: (vazia)

Se necessário, ajuste as configurações em `src/main/resources/application.properties`.

### 3. Build do Projeto

```bash
./mvnw clean install
```

### 4. Execução da Aplicação

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/projeto_springboot/
│   │   ├── controller/          # Controladores REST
│   │   │   ├── AuthController.java
│   │   │   └── ProdutoController.java
│   │   ├── exceptions/          # Tratamento de exceções
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── RecursoNaoEncontradoException.java
│   │   ├── model/               # Entidades JPA
│   │   │   ├── Produto.java
│   │   │   └── Usuario.java
│   │   ├── repository/          # Camada de acesso a dados
│   │   │   ├── ProdutoRepository.java
│   │   │   └── UsuarioRepository.java
│   │   ├── security/            # Configurações de segurança
│   │   │   ├── JwtAuthFilter.java
│   │   │   ├── JwtUtil.java
│   │   │   └── SecurityConfig.java
│   │   ├── service/             # Lógica de negócio
│   │   │   ├── ProdutoService.java
│   │   │   ├── UsuarioDetailsService.java
│   │   │   └── UsuarioService.java
│   │   └── MeuPrimeiroSpringbootApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/projeto_springboot/
        └── MeuPrimeiroSpringbootApplicationTests.java
```

## Funcionalidades

### Autenticação
- Registro de novos usuários
- Login com geração de token JWT
- Proteção de endpoints com autenticação obrigatória

### Gerenciamento de Produtos
- Listar todos os produtos
- Buscar produto por ID
- Criar novo produto
- Deletar produto


## API Endpoints

### Autenticação
- `POST /auth/register` - Registrar novo usuário
  - Body: `{"username": "string", "password": "string"}`
- `POST /auth/login` - Fazer login
  - Body: `{"username": "string", "password": "string"}`
  - Response: `{"token": "jwt_token"}`

### Produtos (Requer autenticação)
- `GET /api/produtos` - Listar todos os produtos
- `GET /api/produtos/{id}` - Buscar produto por ID
- `POST /api/produtos` - Criar novo produto
  - Body: `{"nome": "string", "preco": number}`
- `DELETE /api/produtos/{id}` - Deletar produto

### Outros
- `GET /api/hello` - Saudação simples
- `GET /api/mensagem` - Obter mensagem

## Testes

Para executar os testes:

```bash
./mvnw test
```

O projeto inclui testes básicos de contexto do Spring Boot.

## Arquitetura

O projeto segue uma arquitetura em camadas:

1. **Controller**: Recebe requisições HTTP e retorna respostas
2. **Service**: Contém a lógica de negócio
3. **Repository**: Acesso aos dados (JPA para entidades, custom para outros)
4. **Model**: Entidades JPA mapeadas para tabelas do banco

A segurança é implementada com Spring Security e JWT, garantindo autenticação stateless.

## Tratamento de Erros

- Exceções customizadas (`RecursoNaoEncontradoException`) para recursos não encontrados
- Handler global que retorna respostas JSON estruturadas com timestamp, status, erro e mensagem

## Desenvolvimento

### Convenções de Código
- Injeção de dependências via construtor
- Uso de anotações do Spring (@Service, @Repository, @RestController)
- Nomes de tabelas em português
- Mensagens de erro em português

### Configurações
- DDL auto-update para desenvolvimento
- Logs SQL habilitados
- Sessão HTTP com timeout de 30 minutos

## Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Autor

Desenvolvido por Jhonatan Moreira de Oliveira como projeto de aprendizado em Spring Boot.

---


Este projeto demonstra habilidades em desenvolvimento backend Java, incluindo Spring Boot, JPA, segurança com JWT, e boas práticas de arquitetura de software.
