# Como Usar a API

## Origens

### Listar todas as Origens

- **Método HTTP:** GET
- **Endpoint:** `/origens`

- **Exemplo de resposta:**
  ```json
  [
    {
      "id": 1,
      "titulo": "Carteira"
    },
    {
      "id": 2,
      "titulo": "Conta corrente"
    }
  ]
  ```

### Ler dados de uma Origem

- **Método HTTP:** GET
- **Endpoint:** `/origens/{id}`

- **Exemplo de resposta:**
  ```json
  {
    "id": 1,
    "titulo": "Carteira"
  }
  ```
- **Possíveis erros:**
  - `404 Not Found`: Quando não há origem correpondente ao `id` informado.

### Criar Nova Origem

- **Método HTTP:** POST
- **Endpoint:** `/origens`
- **Exemplo de payload:**
  ```json
  {
    "titulo": "Conta poupança"
  }
  ```
- **Exemplo de resposta:**

  ```json
  {
    "id": 1,
    "titulo": "Conta poupança"
  }
  ```

  ### Editar dados de uma Origem

- **Método HTTP:** PUT
- **Endpoint:** `/origens/{id}`

- **Exemplo de payload:**
  ```json
  {
    "titulo": "Novo título"
  }
  ```

  - **Exemplo de resposta:**

  ```json
  {
    "id": 1,
    "titulo": "Novo título"
  }
- **Possíveis erros:**

  - `404 Not Found`: Quando não há origem correpondente ao `id` informado.

  ### Excluir uma Origem

- **Método HTTP:** DELETE
- **Endpoint:** `/origens/{id}`

- **Possíveis erros:**
  - `404 Not Found`: Quando não há origem correpondente ao `id` informado.
  - `500 Internal Server Error`: Quando tentar excluir a origem padrão `id = 1`.