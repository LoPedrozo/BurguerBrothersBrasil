package Model;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable{

    private List<Pedido> historicoPedidos;

    public Cliente(String cpf,String nome, String telefone, Endereco endereco) {
        super(cpf,nome, telefone, endereco);
        this.historicoPedidos = new ArrayList<>();
    }



    public void adicionarPedido(Pedido pedido) {
        historicoPedidos.add(pedido);
    }

    public List<Pedido> getHistoricoPedidos() {
        return historicoPedidos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Histórico de Pedidos: " + historicoPedidos.size();
    }
}


