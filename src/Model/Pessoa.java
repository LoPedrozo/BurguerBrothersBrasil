package Model;
import java.io.Serializable;

public abstract class Pessoa implements Serializable {

    protected String cpf;
    protected String nome;
    protected String telefone;
    protected Endereco endereco;

    public Pessoa(String cpf, String nome, String telefone, Endereco endereco) {
        this.cpf=cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}

