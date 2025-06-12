package Model;
import java.io.Serializable;

public class Acompanhamento extends Produto implements Serializable {

    public String tipo;

    public Acompanhamento(String nome, double preco) {
        super(nome, preco);
    }


    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public double getPreco() {
        return super.getPreco();
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void setPreco(float novoPreco) {
        super.setPreco(novoPreco);
    }

    @Override
    public void setNome(String novoNome) {
        super.setNome(novoNome);
    }


}
