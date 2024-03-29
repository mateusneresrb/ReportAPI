﻿
# ReportAPI
> Este projeto foi desenvolvido devido a participação em um processo seletivo!

<img src="https://i.imgur.com/X1hr5YC.png" alt="Report api ilustration">

Ele consiste em realizar uma API de denuncias, onde o usuário deverá fornecer informações sobre a denuncia e o endereço é obtido a partir da latitude/longitude pela API da MapQuest.

Veja mais info sobre o desafio em: https://github.com/run2biz/teste-backend-java

## 📝 Documentação:
Veja a documentação de uma outra perspectiva: http://144.22.139.173/docs

#### Denuncias
| Method | Url           | Description    | How to use                   |
|--------|---------------|----------------|------------------------------|
| GET    | /v1/denuncias | Lista denuncia | [[VER MAIS]](#create-report) |
| PUT    | /v1/denuncias | Cria denuncia  | [[VER MAIS]](#list-reports)  | 

#### Códigos de Error:
| Code  | Message                                                           |
|-------|-------------------------------------------------------------------|
| 1     | Request inválido.                                                 |
| 2     | Endereço não encontrado para essa localidade.                     |
| 3     | A api key do mapquest não foi autorizada!                         |
| 4     | Já existe uma denuncia criada por você com as mesmas informações. |

#### Exemplos:

<a id="create-report">Criando uma nova denuncia:</a>
```bash
curl -X POST \
  http://localhost:8080/v1/denuncias \
  -H 'Content-Type: application/json' \
  -d '{
    "latitude": -15.789925709252136,
    "longitude": -47.887251273393815,
    "denunciante": {
      "nome": "José da Silva",
      "cpf": "45616532145"
    },
    "denuncia": {
      "titulo": "Esgoto a céu aberto",
      "descricao": "Existe um esgoto a céu aberto nesta localidade."
    }
  }'
```
<a id="list-reports">Listando denuncias pelo CPF:</a>
```
curl -X 'GET' \
  'http://localhost:8080/v1/denuncias?cpf=45616532145' \
  -H 'accept: application/json'
```

## 💻 Como construir ?

O projeto foi desenvolvido utilizando as tecnologias abaixo:

* Java (version 8)
* H2
* Spring boot
* assertj-core
* Maven
* Lombok
* JUnit (tests)
* springfox-swagger-ui
* And others...

Para construir um clone deste repositório e certificar-se de que tem o maven instalado na sua máquina, depois basta iniciar a sua IDE e baixar as dependencias.

## 🚀 Baixar e utilizar
Siga todas as instruções abaixo para conseguir executar esta aplicação.

**1. Tenha o docker e docker compose instalado na sua maquina**

Caso você já tenha o docker instalado, faça um clone do repositorio e vá até a pasta **docker**, dentro dela vai ter o Dockerfile e o docker-compose.yml

**2. Configure o docker compose**

É necessário que você configure o docker compose com a sua API KEY do mapquest dentre outras informações de banco de dados

**3. Criar e iniciar o servidor**

Após realizar os passos acima, digite o comando **docker compose up -d** para criar e iniciar o container.

**4. Crie uma denuncia seguindo os passos da documentação**

Nesta etapa você já conseguirá criar e listar denuncias pela api.

## 🤝 Colaboradores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/mateusneresrb">
        <img src="https://avatars.githubusercontent.com/u/52140952?v=4" width="100px;" alt="Foto do Iuri Silva no GitHub"/><br>
        <sub>
          <b>Mateus Neres</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
