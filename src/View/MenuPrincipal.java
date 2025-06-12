
package View;

import Controller.PersistenciaController;

public class MenuPrincipal {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";


    public void exibir() {
        int opcao;
        do {
            System.out.println(ANSI_BLUE + "==========================" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       MENU PRINCIPAL" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "==========================" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "📋 [1] Gerenciar Clientes" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "🛒 [2] Gerenciar Produtos" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "📦 [3] Gerenciar Pedidos" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "💵 [4] Gerenciar Pagamentos" + ANSI_RESET);
            System.out.println(ANSI_RED + "🚪 [0] Sair" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "==========================" + ANSI_RESET);
            opcao = InputHelper.lerInt("Digite uma opção :");

            switch (opcao) {
                case 1 -> MenuCliente.exibir();
                case 2 -> MenuProduto.exibir();
                case 3 -> MenuPedido.exibir();
                case 4 -> MenuPagamento.exibir();
                case 0 -> {
                    PersistenciaController.salvarDados();
                    System.out.println(ANSI_GREEN + "Saindo do sistema. Até logo!" + ANSI_RESET);

                }
                default -> System.out.println(ANSI_RED + "Opção inválida! Tente novamente." + ANSI_RESET);
            }
        } while (opcao != 0);
    }

}

