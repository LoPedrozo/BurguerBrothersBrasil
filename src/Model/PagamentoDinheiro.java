package Model;

import java.io.Serializable;

public class PagamentoDinheiro extends Pagamento implements Serializable {

    public PagamentoDinheiro(double valor, Pedido pedido) {
        super(valor, "Dinheiro", pedido);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " processado em dinheiro.");

        pedido.setStatus(Pedido.Status.PAGO);
    }
}
