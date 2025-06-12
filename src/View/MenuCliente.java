package View;

import Controller.ClienteController;
import Model.Cliente;
import Model.Endereco;

import java.util.List;

public class MenuCliente {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public static void exibir() {
        int opcao = 0;
        do {
            System.out.println(ANSI_BLUE + "============================" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       MENU CLIENTES" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "============================" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "👤 [1] Cadastrar Cliente" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "📋 [2] Listar Clientes" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "✏️ [3] Mudar Infos do Cliente;" + ANSI_RESET);
            System.out.println(ANSI_RED + "❌ [4] Excluir Cliente" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "🔙 [0] Voltar ao Menu Principal" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "============================" + ANSI_RESET);
            opcao = InputHelper.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> mudarInfos();
                case 4 -> excluirCliente();
                case 0 -> System.out.println(ANSI_GREEN + "Voltando ao menu principal..." + ANSI_RESET);
                default -> System.out.println(ANSI_RED + "Opção inválida!" + ANSI_RESET);
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        System.out.println(ANSI_BLUE + "--------- Cadastrar Cliente ---------" + ANSI_RESET);
        String cpf = InputHelper.lerString("CPF: ");
        String nome = InputHelper.lerString("Nome: ");
        String telefone = InputHelper.lerString("Telefone: ");
        String rua = InputHelper.lerString("Rua: ");
        String numero = InputHelper.lerString("Número: ");
        String cidade = InputHelper.lerString("Cidade: ");
        String estado = InputHelper.lerString("Estado: ");

        Endereco endereco = new Endereco(rua, numero, cidade, estado);
        Cliente cliente = new Cliente(cpf, nome, telefone, endereco);
        ClienteController.cadastrarCliente(cliente);
    }

    private static void listarClientes() {
        System.out.println(ANSI_BLUE + "--------- Lista de Clientes ---------" + ANSI_RESET);
        List<Cliente> clientes = ClienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println(ANSI_RED + "Nenhum cliente registrado." + ANSI_RESET);
            return;
        }
        System.out.printf(ANSI_PURPLE + "%-5s %-20s %-15s %-15s %-15s%n" + ANSI_RESET, "Nº", "Nome", "CPF", "Telefone", "Endereço");
        System.out.println(ANSI_PURPLE + "---------------------------------------------------------------" + ANSI_RESET);

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            Endereco e = cliente.getEndereco();
            String enderecoFormatado = String.format("%s, %s, %s, %s", e.getRua(), e.getNumero(), e.getCidade(), e.getEstado());

            System.out.printf("%-5d %-20s %-15s %-15s %-15s%n",
                    i + 1,
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    enderecoFormatado);
        }
    }

    private static void excluirCliente() {
        System.out.println(ANSI_BLUE + "--------- Excluir Cliente ---------" + ANSI_RESET);
        String cpf = InputHelper.lerString("Digite o CPF do cliente ");
        ClienteController.deletarCliente(cpf);
    }

    private static void mudarInfos() {
        System.out.println(ANSI_BLUE + "--------- Mudar Infos ---------" + ANSI_RESET);

        Cliente clienteEncontrado = null;

        while (clienteEncontrado == null) {
            String cpf = InputHelper.lerString("Digite o CPF do cliente: ");
            for (Cliente c : ClienteController.clientes) {
                if (c.getCpf().equals(cpf)) {
                    clienteEncontrado = c;
                    break;
                }
            }
            if (clienteEncontrado == null) {
                System.out.println(ANSI_RED + "Cliente não encontrado... Digite um CPF existente! " + ANSI_RESET);
            }
        }

        System.out.println(ANSI_YELLOW + "Qual informação você quer editar:" + ANSI_RESET);
        System.out.printf(ANSI_PURPLE + "%-5s %-25s%n" + ANSI_RESET, "Opção", "Descrição");
        System.out.println(ANSI_PURPLE + "------------------------------------------" + ANSI_RESET);
        System.out.printf("%-5d %-25s%n", 1, "Nome");
        System.out.printf("%-5d %-25s%n", 2, "Telefone");
        System.out.printf("%-5d %-25s%n", 3, "Endereço -> Rua");
        System.out.printf("%-5d %-25s%n", 4, "Endereço -> Número");
        System.out.printf("%-5d %-25s%n", 5, "Endereço -> Cidade");
        System.out.printf("%-5d %-25s%n", 6, "Endereço -> Estado");

        int op = InputHelper.lerInt("Digite a opção: ");

        String cpf = clienteEncontrado.getCpf();

        switch (op) {
            case 1 -> {
                String novoNome = InputHelper.lerString("Digite o novo nome: ");
                ClienteController.editarNome(cpf, novoNome);
            }
            case 2 -> {
                String novoTel = InputHelper.lerString("Digite o novo telefone: ");
                ClienteController.editarTel(cpf, novoTel);
            }
            case 3 -> {
                String novaRua = InputHelper.lerString("Digite a nova rua: ");
                ClienteController.editarRua(cpf, novaRua);
            }
            case 4 -> {
                String novoNumero = InputHelper.lerString("Digite o novo número: ");
                ClienteController.editarNumero(cpf, novoNumero);
            }
            case 5 -> {
                String novaCidade = InputHelper.lerString("Digite a nova cidade: ");
                ClienteController.editarCidade(cpf, novaCidade);
            }
            case 6 -> {
                String novoEstado = InputHelper.lerString("Digite o novo estado: ");
                ClienteController.editarEstado(cpf, novoEstado);
            }
            default -> System.out.println(ANSI_RED + "Opção inválida!" + ANSI_RESET);
        }
    }

}

