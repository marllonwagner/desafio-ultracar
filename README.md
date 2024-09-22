Desafio Ultracar

Este projeto é uma aplicação desenvolvida com Spring Boot, que utiliza PostgreSQL como banco de dados. O objetivo é gerenciar agendamentos e veículos de clientes.

Requisitos

Antes de executar o projeto, certifique-se de que você tem o Dockker Instalado.


Estrutura do Projeto

O projeto contém dois componentes principais:

Dockerfile: Usado para construir a imagem da aplicação Spring Boot.

docker-compose.yml: Usado para orquestrar a aplicação e o banco de dados PostgreSQL.

Execução

Para executar a aplicação localmente, siga os passos abaixo:

Clone o repositório:


git clone <URL do repositório>
cd <nome do repositório>

Inicie os containers com Docker Compose:

docker-compose up --build

A aplicação estará disponível em http://localhost:8080 e o banco de dados PostgreSQL em localhost:5432.

Execute a aplicação.

Considerações Finais

Certifique-se de que as portas não estejam em uso por outros serviços e que o Docker e o Docker Compose estejam funcionando corretamente.
Qualquer problema ou dúvida, sinta-se à vontade para abrir uma issue neste repositório.
