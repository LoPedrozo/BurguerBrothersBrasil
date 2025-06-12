package View;

import Controller.ClienteController;
import Controller.PedidoController;
import Controller.ProdutoController;
import Model.Pedido;
import Model.Cliente;
import Model.ItemPedido;
import Model.Produto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MenuPedido {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void exibir() {
        int opcao = 0;
        do {
            System.out.println(ANSI_BLUE + "============================" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       MENU DE PEDIDOS" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "============================" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "🛒 [1] Criar Pedido" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "📋 [2] Listar Pedidos" + ANSI_RESET);
            System.out.println(ANSI_YELLOW+ "✏️ [3] Alterar Pedido" + ANSI_RESET);
            System.out.println(ANSI_RED + "❌ [4] Excluir Pedido" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "🔙 [0] Voltar ao Menu Principal" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "============================" + ANSI_RESET);
            opcao = InputHelper.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> criarPedido();
                case 2 -> listarPedidos();
                case 3 -> alterarPedido();
                case 4 -> excluirPedido();
                case 0 -> System.out.println(ANSI_GREEN + "Voltando ao menu principal..." + ANSI_RESET);
                default -> System.out.println(ANSI_RED + "Opção inválida!" + ANSI_RESET);
            }
        } while (opcao != 0);
    }

    private static void criarPedido() {
        System.out.println(ANSI_BLUE + "--------- Criando um Pedido ---------" + ANSI_RESET);

        List<Cliente> clientes = ClienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println(ANSI_RED + "Nenhum cliente cadastrado. Cadastre um cliente antes de criar um pedido." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_YELLOW + "Clientes disponíveis:" + ANSI_RESET);
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + clientes.get(i).getNome() + " (CPF: " + clientes.get(i).getCpf() + ")");
        }
        String cpfCliente = InputHelper.lerString("Digite o CPF do cliente para o pedido: ");
        Cliente clienteDoPedido = null;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpfCliente)) {
                clienteDoPedido = c;
                break;
            }
        }

        if (clienteDoPedido == null) {
            System.out.println(ANSI_RED + "Cliente com CPF " + cpfCliente + " não encontrado." + ANSI_RESET);
            return;
        }

        List<Produto> produtosDisponiveis = ProdutoController.listarProdutos();
        if (produtosDisponiveis.isEmpty()) {
            System.out.println(ANSI_RED + "Nenhum produto cadastrado. Não é possível adicionar itens ao pedido." + ANSI_RESET);
            return;
        }

        List<ItemPedido> itensDoPedido = new ArrayList<>();
        char continuarAdicionando;
        do {
            System.out.println(ANSI_YELLOW + "\nProdutos disponíveis para adicionar:" + ANSI_RESET);
            for (int i = 0; i < produtosDisponiveis.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + produtosDisponiveis.get(i));
            }
            int indiceProduto = InputHelper.lerInt("Escolha o número do produto: ") - 1;

            if (indiceProduto < 0 || indiceProduto >= produtosDisponiveis.size()) {
                System.out.println(ANSI_RED + "Seleção de produto inválida." + ANSI_RESET);
                continuarAdicionando = InputHelper.lerString("Deseja tentar adicionar outro produto? (s/n): ").toLowerCase().charAt(0);
                continue;
            }
            Produto produtoSelecionado = produtosDisponiveis.get(indiceProduto);

            int quantidade = InputHelper.lerInt("Digite a quantidade para " + produtoSelecionado.getNome() + ": ");
            if (quantidade <= 0) {
                System.out.println(ANSI_RED + "Quantidade inválida." + ANSI_RESET);
                continuarAdicionando = InputHelper.lerString("Deseja tentar adicionar outro produto? (s/n): ").toLowerCase().charAt(0);
                continue;
            }

            ItemPedido item = new ItemPedido(produtoSelecionado, quantidade);
            itensDoPedido.add(item);
            System.out.println(ANSI_GREEN + produtoSelecionado.getNome() + " adicionado ao pedido." + ANSI_RESET);

            continuarAdicionando = InputHelper.lerString("Deseja adicionar mais itens? (s/n): ").toLowerCase().charAt(0);
        } while (continuarAdicionando == 's');

        if (itensDoPedido.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Nenhum item adicionado. Pedido não foi criado." + ANSI_RESET);
            return;
        }

        Pedido novoPedido = new Pedido(clienteDoPedido, itensDoPedido);
        PedidoController.criarPedido(novoPedido);

        clienteDoPedido.adicionarPedido(novoPedido);
        System.out.println(ANSI_GREEN + "Pedido criado e adicionado ao histórico do cliente!" + ANSI_RESET);
    }


    private static void excluirPedido() {
        System.out.println(ANSI_BLUE + "--------- Excluindo um Pedido ---------" + ANSI_RESET);
        List<Pedido> pedidos = PedidoController.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Nenhum pedido para excluir." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_YELLOW + "Pedidos existentes:" + ANSI_RESET);
        for (int i = 0; i < pedidos.size(); i++) {
             System.out.println("[" + (i + 1) + "] Pedido de " + pedidos.get(i).getCliente().getNome() +
                               " em " + pedidos.get(i).getData() +
                               " - Valor: R$" + String.format("%.2f", pedidos.get(i).calcularValorTotal()));
        }

        int indicePedido = InputHelper.lerInt("Digite o número do pedido que deseja excluir: ") - 1;
        PedidoController.removerPedido(indicePedido);
    }

    private static void alterarPedido() {
        System.out.println(ANSI_BLUE + "--------- Alterando um Pedido ---------" + ANSI_RESET);
        List<Pedido> pedidos = PedidoController.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Nenhum pedido para alterar." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_YELLOW + "Pedidos existentes:" + ANSI_RESET);
        System.out.printf(ANSI_PURPLE + "%-5s %-20s %-15s %-10s%n" + ANSI_RESET, "Nº", "Cliente", "Data", "Valor Total");
        System.out.println(ANSI_PURPLE + "----------------------------------------------------------" + ANSI_RESET);

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            String dataFormatada = new SimpleDateFormat("dd/MM").format(pedido.getData());
            double valorTotal = pedido.calcularValorTotal();

            System.out.printf("%-5d %-20s %-15s R$ %-9.2f%n",
                    i + 1,
                    pedido.getCliente().getNome(),
                    dataFormatada,
                    valorTotal);
        }

        int indicePedido = InputHelper.lerInt("Digite o número do pedido que deseja alterar: ") - 1;

        if (indicePedido < 0 || indicePedido >= pedidos.size()) {
            System.out.println(ANSI_RED + "Seleção de pedido inválida." + ANSI_RESET);
            return;
        }

        Pedido pedidoParaAlterar = pedidos.get(indicePedido);

        int opcaoAlteracao;
        do {
            System.out.println(ANSI_BLUE + "\nAlterando Pedido de: " + pedidoParaAlterar.getCliente().getNome() + ANSI_RESET);
            System.out.println("Itens atuais do pedido:");
            List<ItemPedido> itensPedido = pedidoParaAlterar.getItens();

            if (itensPedido.isEmpty()) {
                System.out.println(ANSI_YELLOW + "Nenhum item no pedido." + ANSI_RESET);
            } else {
                System.out.printf(ANSI_PURPLE + "%-5s %-25s %-10s%n" + ANSI_RESET, "Nº", "Produto", "Quantidade");
                System.out.println(ANSI_PURPLE + "------------------------------------------" + ANSI_RESET);
                for (int i = 0; i < itensPedido.size(); i++) {
                    ItemPedido item = itensPedido.get(i);
                    System.out.printf("%-5d %-25s %-10d%n", i + 1, item.getProduto().getNome(), item.getQuantidade());
                }
            }

            System.out.println(ANSI_GREEN + "[1] Adicionar Item" + ANSI_RESET);
            System.out.println(ANSI_RED + "[2] Remover Item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "[0] Concluir Alterações" + ANSI_RESET);
            opcaoAlteracao = InputHelper.lerInt("Escolha uma opção de alteração: ");

            switch (opcaoAlteracao) {
                case 1:
                    List<Produto> produtosDisponiveis = ProdutoController.listarProdutos();
                    if (produtosDisponiveis.isEmpty()) {
                        System.out.println(ANSI_RED + "Nenhum produto disponível para adicionar." + ANSI_RESET);
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "\nProdutos disponíveis para adicionar:" + ANSI_RESET);
                    System.out.printf(ANSI_PURPLE + "%-5s %-25s %-10s%n" + ANSI_RESET, "Nº", "Nome", "Preço");
                    System.out.println(ANSI_PURPLE + "------------------------------------------" + ANSI_RESET);
                    for (int i = 0; i < produtosDisponiveis.size(); i++) {
                        Produto produto = produtosDisponiveis.get(i);
                        System.out.printf("%-5d %-25s R$ %-9.2f%n", i + 1, produto.getNome(), produto.getPreco());
                    }
                    int indiceProdutoAdd = InputHelper.lerInt("Escolha o número do produto: ") - 1;
                    if (indiceProdutoAdd < 0 || indiceProdutoAdd >= produtosDisponiveis.size()) {
                        System.out.println(ANSI_RED + "Seleção de produto inválida." + ANSI_RESET);
                        break;
                    }
                    Produto produtoParaAdicionar = produtosDisponiveis.get(indiceProdutoAdd);
                    int qtd = InputHelper.lerInt("Digite a quantidade para " + produtoParaAdicionar.getNome() + ": ");
                    if (qtd <= 0) {
                        System.out.println(ANSI_RED + "Quantidade inválida." + ANSI_RESET);
                        break;
                    }
                    ItemPedido novoItem = new ItemPedido(produtoParaAdicionar, qtd);
                    pedidoParaAlterar.adicionarItem(novoItem);
                    System.out.println(ANSI_GREEN + "Item adicionado ao pedido." + ANSI_RESET);
                    break;

                case 2:
                    if (itensPedido.isEmpty()) {
                        System.out.println(ANSI_YELLOW + "O pedido não possui itens para remover." + ANSI_RESET);
                        break;
                    }
                    int indiceItemRemover = InputHelper.lerInt("Escolha o número do item para remover: ") - 1;
                    if (indiceItemRemover < 0 || indiceItemRemover >= itensPedido.size()) {
                        System.out.println(ANSI_RED + "Seleção de item inválida." + ANSI_RESET);
                        break;
                    }
                    itensPedido.remove(indiceItemRemover);
                    System.out.println(ANSI_GREEN + "Item removido do pedido." + ANSI_RESET);
                    break;

                case 0:
                    System.out.println(ANSI_GREEN + "Alterações concluídas." + ANSI_RESET);
                    break;

                default:
                    System.out.println(ANSI_RED + "Opção de alteração inválida!" + ANSI_RESET);
            }
        } while (opcaoAlteracao != 0);
        System.out.println(ANSI_GREEN + "Pedido atualizado com sucesso!" + ANSI_RESET);
    }


    private static void listarPedidos() {
        System.out.println(ANSI_BLUE + "--------- Lista de Pedidos ---------" + ANSI_RESET);
        List<Pedido> pedidos = PedidoController.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Nenhum pedido cadastrado." + ANSI_RESET);
            return;
        }

        System.out.printf(ANSI_PURPLE + "%-5s %-20s %-15s %-10s%n" + ANSI_RESET, "Nº", "Cliente", "Data", "Valor Total");
        System.out.println(ANSI_PURPLE + "----------------------------------------------------------" + ANSI_RESET);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            String dataFormatada = sdf.format(pedido.getData());
            double valorTotal = pedido.calcularValorTotal();

            System.out.printf("%-5d %-20s %-15s R$ %-9.2f%n",
                    i + 1,
                    pedido.getCliente().getNome(),
                    dataFormatada,
                    valorTotal);
        }
    }

}