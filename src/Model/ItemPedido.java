package Model;
import java.io.Serializable;

public class ItemPedido implements Serializable{

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "Item: " + produto.getNome() + ", Quantidade: " + quantidade + ", Subtotal: " + calcularSubtotal();
    }

    public ItemCardapio getProduto() {
        return produto;
    }

    public int  getQuantidade() {
        return quantidade;
    }
}


