# Como Usar a API

## Lançamentos

### Listar todos os Lançamentos

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos`

- **Exemplo de resposta:**
  ```json
  [
    {
      "id": 1,
      "data": "2026-01-04",
      "descricao": "Frutas",
      "valor": 27.0,
      "categoria": { "id": 1, "titulo": "Alimentação" },
      "origem": { "id": 1, "titulo": "Carteira" }
    },
    {
      "id": 2,
      "data": "2025-01-04",
      "descricao": "Sabão",
      "valor": 13.0,
      "categoria": { "id": 2, "titulo": "Limpeza" },
      "origem": { "id": 2, "titulo": "Conta Corrente" }
    }
  ]
  ```

### Ler dados de um Lançamento

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/{id}`

- **Exemplo de resposta:**
  ```json
  {
    "id": 1,
    "data": "2026-01-04",
    "descricao": "Frutas",
    "valor": 27.0,
    "categoria": { "id": 1, "titulo": "Alimentação" },
    "origem": { "id": 1, "titulo": "Carteira" }
  }
  ```
- **Possíveis erros:**
  - `404 Not Found`: Quando não há lançamento correpondente ao `id` informado.

### Criar Novo Lançamento

- **Método HTTP:** POST
- **Endpoint:** `/lancamentos`
- **Exemplo de payload:**
  ```json
  {
    "data": "2026-01-04",
    "descricao": "Frutas",
    "valor": 27.0,
    "categoria": { "id": 1 },
    "origem": { "id": 1 }
  }
  ```
- **Exemplo de resposta:**

  ```json
  {
    "id": 1,
    "data": "2026-01-04",
    "descricao": "Frutas",
    "valor": 27.0,
    "categoria": { "id": 1, "titulo": "Alimentação" },
    "origem": { "id": 1, "titulo": "Carteira" }
  }
  ```

  ### Editar dados de um Lançamento

- **Método HTTP:** PUT
- **Endpoint:** `/lancamentos/{id}`

- **Exemplo de payload:**

  ```json
  {
    "data": "2026-01-04",
    "descricao": "Frutas",
    "valor": 27.0,
    "categoria": { "id": 1 },
    "origem": { "id": 1 }
  }
  ```

- **Exemplo de resposta:**

  ```json
  {
    "id": 1,
    "data": "2026-01-04",
    "descricao": "Frutas",
    "valor": 27.0,
    "categoria": { "id": 1, "titulo": "Alimentação" },
    "origem": { "id": 1, "titulo": "Carteira" }
  }
  ```

- **Possíveis erros:**

  - `404 Not Found`: Quando não há lançamento correpondente ao `id` informado.

  ### Excluir um Lançamento

- **Método HTTP:** DELETE
- **Endpoint:** `/lancamentos/{id}`

- **Possíveis erros:**

  - `404 Not Found`: Quando não há lançamento correpondente ao `id` informado.

 ## Filtros

  ### Filtrar lançamentos por origem

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/origem/{titulo origem}`

  ### Filtrar lançamentos por categoria

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/categoria/{titulo categoria}`

## Resumos

  ### Resumir o valor acumulado em um determinado ano

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/{ano}`

  ### Resumir o valor acumulado em um determinado ano e mês

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/{ano}/{mes}`

  ### Resumir o valor acumulado em um determinado ano, mês e categoria

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/{ano}/{mes}/categoria/{titulo categoria}`

  ### Resumir o valor acumulado em um determinado ano e categoria

- **Método HTTP:** GET
- **Endpoint:** `/lancamentos/{ano}/categoria/{titulo categoria}`
