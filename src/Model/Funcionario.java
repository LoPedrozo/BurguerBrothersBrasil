package Model;
import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable{

    private String cargo;
    private String login;
    private String senha;

    public Funcionario(String cpf,String nome, String telefone, Endereco endereco, String cargo, String login, String senha) {
        super(cpf,nome, telefone, endereco);
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return super.toString() + ", Cargo: " + cargo;
    }
}

