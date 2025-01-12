# Como Usar a API

## Categorias

### Listar todas as Categorias

- **Método HTTP:** GET
- **Endpoint:** `/categorias`

- **Exemplo de resposta:**
  ```json
  [
    {
      "id": 1,
      "titulo": "Alimentação"
    },
    {
      "id": 2,
      "titulo": "Limpeza"
    }
  ]
  ```

### Ler dados de uma Categoria

- **Método HTTP:** GET
- **Endpoint:** `/categorias/{id}`

- **Exemplo de resposta:**
  ```json
  {
    "id": 1,
    "titulo": "Alimentação"
  }
  ```
- **Possíveis erros:**
  - `404 Not Found`: Quando não há categoria correpondente ao `id` informado.

### Criar Nova Categoria

- **Método HTTP:** POST
- **Endpoint:** `/categorias`
- **Exemplo de payload:**
  ```json
  {
    "titulo": "Alimentação"
  }
  ```
- **Exemplo de resposta:**

  ```json
  {
    "id": 1,
    "titulo": "Alimentação"
  }
  ```

  ### Editar dados de uma Categoria

- **Método HTTP:** PUT
- **Endpoint:** `/categorias/{id}`

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
  ```

- **Possíveis erros:**

  - `404 Not Found`: Quando não há categoria correpondente ao `id` informado.

  ### Excluir uma Categoria

- **Método HTTP:** DELETE
- **Endpoint:** `/categorias/{id}`

- **Possíveis erros:**
  - `404 Not Found`: Quando não há categoria correpondente ao `id` informado.
  - `500 Internal Server Error`: Quando tentar excluir a categoria padrão `id = 1`.
