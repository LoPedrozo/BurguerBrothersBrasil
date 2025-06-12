package Controller;

import Model.Pedido;
import Util.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    private static List<Pedido> pedidos = new ArrayList<>();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void criarPedido(Pedido pedido) {
        pedidos.add(pedido);
        LoggerService.log("CREATE: Pedido criado para o cliente " + pedido.getCliente().getNome() + " (CPF: " + pedido.getCliente().getCpf() + ")");
        System.out.println(ANSI_GREEN + "Pedido criado com sucesso! " + ANSI_RESET);
    }

    public static List<Pedido> listarPedidos() {
        LoggerService.log("READ: Listagem de pedidos solicitada.");
        return pedidos;
    }

    public static void removerPedido(int indice) {
        if (indice >= 0 && indice < pedidos.size()) {
            Pedido pedidoRemovido = pedidos.get(indice);
            LoggerService.log("DELETE: Pedido de " + pedidoRemovido.getCliente().getNome() + " removido.");
            pedidos.remove(indice);
            System.out.println(ANSI_GREEN + "Pedido de " + pedidoRemovido.getCliente().getNome() +
                               " realizado em " + pedidoRemovido.getData() + " removido com sucesso!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Índice de pedido inválido!" + ANSI_RESET);
        }
    }
}