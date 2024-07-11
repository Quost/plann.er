# 🚀 Plann.er - NLW 16: Journey

## Descrição

O **Plann.er** é um projeto desenvolvido durante o Next Level Week 16 da Rocketseat. O objetivo deste projeto é ajudar os usuários a organizar suas viagens, sejam elas a trabalho ou lazer, permitindo que criem viagens, adicionem atividades e compartilhem links importantes com os participantes.

## Funcionalidades

### Viagens
- Cadastro de viagens com destino, data de início e término, e-mails dos convidados, nome e e-mail do organizador.
- Confirmação de viagem por e-mail.
- Adição de novos participantes e confirmação de presença.
- Consulta de informações sobre a viagem.

### Atividades
- Cadastro de atividades dentro de uma viagem com título, data e horário.
- Consulta das atividades de uma viagem.

### Links
- Adição de links importantes relacionados à viagem, como reservas de hospedagem e locais a serem visitados.
- Consulta dos links de uma viagem.

## Requisitos

- Java 11+
- Spring Boot
- Banco de Dados H2
- Maven

## Estrutura do Projeto

- **Entidades:**
  - `Trip`
  - `Participant`
  - `Activity`
  - `Link`

- **Endpoints:**
  - **Trips:**
    - `POST /trips` - Cadastro de viagem
    - `GET /trips/{tripId}` - Consulta de viagem
    - `PUT /trips/{tripId}` - Atualização de viagem
    - `GET /trips/{tripId}/confirm` - Confirmação de viagem
    - `POST /trips/{tripId}/invites` - Convite de participantes
    - `GET /trips/{tripId}/participants` - Consulta de participantes
    - `POST /trips/{tripId}/activities` - Cadastro de atividades
    - `GET /trips/{tripId}/activities` - Consulta de atividades
    - `POST /trips/{tripId}/links` - Criação de links
    - `GET /trips/{tripId}/links` - Consulta de links

## Configuração do Ambiente

1. Clone o repositório:
   ```sh
   git clone https://github.com/Quost/planner.git
   ```
2. Navegue até o diretório do projeto:
   ```sh
   cd planner
   ```
3. Configure o banco de dados H2 no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:h2:mem:planner
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   ```
4. Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

## Como Usar

1. Acesse o console do H2 para verificar as tabelas criadas e dados inseridos.
2. Utilize ferramentas como Postman ou Insomnia para testar os endpoints da API.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Contato

Matheus Quost - [LinkedIn](https://www.linkedin.com/in/matheusquost) - [GitHub](https://github.com/Quost)
````​
