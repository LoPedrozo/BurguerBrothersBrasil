package Controller;

import Model.Pagamento;
import Model.Pedido;
import Util.LoggerService;
import View.InputHelper;

import java.util.ArrayList;
import java.util.List;

public class PagamentoController {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    private static List<Pagamento> pagamentos = new ArrayList<>();
    private static List<Pedido> pedidosPendentes = new ArrayList<>();


    public static void inicializarPedidosPendentes(List<Pedido> pedidos) {

        pedidosPendentes.clear();
        for (Pedido p : pedidos) {
            if (p.getStatus() == Pedido.Status.PENDENTE) {
                pedidosPendentes.add(p);
            }
        }
    }


    public static List<Pedido> listarPedidosPendentes() {
        return new ArrayList<>(pedidosPendentes);
    }

    public static void adicionarPagamento(Pagamento pagamento) {
        if (pagamento.getValor() <= 0) {
            System.out.println(ANSI_RED + "Erro: Valor do pagamento deve ser maior que zero." + ANSI_RESET);
            return;
        }
        pagamentos.add(pagamento);
        LoggerService.log("CREATE: Pagamento registrado - " + pagamento);

        Pedido pedido = pagamento.getPedido();
        pedido.setStatus(Pedido.Status.PAGO);


        pedidosPendentes.remove(pedido);
        System.out.println(ANSI_GREEN + "Pagamento registrado com sucesso para pedido do cliente " + pedido.getCliente().getNome() + "!" + ANSI_RESET);
    }


    public static List<Pagamento> listarPagamentos() {
        LoggerService.log("READ: Listagem de pagamentos solicitada.");
        return new ArrayList<>(pagamentos);
    }

    public static void removerPagamento(int indice) {
        if (indice < 0 || indice >= pagamentos.size()) {
            System.out.println(ANSI_RED + "Índice inválido para remoção de pagamento!" + ANSI_RESET);
            return;
        }
        Pagamento p = pagamentos.get(indice);
        pagamentos.remove(indice);
        System.out.println(ANSI_GREEN + "Pagamento removido com sucesso: " + p + ANSI_RESET);
    }


    public static boolean confirmarReembolso(int indice) {
        if (indice < 0 || indice >= pagamentos.size()) {
            System.out.println(ANSI_RED + "Índice inválido para reembolso!" + ANSI_RESET);
            return false;
        }
        Pagamento p = pagamentos.get(indice);
        System.out.println("Você tem certeza que deseja reembolsar o seguinte pagamento?");
        System.out.println("-> " + p);
        String resposta = InputHelper.lerString("Digite 's' para confirmar ou qualquer outra tecla para cancelar: ");
        if (resposta.equalsIgnoreCase("s")) {
            pagamentos.remove(indice);
            Pedido pedidoAssociado = p.getPedido();
            pedidoAssociado.setStatus(Pedido.Status.REEMBOLSADO);
            pedidosPendentes.add(pedidoAssociado);
            LoggerService.log("DELETE: Pagamento reembolsado - " + p);
            System.out.println(ANSI_GREEN + "Pagamento reembolsado com sucesso." + ANSI_RESET);
            return true;
        } else {
            System.out.println(ANSI_RED + "Reembolso cancelado." + ANSI_RESET);
            return false;
        }
    }


    public static void processarPagamento(Pagamento pagamento) {
        adicionarPagamento(pagamento);
        if (pagamento.getValor() > 0) {
            pagamento.processarPagamento();
        }
    }
}

