package View;

import Controller.PagamentoController;
import Controller.PedidoController;
import Model.Pagamento;
import Model.PagamentoCartao;
import Model.PagamentoDinheiro;
import Model.Pedido;

import java.util.List;

public class MenuPagamento {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void exibir() {
        int opcao;
        do {
            System.out.println(ANSI_BLUE + "========== MENU PAGAMENTO ==========" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Processar Pagamento" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "2. Histórico de Pagamentos" + ANSI_RESET);
            System.out.println(ANSI_RED + "3. Reembolsar Pagamento" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "0. Voltar ao Menu Principal" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "====================================" + ANSI_RESET);

            opcao = InputHelper.lerInt("Digite uma opção: ");

            switch (opcao) {
                case 1 -> processarPagamento();
                case 2 -> listarPagamentos();
                case 3 -> reembolsarPagamento();
                case 0 -> System.out.println(ANSI_GREEN + "Voltando ao menu principal..." + ANSI_RESET);
                default -> System.out.println(ANSI_RED + "Opção inválida! Tente novamente." + ANSI_RESET);
            }
            System.out.println();
        } while (opcao != 0);
    }

    private static void processarPagamento() {

        PagamentoController.inicializarPedidosPendentes(PedidoController.listarPedidos());
        List<Pedido> pedidosPendentes = PagamentoController.listarPedidosPendentes();

        if (pedidosPendentes.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Não há pedidos pendentes no momento." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_BLUE + "------ Pedidos pendentes para pagamento ------" + ANSI_RESET);
        for (int i = 0; i < pedidosPendentes.size(); i++) {
            Pedido p = pedidosPendentes.get(i);
            System.out.printf("%d - Cliente: %s | Valor: R$ %.2f%n", i + 1, p.getCliente().getNome(), p.calcularValorTotal());
        }

        int indicePedido = InputHelper.lerInt("Escolha o número do pedido que deseja pagar: ") - 1;
        if (indicePedido < 0 || indicePedido >= pedidosPendentes.size()) {
            System.out.println(ANSI_RED + "Número inválido! Tente novamente." + ANSI_RESET);
            return;
        }

        Pedido pedido = pedidosPendentes.get(indicePedido);
        float valorPedido = (float) pedido.calcularValorTotal();

        System.out.println(ANSI_BLUE + "------ Escolha o método de pagamento ------" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[C] - Cartão 💳" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[D] - Dinheiro 💵" + ANSI_RESET);

        char metodo;
        do {
            metodo = InputHelper.lerChar("Digite o método de pagamento (C/D): ");
            if (metodo != 'C' && metodo != 'D' && metodo != 'c' && metodo != 'd') {
                System.out.println(ANSI_RED + "Método inválido! Tente novamente." + ANSI_RESET);
            }
        } while (metodo != 'C' && metodo != 'D' && metodo != 'c' && metodo != 'd');

        Pagamento pagamento;
        if (metodo == 'C' || metodo == 'c') {
            pagamento = new PagamentoCartao(valorPedido, pedido);
        } else {
            pagamento = new PagamentoDinheiro(valorPedido, pedido);
        }

        PagamentoController.processarPagamento(pagamento);
    }

    private static void listarPagamentos() {
        System.out.println(ANSI_BLUE + "------------ Histórico de Pagamentos ------------" + ANSI_RESET);
        List<Pagamento> pagamentos = PagamentoController.listarPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println(ANSI_RED + "Nenhum pagamento registrado." + ANSI_RESET);
            return;
        }
        System.out.printf(ANSI_PURPLE + "%-5s %-15s %-15s %-12s%n" + ANSI_RESET,
                "Nº", "Método", "Valor", "Status");
        System.out.println(ANSI_PURPLE + "------------------------------------------------------" + ANSI_RESET);
        for (int i = 0; i < pagamentos.size(); i++) {
            Pagamento p = pagamentos.get(i);
            String status = ANSI_GREEN + "Processado" + ANSI_RESET;

            System.out.printf("%-5d %-15s R$ %-13.2f %-12s%n",
                    i + 1,
                    p.getMetodo(),
                    p.getValor(),
                    status);

            System.out.println("      Detalhes: " + p.toString());
            System.out.println();
        }
    }

    private static void reembolsarPagamento() {
        listarPagamentos();
        List<Pagamento> pagamentos = PagamentoController.listarPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Nenhum pagamento para reembolsar." + ANSI_RESET);
            return;
        }

        int indice = -1;
        do {
            indice = InputHelper.lerInt("Escolha o número do pagamento que deseja reembolsar: ") - 1;
            if (indice < 0 || indice >= pagamentos.size()) {
                System.out.println(ANSI_RED + "Número inválido! Tente novamente." + ANSI_RESET);
                indice = -1;
            }
        } while (indice == -1);

        PagamentoController.confirmarReembolso(indice);
    }

}
