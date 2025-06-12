package Model;
import java.io.Serializable;

public class Bebida extends Produto implements Serializable{


    public Bebida(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public double getPreco() {
        return super.getPreco();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String novoNome) {
        super.setNome(novoNome);
    }

    @Override
    public void setPreco(float novoPreco) {
        super.setPreco(novoPreco);
    }

}
