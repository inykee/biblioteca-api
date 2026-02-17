<br>

# 📚 Biblioteca API

API RESTful para gerenciamento de biblioteca, permitindo operações CRUD para clientes, livros, exemplares e empréstimos.
Desenvolvida com Java 21, Spring Boot 3, JPA/Hibernate e banco de dados H2 persistente em arquivo.

<br>

# 🛠️ Tecnologias Utilizadas

- Java 21 LTS
- Spring Boot 3
- Spring Data JPA / Hibernate
- H2 Database
- Maven
- IntelliJ IDEA
- Bean Validation (Jakarta Validation / Hibernate Validator)

<br>

# ⚙️ Configuração do Projeto

1. Certifique-se de que o IntelliJ IDEA está instalado.
2. Verifique se o Maven está configurado corretamente.
3. Abra o projeto no IntelliJ IDEA e aguarde o Maven baixar as dependências.

<br>

# 🚀 Executando a API

Para iniciar a API, basta executar a classe principal: `bibliotecaAPI/src/main/java/com/example/bibliotecaAPI/BibliotecaApiApplication.java`

Para testar os endpoints (GET, POST, PUT, DELETE), você pode usar qualquer ferramenta de requisições HTTP, como Postman ou Insomnia.
O banco H2 é persistente e armazenado na pasta data/ na raiz do projeto. Não é necessário configurar nada adicional.

<br>

# 🧰 Detalhes Técnicos

## Estrutura do Projeto

```
biblioteca-api/
│
├─ data/                         # Banco H2 persistente (.mv.db e .trace.db)
├─ src/
│   ├─ main/java/com/example/bibliotecaapi/
│   │   ├─ configs/              # Configurações (H2, Swagger, etc.)
│   │   ├─ controllers/          # Controllers REST
│   │   ├─ dtos/                 # DTOs para requests/responses
│   │   ├─ enums/                # Enums (situação, estado, categoria)
│   │   ├─ exceptions/           # Tratamento global de erros
│   │   ├─ models/               # Entidades JPA
│   │   ├─ repositories/         # Repositórios JPA
│   │   └─ services/             # Lógica de negócio
│   └─ resources/
│       ├─ application.yaml
└─ pom.xml
```

<br>

## Operações CRUD

| Método | URL Base        | Descrição                   |
|--------|----------------|-----------------------------|
| GET    | /clientes      | Listar todos os clientes    |
| GET    | /clientes/{id} | Consultar cliente por ID    |
| POST   | /clientes      | Criar novo cliente          |
| PUT    | /clientes/{id} | Atualizar cliente existente |
| DELETE | /clientes/{id} | Remover cliente             |

Mesma lógica aplicada para /livros e /exemplares.

<br>

## Empréstimos

CRUD parcialmente disponível:
- GET → consulta empréstimos
- GET → consulta empréstimo por ID
- POST → cria um novo empréstimo
- PUT → ❌ não disponível (alteração de empréstimos não permitida)
- DELETE → cancela/deleta um empréstimo

<br>

## Regras de negócio

1. Não permite criar empréstimo para clientes BLOQUEADOS
2. Não permite criar empréstimo para exemplares que não estão DISPONÍVEIS

<br>

## Valores Possíveis para Enums

Clientes – situacao
- ATIVO
- BLOQUEADO

Exemplares – estado
- DISPONIVEL
- RESERVADO
- INDISPONIVEL

Livros – categoria
- FICCAO, FICCAO_CIENTIFICA, FANTASIA, ROMANCE, AVENTURA, MISTERIO, TERROR
- BIOGRAFIA, HISTORIA, EDUCACAO, FILOSOFIA, RELIGIAO, AUTOAJUDA, NEGOCIOS
- TECNOLOGIA, CIENCIAS, SAUDE, PSICOLOGIA, ARTE, POESIA, INFANTIL, JUVENIL, HQ

<br>

## Exemplos de Requisição POST

Cliente
```
{
  "nomeCompleto": "Ana Beatriz Souza",
  "cpf": "123.456.789-00",
  "telefone": "+55 (11) 91234-5678",
  "email": "ana.souza@example.com",
  "situacao": "ATIVO"
}
```

Livro
```
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "anoPublicacao": 2008,
  "isbn": "9780132350884",
  "categoria": "TECNOLOGIA"
}
```

Exemplar
```
{
  "idLivro": 1,
  "referencia": 101,
  "estado": "DISPONIVEL"
}
```

Empréstimo
```
{
  "idExemplar": 1,
  "idCliente": 1,
  "dataEmprestimo": "2026-02-10",
  "dataDevolucao": "2026-02-20"
}
```

<br>

# 📜 Licença

Este projeto é licenciado sob a Licença MIT.

<br>

# 📬 Contato

Para dúvidas, sugestões ou feedback, entre em contato pelo e-mail: inykee.github@gmail.com

<br>
