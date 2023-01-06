# Microsserviço de envio de e-mail com Spring Boot
## Objetivo do projeto
Implementar um microsserviço de envio de e-mail utilizando Spring Boot e Spring Mail. Para simular o envio de e-mail, foi utilizado o [Mailtrap](https://mailtrap.io/), que disponibiliza um servidor SMTP para testes.
## O que é um microserviço?
Microsserviço é um padrão de arquitetura de software segundo o qual uma aplicação é construída como um conjunto de pequenos serviços que se comunicam entre si através de APIs.
Cada microsserviço possui responsabilidades muito bem definidas e é independente dos demais, de modo que eventual falha é isolada e não afeta o funcionamento dos demais.
Idealmente, cada microsserviço é a menor parte possível de um recurso da aplicação e utilizam bases de dados distintas. São flexíveis, altamente escaláveis e podem ser utilizados pelos demais serviços.
Em contraposição, o padrão arquitetural monolítico consiste em uma única aplicação com todas as funcionalidades do sistema em um grande bloco de código que é executado em um único processo e possui todas as responsabilidades do sistema.
O monolito é mais fácil de ser desenvolvido e mantido, porém possui baixa flexibilidade e escalabilidade, assim como altíssimo acoplamemto.
## Casos de uso
Este microsserviço é responsável por enviar e-mails aos usuários do sistema, comunicando-lhes a respeito de eventos ocorridos que sejam de seu interesse.
É possível ainda criar uma camada mais elevada, que seria responsável por gerenciar notificações em geral, e que se comunicaria com este microsserviço de e-mails. Também é possível tornar este microsserviço compatível com mensagens assíncronas, utilizando mensagerias. Esse recurso, porém, ainda não foi implementado.
Ainda, neste momento não é possível anexar arquivos aos e-mails enviados.
- Para enviar um e-mail, deve ser feita uma requisição ```POST``` para o endpoint ```/send``` com os seguintes parâmetros em seu corpo (no formato JSON):
  - ```ownerRef``` - Referência do usuário ou serviço que enviou o e-mail pelo sistema
  - ```emailFrom``` - Endereço de e-mail do remetente
  - ```emailTo``` - Endereço de e-mail do destinatário
  - ```subject``` - Assunto do e-mail
  - ```text``` - Corpo do e-mail 
  - Em resposta, a API deve retornar o ```status code``` ```201 CREATED``` além dos mesmos parâmetros informados na requisição, acrescidos de um ```id``` que identifica o e-mail enviado no banco de dados, um ```sendDateEmail``` com a data e hora de envio e um ```statusEmail``` informando se o e-mail foi enviado com sucesso - ```SENT``` - ou não - ```ERROR```.
- Para consultar todos os e-mails enviados, deve ser feita uma requisição ```GET``` para o endpoint ```/list```. 
  - Em resposta, a API deve retornar o ```status code``` ```200 OK``` e uma lista paginada (por padrão, com 5 registros) contendo todos os e-mails enviados.
  - Podem ser informados os parâmetros ```page```, ```size``` e/ou ```sort``` para selecionar uma página, modificar a quantidade de registros por página e/ou a ordenação, respectivamente.
- Para consultar um e-mail específico, deve ser feita uma requisição ```GET``` para o endpoint ```/list/{id}``` com o ```id``` do e-mail a ser consultado.
  - Em resposta, a API deve retornar o ```status code``` ```200 OK``` e os dados consultados.
## Endpoints
Nesta primeira versão, são disponibilizados três endpoints:
- ```POST /send``` - Cria e envia um novo e-mail
- ```GET /list``` - Retorna uma lista paginada contendo todos os e-mails enviados pelo microserviço
- ```GET /list/{id}``` - Retorna um e-mail específico
----------
# Email microservice with Spring Boot
## Project's goal
To implement an email sending microservice using Spring Boot and Spring Mail. To simulate sending e-mails, [Mailtrap](https://mailtrap.io/) was used, which provides an SMTP server for testing.
## What is a microservice?
Microservice is a software architecture pattern according to which an application is built as a set of small services that communicate with each other through APIs.
Each microservice has very well-defined responsibilities and is an independent service, so that any failure is isolated and does not affect the functioning of the others.
Ideally, each microservice is the smallest possible part of an application resource and uses different databases. They are flexible, highly scalable and can be used by other services.
In contrast, the monolithic architectural pattern consists of a single application with all system functionality in a large block of code that runs in a single process and has all the responsibilities of the system.
The monolith is easier to develop and maintain, but has low flexibility and scalability, as well as very high coupling.
## Use cases
This microservice is responsible for sending emails to system users, informing them about events of their interest.
It is even possible to create a higher layer, which would be responsible for managing notifications in general, and which would communicate with this e-mail microservice.  It is also possible to make this microservice compatible with asynchronous messages, using messages. This feature, however, has not yet been implemented.
Also, at this time it is not possible to attach files to outgoing emails.
- To send an email, a ```POST``` request must be made to the ```/send``` endpoint with the following parameters in its body (in JSON format):
  - ```ownerRef``` - Reference of the user or service that sent the email through the system
  - ```emailFrom``` - Sender's email address
  - ```emailTo``` - Recipient's email address
  - ```subject``` - Subject of the email
  - ```text``` - Email body
  - In response, the API must return the ```status code``` ```201 CREATED``` in addition to the same parameters informed in the request, plus an ```id``` that identifies the email sent in the database, a ```sendDateEmail``` with the sending date and time and a ```statusEmail``` informing whether the email was successfully sent - ```SENT``` - or not - ```ERROR```.
- To consult all sent emails, a ```GET``` request must be made to the endpoint ```/list```.
  - In response, the API should return the ```status code``` ```200 OK``` and a paginated list (by default, with 5 records) containing all emails sent.
  - The parameters ```page```, ```size``` and/or ```sort``` can be informed to select a page, modify the number of records per page and/or the ordering, respectively .
- To query a specific e-mail, a ```GET``` request must be made to the endpoint ```/list/{id}``` with the ```id``` of the e-mail to be consulted.
  - In response, the API must return the ```status code``` ```200 OK``` and the queried data.
## Endpoints
In this first version, three endpoints are available:
- ```POST /send``` - Create and send a new email
- ```GET /list``` - Returns a paginated list containing all emails sent by the microservice
- ```GET /list/{id}``` - Returns a specific email
- 