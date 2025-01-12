
# Vulpsmoney

## Descrição
**Vulpsmoney** é uma API REST desenvolvida para facilitar o gerenciamento de finanças pessoais. Com ela, é possível organizar suas despesas e receitas, categorizá-las e realizar buscas específicas para acompanhar melhor sua saúde financeira.

## Tecnologias Usadas
- **Spring Boot**
- **Maven**
- **Java**

## Como Instalar e Rodar o Projeto
1. **Pré-requisitos**
   - Java 17 ou superior instalado.
   - Maven configurado em seu sistema.
   - Banco de dados (ex.: H2, MySQL ou PostgreSQL) configurado, conforme o application.properties.

2. **Clonar o repositório**
   ```bash
   git clone https://github.com/seu-usuario/vulpsmoney.git
   cd vulpsmoney
   ```

3. **Compilar o projeto**
   ```bash
   mvn clean install
   ```

4. **Rodar o projeto**
   ```bash
   mvn spring-boot:run
   ```

5. **Testar a API**
   Após rodar, a API estará disponível em `http://localhost:8080`. Utilize ferramentas como Postman ou Insomnia para testar as rotas.

## Funcionalidades Principais
- **CRUD de Origens:** Gerencie as fontes de renda e despesas. [Endpoints para Origem](EndpointsOrigem.md)
- **CRUD de Categorias:** Organize lançamentos por categorias específicas. [Endpoints para Categoria](EndpointsCategoria.md)
- **CRUD de Lançamentos:** Registre despesas e receitas com detalhes. [Endpoints para Lançamento](EndpointsLancamento.md)
- **Pesquisa de Lançamentos:** Filtre lançamentos por categoria ou origem. [Endpoints de Filtro](EndpointsLancamento.md#filtros)
- **Resumo de Lançamentos:** Resuma lançamentos por ano, mês e categoria. [Endpoints de Resumo](EndpointsLancamento.md#resumos)


## Contribuições
Contribuições são bem-vindas! Para contribuir:
1. Faça um fork do repositório.
2. Crie uma branch com sua feature: `git checkout -b minha-feature`.
3. Realize as alterações e faça commit: `git commit -m 'Minha nova feature'`.
4. Envie para o repositório principal: `git push origin minha-feature`.
5. Abra um Pull Request.

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
