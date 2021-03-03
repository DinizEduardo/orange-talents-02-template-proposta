# Projeto de Proposta

## 000-SETUP-DO-PROJETO

### Objetivo

Sabemos que está ansioso(a) para começar a codificar, porém antes precisamos preparar nosso ambiente, portanto esse será nosso objetivo nessa tarefa.

### Descrição

Nessa tarefa precisamos criar um projeto para atender as funcionalidades da **Proposta**, para tal, temos alguns pré requisitos de linguagem de programação e tecnologia, pois precisamos que esse projeto seja evoluído e mantido por anos, portanto é extremamente importante a escolha das mesmas.

Nosso mais experiente membro do time, sugeriu os seguintes itens:

Linguagem de programação

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Kotlin 1.3](https://kotlinlang.org/)

Tecnologia

- [Spring Boot 2.3.*](https://spring.io/projects/spring-boot)

Gerenciador de dependência

- [Maven](https://maven.apache.org/)

## 001-SETUP-DOCKER-COMPOSE

### Objetivo

Nosso objetivo aqui é preparar a nossa _"infraestrutura local"_ para nos conectarmos com ela quando for necessário. Vamos usar o docker-compose para isso!!!
Vamos lá!!

### Descrição

Durante o ciclo de desenvolvimento do nosso projeto Proposta, vamos precisar nos conectar alguns serviços externos como por exemplo banco de dados, serviços legados e outros. Não vamos criar esses serviços
vamos usá-los somente, então podemos entendê-los como uma "caixa preta".

### Resultado Esperado

* Todos nossos containers de infraestrutura no estado **Running**

## 005-CRIAÇÃO-PROPOSTA

### Objetivo

Realizar a criação de uma proposta, durante o processo de criação da proposta deve ser checado restrições ao solicitante da proposta.

### Necessidades

- O documento necessário deve ser o CPF/CNPJ
- email
- nome
- endereço
- salário

### Restrições

- documento do solicitante deve ser obrigatório e válido
- email não pode ser vazio, nulo ou inválido
- nome não pode ser vazio ou nulo
- endereço não pode ser vazio ou nulo
- salário bruto não pode ser vazio, nulo ou negativo

### Resultado Esperado

- A proposta deve estar armazenada no sistema, com um identificador gerado pelo sistema.
- Retornar **201** com Header Location preenchido com a URL da nova proposta em caso de sucesso.
- Retornar **400** quando violado alguma das restrições.

## 010-DOCUMENTO-UNICO

### Objetivo

Criamos o fluxo de geração de proposta, porém nosso cliente solicitou uma alteração que consiste em adicionar uma nova
validação. Entretanto, não podemos permitir a existência de mais de uma proposta para o mesmo solicitante, ou seja, para o mesmo
CNPJ ou CPF.

### Resultado Esperado

Não podemos permitir que tenha mais de uma proposta para o mesmo solicitante, ou seja, para o mesmo
CNPJ ou CPF.

- Devemos retornar o status code **422**, quando o solicitante já requisitou uma proposta.
- Permitir a criação de uma proposta, caso o solicitante não tenha nenhuma outra.

## 015-CONSULTANDO-DADOS-SOLICITANTE

### Objetivo

Devemos consultar alguns dados financeiros do solicitante afim de validar se sera possivel oferecer um cartão.

### Necessidades

Antes de provisionar um cartão para o solicitante, devemos verificar se o mesmo possui restrições no sistema de dados
finaneiros.

Temos uma API específica para consultar os dados financeiros, vamos analisá-la?

`http://localhost:9999/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`

### Restrições

- O documento deve ser passado no body da requisição por motivo de segurança, header and query parameters nao são criptografados
    - CPF/CNPJ

- O nome deve ser passado no body

- O identificador da proposta deve ser passado no body

## Resultado Esperado

No processo de Criação da Proposta deve considerar o status recebido da avaliação financeira do solicitante.
-  Caso a devolutiva da analise for o estado **COM_RESTRICAO** o estado da proposta deve ser **NAO_ELEGIVEL**
-  Caso a devolutiva da analise for o estado **SEM_RESTRICAO** o estado da proposta deve ser **ELEGIVEL**
