# Sistema de Gerenciamento de Biblioteca - DDD em Java

Projeto desenvolvido como parte de um exercício de aplicação prática dos conceitos de **Domain-Driven Design (DDD)** com **padrões de projeto GoF** em Java. O sistema modela uma biblioteca com agregados como `Livro`, `Membro` e `Empréstimo`, utilizando padrões como **Factory**, **Repository**, **Strategy**, **State** e **Observer** para refletir regras reais do domínio bibliotecário.

## 🔍 Visão geral do sistema

O sistema gerencia o ciclo de vida de livros, membros e empréstimos em uma biblioteca. Ele modela o comportamento do domínio através de agregados, garantindo regras de negócio como:

* Um membro pode pegar no máximo X livros emprestados simultaneamente.
* Um empréstimo só é criado se a cópia do livro estiver disponível.
* Políticas de multa podem variar (por tipo de membro).
* Eventos de domínio como `LivroEmprestado` e `LivroDevolvido` podem notificar serviços externos.

### 📊 Diagrama de agregados e responsabilidades

[![](https://mermaid.ink/img/pako\:eNp9j81uwjAQhF9l5yoJoZzKY4EKJEEakYKmG4uKkN44BaHSkYxYeUal3HdVFMExR-a0nM-8srjcMSoWTQ9npUq2mXpiwDR0YQbGf8b-ZP0fLIfvGPvMJfrpmKZ3vOz3bXCRBhKUCEjMIOW8GpYuXTCuxD2FzrbHkP1wh5AtfcOeCLPfBPqhvlRbA1oOpl5kYHCEBeCCxO8EzGmF47AwB2lh2rzwkpyJhR-f6rPgORq97u?type=png)](https://mermaid.live/edit#pako:eNp9j81uwjAQhF9l5yoJoZzKY4EKJEEakYKmG4uKkN44BaHSkYxYeUal3HdVFMExR-a0nM-8srjcMSoWTQ9npUq2mXpiwDR0YQbGf8b-ZP0fLIfvGPvMJfrpmKZ3vOz3bXCRBhKUCEjMIOW8GpYuXTCuxD2FzrbHkP1wh5AtfcOeCLPfBPqhvlRbA1oOpl5kYHCEBeCCxO8EzGmF47AwB2lh2rzwkpyJhR-f6rPgORq97u)

---

### ⚙️ Tecnologias Utilizadas

* [Java 17](https://www.oracle.com/java/)
* [JUnit 5](https://junit.org/junit5/) – Testes
* [Maven](https://maven.apache.org/) – Gerenciamento de dependências
* [Mermaid.js](https://mermaid.js.org/) – Diagramas (documentação)
* [Lombok](https://projectlombok.org/) – Redução de boilerplate (opcional)

---

## 🧩 Estrutura do Projeto

```
src/
├── domain/
│   ├── model/
│   │   ├── livro/                # Agregado Livro (Raiz: Livro)
│   │   ├── membro/               # Agregado Membro (Raiz: Membro)
│   │   ├── emprestimo/           # Agregado Empréstimo (Raiz: Emprestimo)
│   │   ├── shared/               # Objetos de Valor (ISBN, Endereco, etc.)
│   │   └── repository/           # Interfaces de repositório
│   └── domain.service/                  # Serviços de domínio
├── infrastructure/
│   └── persistence/              # Implementações em memória dos repositórios
├── application/                  # Casos de uso e serviços de aplicação
└── Main.java                     # Classe principal (demonstração)
```

---

## ✅ Como rodar o projeto

Requisitos:

* Java 17
* Maven 3.8+

Passos:

```bash
git clone https://github.com/seu-usuario/sistema-biblioteca-ddd.git
cd sistema-biblioteca-ddd
mvn clean install
```

Depois, para executar a aplicação principal:

```bash
mvn exec:java -Dexec.mainClass="Main"
```

Você verá uma saída no console simulando o fluxo de criação de membros, empréstimos e devoluções.

---

## 🧪 Como rodar os testes

Para executar todos os testes da aplicação:

```bash
mvn test
```

Você pode adicionar testes para garantir as invariantes do domínio, como:

* Criar empréstimo com cópia indisponível deve lançar exceção.
* Membro com limite estourado não pode pegar novo livro.

---

## 📌 Rotas (caso use API futura)

> ⚠️ Por enquanto, o sistema é apenas um console app. Se for evoluído para uma API, rotas REST como abaixo podem ser adicionadas:

| Método | Rota                | Descrição                |
| ------ | ------------------- | ------------------------ |
| GET    | /livros/disponiveis | Lista livros disponíveis |
| POST   | /emprestimos        | Cria um novo empréstimo  |
| PUT    | /emprestimos/{id}   | Processa devolução       |

---

## ⚠️ Problemas enfrentados

### Problema 1: Modelar regras de negócio complexas sem misturar responsabilidades

* **Solução:** Utilizar serviços de domínio e `Factory` para encapsular a lógica de criação de empréstimos.

### Problema 2: Controle de estado de cópias de livro e empréstimos

* **Solução:** Aplicação do **State Pattern** para encapsular os estados (`Disponível`, `Emprestada`, `Atrasada`, etc.)

---

## ⏭️ Próximos passos

* [ ] Criar interface REST usando Spring Boot
* [ ] Persistência real com PostgreSQL ou MongoDB
* [ ] Integração com sistema de notificações (Observer/Event)
* [ ] Interface gráfica (JavaFX ou Web)
---