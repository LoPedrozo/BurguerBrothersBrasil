package Model;

import java.io.Serializable;

public abstract class Pagamento implements Serializable {

    protected double valor;
    protected String metodo;
    protected Pedido pedido;

    public Pagamento(double valor, String metodo, Pedido pedido) {
        this.valor = valor;
        this.metodo = metodo;
        this.pedido = pedido;
    }

    public double getValor() {
        return valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public abstract void processarPagamento();

    @Override
    public String toString() {
        return "Pagamento: R$ " + valor + ", Método: " + metodo +
                ", Pedido Cliente: " + pedido.getCliente().getNome() +
                ", Status do Pedido: " + pedido.getStatus();
    }
}

