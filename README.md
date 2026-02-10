## 📚 Biblioteca API
Uma API RESTful para gerenciamento de biblioteca, permitindo operações CRUD para clientes, livros, exemplares e empréstimos.
Desenvolvido com Java, Spring Boot, JPA/Hibernate e banco de dados H2.

<br><br><br>

## 🛠️ Tecnologias Utilizadas
- Java 21 LTS
- Spring Boot
- JPA/Hibernate
- H2 Database
- Maven
- IntelliJ IDEA

<br><br><br>

## ⚙️ Configuração do Projeto
1. Certifique-se de que o IntelliJ IDEA está instalado.
2. Verifique se o Maven está configurado corretamente.
3. Abra o projeto no IntelliJ IDEA e aguarde o Maven baixar as dependências.

<br><br><br>

## 🚀 Executando a API
Para iniciar a API, execute a classe principal do projeto `com.example.bibliotecaAPI.BibliotecaApiApplication`. As rotas para gerenciamento de clientes, livros, empréstimos e exemplares estarão disponíveis conforme definido nos controladores.

**Dica:** Para testar as rotas da API, você pode usar ferramentas como **Postman** ou **Insomnia**, enviando requisições GET, POST, PUT e DELETE para os endpoints correspondentes.

<br><br><br>

## 🔍 Operações CRUD - Todos os Recursos
| Método | URL Base        | Descrição                     |
|--------|----------------|-------------------------------|
| GET    | /{recurso}     | Listar todos os registros     |
| GET    | /{recurso}/{id}| Consultar registro por ID     |
| POST   | /{recurso}     | Criar novo registro           |
| PUT    | /{recurso}/{id}| Atualizar registro existente  |
| DELETE | /{recurso}/{id}| Remover registro              |

<br>

## Exemplos de recurso:
- /clientes
- /livros
- /exemplares
- /emprestimos

<br>

<details><summary>Exemplo de requisição POST para cada recurso</summary>

<br>

Clientes
```
{
"nomeCompleto": "Ana Beatriz Souza",
"cpf": "123.456.789-00",
"telefone": "+55 (11) 91234-5678",
"email": "ana.souza@example.com",
"situacao": "APTO"
}
```

<br>

Livros
```
{
"titulo": "Clean Code",
"autor": "Robert C. Martin",
"anoPublicacao": 2008,
"isbn": "978-0132350884",
"categoria": "TECNOLOGIA"
}
```

<br>

Exemplares
```
{
"idLivro": 1,
"referencia": 101,
"estado": "DISPONIVEL"
}
```

<br>

Empréstimos
```
{
"idExemplar": 1,
"idCliente": 1,
"dataEmprestimo": "2026-02-10",
"dataDevolucao": "2026-02-20"
}
```
</details>

<br><br><br>

## 📜 Licença
Este projeto é licenciado sob a Licença MIT.

<br><br><br>

## 📬 Contato
Para dúvidas, sugestões ou feedback, entre em contato pelo e-mail: inykee.github@gmail.com
