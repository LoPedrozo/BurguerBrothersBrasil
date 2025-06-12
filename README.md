
# 🍔 Burger Brothers Brazil 🍔

**THE BEST BURGER IN THE WORLD**

Projeto acadêmico desenvolvido em Java com o objetivo de simular um sistema de gerenciamento de um restaurante especializado em hambúrgueres. O sistema contempla funcionalidades de cadastro e gestão de clientes, produtos, pedidos e pagamentos, utilizando princípios sólidos de Programação Orientada a Objetos.

---

## 📌 Informações Gerais sobre o Projeto

**Objetivo:**  
Desenvolver um sistema completo em **Java puro** que simule a operação de um restaurante, aplicando conceitos de **Orientação a Objetos**, como **herança**, **polimorfismo**, **classes abstratas**, **interfaces** e **coleções**, além de **leitura e gravação em arquivos** para persistência de dados.

**Funcionalidades principais:**
- Cadastro, edição, listagem e remoção de clientes (CRUD)
- Criação e gerenciamento de produtos e combos
- Registro e cálculo de pedidos
- Controle de pagamentos
- Armazenamento de dados em arquivos `.txt`

---

## 🧩 Informações sobre as Classes e suas Relações

### 📁 `cliente`
- **Pessoa** (classe abstrata): Superclasse com atributos comuns (nome, CPF, etc.)
- **Cliente**: Subclasse de Pessoa, com métodos específicos de cliente
- **Validador**: Validação de entrada de dados
- **ClienteController**: CRUD de clientes
- **MenuCliente**: Interface de terminal para cliente

### 📁 `produto`
- **Produto**: Classe base para itens do cardápio
- **Combo**: Composição de vários produtos com desconto (usa *composição* de Produto)
- **ItemCardapio**: Representa produtos individuais no menu
- **CategoriaProduto**: Enum para categorias de produtos
- **ProdutoController**: Responsável por gerenciar produtos e combos

### 📁 `pedido`
- **Pedido**: Representa um pedido feito por um cliente (associação com Cliente e ItemPedido)
- **ItemPedido**: Representa um item dentro do pedido (agregação de Produto)
- **Pagamento**: Representa a forma de pagamento
- **PedidoController** e **MenuPedido**: Gerenciam fluxo do pedido no terminal

### 📁 `utils`
- **ArquivoUtil**: Leitura e gravação em arquivos `.txt` para persistência de dados

### 📁 `main`
- **Main.java**: Classe principal que inicia o sistema
- **MenuPrincipal**: Menu de navegação entre os módulos

---

## ▶️ Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/acairesm/Burger_Brothers_Brasil-.git
   ```
2. **Abra o projeto** em uma IDE como IntelliJ IDEA, Eclipse ou VS Code com suporte a Java.

3. **Execute a classe `Main.java`** localizada no pacote `main`.

> ⚠️ Os dados são salvos localmente em arquivos `.txt`. Não é necessário banco de dados.

---

## 🤖 Uso do ChatGPT & Blackbox AI

O **ChatGPT** e o **Blackbox AI** foram utilizados como assistentes de desenvolvimento para:

- Modelagem de classes com base nos requisitos fornecidos
- Revisão de padrões de projeto (como MVC) e boas práticas
- Suporte na criação deste arquivo `README.md`
- Sugestões para organização de pacotes, validações e estruturação do código

A utilização da IA visou acelerar o desenvolvimento, reforçar boas práticas e apoiar o aprendizado, sem comprometer a autoria do projeto.

---

## 📚 Referências e Recursos

- Documentação oficial do Java
- Exemplos e tutoriais sobre boas práticas no GitHub
- [ChatGPT (OpenAI)](https://chat.openai.com) & [Blackbox AI](https://www.useblackbox.io)

---

## 👥 Autores

- [**Lorenzo Garcia Pedrozo**](https://github.com/LoPedrozo) – Organização Geral de Todas as Classes do Projeto, melhorias na interface dos menus e Criação do README.
- [**André Caires**](https://github.com/acairesm) – Módulo Produto, Pagamento, Criação do InputHelper(ambos) e Correções de Bugs no Sistema.
- [**Aslam Rekik**](https://github.com/aslamrekik) – Módulo Cliente, Menu Principal, Criação do InputHelper(ambos) e Correções de Bugs no Sistema.
- [**Leonardo Herédia**](https://github.com/LeoHeredia2004) – Logs, Salvamentos em arquivos e Módulo Pedidos.

---

