package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

public class Pedido implements Serializable {

    public enum Status {
        PENDENTE,
        PAGO,
        REEMBOLSADO
    }

    private Cliente cliente;
    private List<ItemPedido> itens;
    private Date data;
    private Status status;

    public Pedido(Cliente cliente, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.itens = (itens != null) ? new ArrayList<>(itens) : new ArrayList<>();
        this.data = new Date();
        this.status = Status.PENDENTE;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Date getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido { Cliente: ").append(cliente.getNome());
        sb.append(", Data: ").append(data);
        sb.append(", Status: ").append(status);
        sb.append(", Itens: [\n");
        if (itens.isEmpty()) {
            sb.append("  Nenhum item no pedido.\n");
        } else {
            for (ItemPedido item : itens) {
                sb.append("    ").append(item).append("\n");
            }
        }
        sb.append("  ], Valor Total: R$ ").append(String.format("%.2f", calcularValorTotal()));
        sb.append("\n}");
        return sb.toString();
    }
}
