# BelongWorkAPI

API desenvolvida para o projeto da disciplina **SOA & Web Services** (FIAP).  
O sistema implementa autenticação JWT, controle de acesso por perfis e endpoints REST .

---
## Tema do projeto da GS

O Belong Work é uma plataforma digital inovadora que tem como missão promover inclusão e capacitação profissional para pessoas com deficiência (PCDs), facilitando o acesso ao mercado de trabalho por meio da tecnologia. O aplicativo conecta candidatos, empresas e programas de formação inclusiva, oferecendo trilhas personalizadas de aprendizado, oportunidades profissionais e acompanhamento do desenvolvimento de cada usuário.
Para tornar essa experiência mais eficiente, o Belong Work utiliza Inteligência Artificial (IA) como aliada no processo de inclusão. Por meio de algoritmos de recomendação, a plataforma identifica trilhas de capacitação adequadas ao perfil, ritmo e necessidades do usuário, além de sugerir vagas compatíveis com suas habilidades e interesses. A IA também atua na acessibilidade, oferecendo assistentes virtuais, leitura de tela otimizada e recursos de linguagem natural que tornam o aprendizado mais acessível e adaptado a diferentes deficiências.

<img width="430" height="324" alt="image" src="https://github.com/user-attachments/assets/2e0a9953-3328-4d04-ae59-a38691b135af" />


---
## 👨‍💻 Integrantes do Grupo

| Nome | RM |
|------|----|
| Bernardo Pinto Rocha | 99209 |
| Pedro Palladino | 551180 |
| Renato Izumi | 99242 |

---

## LINK DO GITHUB
- [Link para o Github](https://github.com/BernardoPRocha/BelongWorkAPI.git)

---

O projeto representa um exemplo de microsserviço que:
- Possui endpoints REST;
- Implementa autenticação via JWT;
- Mantém política de sessão `STATELESS`;
- Aplica boas práticas de modularização e reutilização de serviços.

---

## 🏗️ Estrutura do Projeto

![img_4.png](img_4.png)

---

## ⚙️ Tecnologias Utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot 3.2.5**
- 🔐 **Spring Security + JWT (0.11.5)**
- 🐬 **MySQL**
- 🧱 **Maven**
- 💾 **JPA / Hibernate**

---

## 🔑 Segurança e Autenticação

A aplicação utiliza **Spring Security com autenticação JWT**:

- Login via `/api/auth/login`;
- Tokens assinados com `HS256`;
- Sessões `STATELESS`;
- Controle de autorização baseado em **roles (ADMIN, USER)**;
- Filtros de segurança configurados manualmente via `SecurityConfig`.

---

## 🚀 Como Executar o Projeto

### 1️⃣ Clonar o Repositório
```bash
git clone https://github.com/BernardoPRocha/BelongWorkAPI.git
cd BelongWorkAPI
```

### 2️⃣ Configurar o Banco de Dados
Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/belongworkdb
spring.datasource.username=root
spring.datasource.password=fiap1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Executar o Projeto
```bash
mvn spring-boot:run
```

A aplicação iniciará em:  
➡️ **http://localhost:8080**

---

## 🧩 Endpoints da API

### 🔸 Autenticação
**POST** `/api/auth/login`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "username": "admin"
}
```

---

## 🧩 Fotos do MySQL

![img_1.png](img_1.png)
![img_2.png](img_2.png)




#### Exemplo de Cabeçalho
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2MzAxMDg1NSwiZXhwIjoxNzYzMDQ2ODU1fQ.tnKvx9gGvfZJes91dDGhfPNQDYBobZVoalQBr2Wk-0s
```

#### Exemplo de POST `http://localhost:8080/api/auth/login`
```json
{
  "type": "Bearer",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NDQxNjk1MSwiZXhwIjoxNzY0NDUyOTUxfQ.05nX92xcRAyHXoAVfo95jwruhQIxgA8sP7E5pd8cVzQ",
  "username": "admin"
}
```
#### Exemplo de GET `http://localhost:8080/api/opportunities`
```json
{
  "id": 1,
  "title": "Desenvolvedor Java Jr",
  "company": "Tech Inclusiva",
  "description": "Vaga com adaptação para PCDs",
  "requiredSkills": "java,spring,sql",
  "accessible": true
}
```
---
#### Exemplo de POST Candidates `http://localhost:8080/api/candidates`
![img_7.png](img_7.png)
---
#### Exemplo de GET por ID `http://localhost:8080/api/candidates/2`
![img_8.png](img_8.png)
---
## Explicação da IA de recomendação 
A recomendação compara as skills do candidato com as skills requeridas pelas oportunidades, 
calculando uma pontuação simples e retornando as vagas compatíveis. Isso simula a camada de IA descrita no tema.
---

## ⚖️ Critérios Atendidos

| Critério | Descrição | Peso | Status |
|-----------|------------|------|--------|
| Entities, VO, Enums, DTO, Controllers | Estrutura completa | 5% | ✅ |
| ResponseEntity padronizado | Implementado | 5% | ✅ |
| Tratamento global de exceções | Classe `Advice` | 10% | ✅ |
| Autenticação de usuário | Login JWT | 10% | ✅ |
| Autorização por perfil | Roles no UserDetails | 20% | ✅ |
| Política Stateless e JWT | Implementada | 20% | ✅ |
| Casos de uso e regras de negócio | Serviços isolados | 20% | ✅ |
| Organização modular e reuso | Padrão SOA | 10% | ✅ |

---

## 📚 Referências

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [JJWT Library (io.jsonwebtoken)](https://github.com/jwtk/jjwt)
- [MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/)

---

## 🧠 Conclusão

O projeto **BelongWorkAPI** demonstra a aplicação prática dos conceitos de **SOA**, **segurança de APIs REST**, e **autenticação JWT**.  
A arquitetura modular garante fácil expansão e integração com novos serviços ou microsserviços futuros — tornando-o escalável e didático.

---

## 🏁 Status do Projeto
✅ **Concluído e funcional**

Todos os módulos foram implementados, testados no Postman e conectados ao MySQL com sucesso.
```
Tomcat started on port(s): 8080
Application started successfully!
```
