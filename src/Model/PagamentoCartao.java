package Model;

import java.io.Serializable;

public class PagamentoCartao extends Pagamento implements Serializable {

    public PagamentoCartao(double valor, Pedido pedido) {
        super(valor, "Cartão", pedido);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " processado via cartão.");
        pedido.setStatus(Pedido.Status.PAGO);
    }
}

