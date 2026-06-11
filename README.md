# Serviço de Funcionários

API REST para gerenciamento de funcionários e seus dados pessoais, construída com Quarkus.

## Pré-requisitos

- Java 21 (ou superior)
- Apache Maven 3.8 (ou superior)

## Como Executar

Para iniciar a aplicação em modo de desenvolvimento, execute o seguinte comando na raiz do projeto:

```bash
./mvnw quarkus:dev
```

A API estará disponível em `http://localhost:8080`.

## Explorando a API

A documentação completa e interativa da API é gerada automaticamente e está disponível através do Swagger UI.

Após iniciar a aplicação, acesse o seguinte endereço no seu navegador:

[**http://localhost:8080/q/swagger-ui**](http://localhost:8080/q/swagger-ui)

A interface do Swagger UI permite visualizar todos os endpoints, seus parâmetros, modelos de dados e testar a API diretamente do navegador.

### Exemplo de Uso Rápido

Para um teste rápido via linha de comando, você pode criar uma nova pessoa usando `curl`:

```bash
curl -X POST http://localhost:8080/pessoas \
-H "Content-Type: application/json" \
-d '{
  "nome": "Maria Clara",
  "cpf": "98765432100"
}'
```

Para todos os outros endpoints, detalhes de implementação e testes interativos, por favor, utilize a interface do Swagger UI.
