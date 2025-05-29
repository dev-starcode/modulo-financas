````markdown
# StraCode Finance - Módulo Financeiro

Este é o módulo financeiro da aplicação da Empresa Júnior do IFMA - Campus Santa Inês, desenvolvido por
**Henrique Coqueiro**. A API foi criada para gerir informações financeiras como contas a pagar, contas a receber, métodos de pagamento e categorias financeiras.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **PostgreSQL**
* **Hibernate / JPA**
* **JUnit 5** e **Mockito** (testes unitários)
* **Jakarta Validation**
* **RESTful APIs**

---

## 💲 Recursos da API

A aplicação possui os seguintes recursos expostos:

### 🔹 Payment Method

- `POST /payment-method`: Cria um novo método de pagamento  
- `GET /payment-method`: Lista todos os métodos de pagamento  
- `PUT /payment-method/{id}`: Atualiza um método de pagamento  
- `DELETE /payment-method/{id}`: Remove um método de pagamento  

Exemplo de `POST`:

```json
{
  "name": "Cartão de Crédito"
}
````

---

### 🔹 Account Receivables (Contas a Receber)

* `POST /account-receivables`: Cria uma nova conta a receber
* `GET /account-receivables`: Lista todas as contas a receber
* `PUT /account-receivables/{id}`: Atualiza uma conta a receber
* `DELETE /account-receivables/{id}`: Remove uma conta a receber

Exemplo de `POST`:

```json
{
  "description": "Venda produto X",
  "amount": 150.00,
  "dueDate": "2025-06-10",
  "paymentMethodId": "uuid"
}
```

---

### 🔹 Account Payables (Contas a Pagar)

* `POST /account-payables`: Cria uma nova conta a pagar
* `GET /account-payables`: Lista todas as contas a pagar
* `PUT /account-payables/{id}`: Atualiza uma conta a pagar
* `DELETE /account-payables/{id}`: Remove uma conta a pagar

Exemplo de `POST`:

```json
{
  "description": "Compra de material",
  "amount": 80.00,
  "dueDate": "2025-06-05",
  "paymentMethodId": "uuid"
}
```

---

### 🔹 Account Categories

* `POST /account-categories`: Cria uma nova categoria
* `GET /account-categories`: Lista todas as categorias
* `PUT /account-categories/{id}`: Atualiza uma categoria
* `DELETE /account-categories/{id}`: Remove uma categoria

Exemplo de `POST`:

```json
{
  "name": "Serviços",
  "type": "RECEITA"
}
```

---

## 🧠 Estrutura do Domínio

O domínio da aplicação é composto por cinco entidades principais:

### `PaymentMethod`

Método de pagamento utilizado em uma transação (ex: PIX, Cartão).

* `paymentMethodId` (UUID)
* `name`: Nome do método
* `amount`: Valor relacionado
* `createdAt`: Timestamp automático

---

### `AccountCategories`

Define categorias de receitas e despesas.

* `accountCategoriesId` (UUID)
* `name`: Nome da categoria
* `description`: Descrição
* `createdAt`: Timestamp automático

---

### `Account` (Classe Abstrata)

Contém campos comuns às contas a pagar e a receber.

* `description`, `amount`, `dueDate`, `status`
* `accountCategoryId`, `paymentMethodId`

---

### `AccountReceivables`

Conta a receber de um cliente.

* `accountReceivablesId` (UUID)
* `receivedDate`: Quando foi recebido
* `clientId`: Identificação do cliente

---

### `AccountPayables`

Conta a pagar para um fornecedor.

* `accountPayableId` (UUID)
* `paymentDate`: Quando foi pago
* `supplierId`: Identificação do fornecedor

---

## 📁 Estrutura de Pastas

```
src/
└── main/
    └── java/
        └── com/
            └── StraCode/
                └── finance/
                    └── domain/
                        └── model/
                            ├── Account.java
                            ├── AccountPayables.java
                            ├── AccountReceivables.java
                            ├── PaymentMethod.java
                            └── AccountCategories.java
```

---

## 🔧 Como Executar Localmente

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/stracode-finance.git
cd stracode-finance
```

2. Configure o `application.properties` com suas credenciais do PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finance
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Execute com o Maven ou Spring Boot:

```bash
./mvnw spring-boot:run
```

A aplicação estará acessível em: `http://localhost:8080`

---

## ✅ Testes Unitários

Os testes estão localizados na pasta `src/test/java` e utilizam **JUnit 5** e **Mockito**.

Execute os testes com:

```bash
./mvnw test
```
