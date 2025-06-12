package View;

import Controller.ProdutoController;
import Model.Produto;
import Model.Hamburguer;
import Model.Bebida;
import Model.Acompanhamento;

import java.util.List;

public class MenuProduto {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void exibir() {
        int opcao;
        do {
            System.out.println(ANSI_BLUE + "==========================" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       MENU PRODUTOS" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "==========================" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "🛍️ [1] Cadastrar Produto" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "📦 [2] Listar Produtos" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "✏️ [3] Editar Produto" + ANSI_RESET);
            System.out.println(ANSI_RED + "❌ [4] Excluir Produto" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "🔙 [0] Voltar ao Menu Principal" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "==========================" + ANSI_RESET);
            opcao = InputHelper.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> editarProduto();
                case 4 -> excluirProduto();
                case 0 -> System.out.println(ANSI_GREEN + "Voltando ao menu principal..." + ANSI_RESET);
                default -> System.out.println(ANSI_RED + "Opção inválida!" + ANSI_RESET);
            }
        } while (opcao != 0);
    }

    private static void cadastrarProduto() {
        System.out.println(ANSI_BLUE + "--------- Cadastro de Produtos ---------" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[1] Hamburguer" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[2] Bebida" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[3] Acompanhamento" + ANSI_RESET);
        int tipo = InputHelper.lerInt("Escolha o tipo de produto: ");
        String nome = InputHelper.lerString("Nome: ");
        float preco = InputHelper.lerFloat("Preço: ");

        switch (tipo) {
            case 1 -> ProdutoController.cadastrarHamburguer(nome, preco);
            case 2 -> ProdutoController.cadastrarBebida(nome, preco);
            case 3 -> ProdutoController.cadastrarAcompanhamento(nome, preco);
            default -> System.out.println(ANSI_RED + "Tipo de produto inválido!" + ANSI_RESET);
        }

        System.out.println(ANSI_GREEN + "Produto cadastrado com sucesso!" + ANSI_RESET);
    }

    private static void listarProdutos() {
        List<Produto> produtos = ProdutoController.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println(ANSI_RED + "Nenhum produto cadastrado." + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_BLUE + "--------- Lista de Produtos ---------" + ANSI_RESET);
        System.out.printf(ANSI_PURPLE + "%-5s %-20s %-10s%n" + ANSI_RESET, "Nº", "Nome", "Preço");
        System.out.println(ANSI_PURPLE + "------------------------------------------" + ANSI_RESET);

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            System.out.printf("%-5d %-20s R$ %-9.2f%n", i + 1, produto.getNome(), produto.getPreco());
        }
    }

    private static void editarProduto() {
        List<Produto> produtos = ProdutoController.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println(ANSI_RED + "Não há produtos cadastrados para editar." + ANSI_RESET);
            return;
        }

        listarProdutos();
        int indice = InputHelper.lerInt("Escolha o número do produto que deseja editar: ") - 1;

        if (indice < 0 || indice >= produtos.size()) {
            System.out.println(ANSI_RED + "Produto inválido!" + ANSI_RESET);
            return;
        }

        Produto produtoAtual = produtos.get(indice);

        System.out.println(ANSI_YELLOW + "Você está editando o produto: " + produtoAtual.getNome() + ANSI_RESET);
        String novoNome = InputHelper.lerString("Novo nome (deixe em branco para manter \"" + produtoAtual.getNome() + "\"): ");
        float novoPreco = InputHelper.lerFloat("Novo preço (digite -1 para manter R$ " + produtoAtual.getPreco() + "): ");


        String nomeFinal;
        if (novoNome.trim().isEmpty()) {
            nomeFinal = produtoAtual.getNome();
        } else {
            nomeFinal = novoNome.trim();
        }


        double precoFinal;
        if (novoPreco >= 0) {
            precoFinal = novoPreco;
        } else {
            precoFinal = produtoAtual.getPreco();
        }


        if (!nomeFinal.equals(produtoAtual.getNome()) || precoFinal != produtoAtual.getPreco()) {
            Produto produtoAtualizado;

            if (produtoAtual instanceof Hamburguer) {
                Hamburguer hamburguer = (Hamburguer) produtoAtual;
                produtoAtualizado = new Hamburguer(nomeFinal, precoFinal);
            } else if (produtoAtual instanceof Bebida) {
                Bebida bebida = (Bebida) produtoAtual;
                produtoAtualizado = new Bebida(nomeFinal, precoFinal);
            } else if (produtoAtual instanceof Acompanhamento) {
                Acompanhamento acompanhamento = (Acompanhamento) produtoAtual;
                produtoAtualizado = new Acompanhamento(nomeFinal, precoFinal);
            } else {
                System.out.println(ANSI_RED + "Tipo de produto desconhecido!" + ANSI_RESET);
                return;
            }

            ProdutoController.editarProduto(indice, produtoAtualizado);
            System.out.println(ANSI_GREEN + "Produto editado com sucesso!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + "Nenhuma alteração foi feita no produto." + ANSI_RESET);
        }
    }

    private static void excluirProduto() {
        listarProdutos();
        int indice = InputHelper.lerInt("Escolha o número do produto que deseja excluir: ") - 1;

        List<Produto> produtos = ProdutoController.listarProdutos();
        if (indice < 0 || indice >= produtos.size()) {
            System.out.println(ANSI_RED + "Produto inválido!" + ANSI_RESET);
            return;
        }

        Produto produto = produtos.get(indice);
        ProdutoController.deletarProduto(produto);
        System.out.println(ANSI_GREEN + "Produto excluído com sucesso!" + ANSI_RESET);
    }

}
