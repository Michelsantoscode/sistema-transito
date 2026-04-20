# 🚗 Sistema de Gestão de Trânsito (Transito API)

API REST desenvolvida com **Spring Boot** para gerenciamento de veículos, proprietários e autuações de trânsito.  
O projeto aplica boas práticas de arquitetura em camadas, DTOs, validações, regras de negócio e tratamento global de exceções.

---

## 📌 Funcionalidades

### 🚗 Veículos
- Cadastro de veículos
- Consulta por ID e listagem
- Controle de status (REGULAR / APREENDIDO)
- Apreensão e remoção de apreensão

### 👤 Proprietários
- Cadastro, atualização e remoção
- Consulta por ID e listagem
- Validação de e-mail único

### 🚨 Autuações
- Registro de multas vinculadas a veículos
- Listagem de autuações por veículo
- Controle automático de data da ocorrência

---

## 🧱 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas baseada em boas práticas de **DDD simplificado**:


---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation (Jakarta Validation)
- ModelMapper
- Lombok
- Banco de dados relacional (PostgreSQL/MySQL)

---

## 📦 Estrutura do Projeto

com.michel.transito
│
├── api
│ ├── controller
│ ├── model
│ ├── model/input
│ └── assembler
│
├── domain
│ ├── model
│ ├── repository
│ ├── service
│ └── exception
│
└── common
└── configuration


---

## ⚙️ Regras de Negócio

- Veículo não pode ser cadastrado com ID existente
- Placa de veículo deve ser única
- E-mail de proprietário deve ser único
- Veículo não pode ser apreendido duas vezes
- Não é possível remover apreensão de veículo regular
- Autuação sempre vinculada a um veículo existente

---

## 🚨 Tratamento de Exceções

O sistema possui um handler global com `@RestControllerAdvice`.

### Tipos tratados:

- `NegocioException` → regra de negócio (400)
- `EntidadeNaoEncontradaException` → recurso não encontrado (404)
- `DataIntegrityViolationException` → conflito de dados (409)
- `MethodArgumentNotValidException` → erro de validação (400)

---

## 📡 Endpoints principais

### 🚗 Veículos

GET /veiculos
GET /veiculos/{id}
POST /veiculos
PUT /veiculos/{id}/apreensao
DELETE /veiculos/{id}/apreensao

### 👤 Proprietários

GET /proprietarios
GET /proprietarios/{id}
POST /proprietarios
PUT /proprietarios/{id}
DELETE /proprietarios/{id}

### 🚨 Autuações

GET /veiculos/{veiculoId}/autuacoes
POST /veiculos/{veiculoId}/autuacoes


---

## 📊 Modelo de Domínio

### Veículo
- id
- marca
- modelo
- placa
- status
- data de cadastro
- data de apreensão

### Proprietário
- id
- nome
- e-mail
- telefone

### Autuação
- id
- descrição
- valor da multa
- data da ocorrência

---

## 🚀 Como executar o projeto

### Pré-requisitos
- Java 17+
- Maven
- Banco de dados configurado

### Passos

```bash
git clone <url-do-repositorio>
cd transito-api
mvn spring-boot:run

📌 Status do projeto

✔ Estrutura base concluída
✔ Regras de negócio implementadas
✔ API funcional
🔄 Em evolução contínua

👨‍💻 Autor

Desenvolvido por Michel
Projeto focado em aprendizado de arquitetura backend com Spring Boot.
