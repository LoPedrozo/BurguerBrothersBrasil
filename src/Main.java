
import View.MenuPrincipal;
import Controller.PersistenciaController;

public class Main {
    public static void main(String[] args) {
        PersistenciaController.carregarDados();
        MenuPrincipal menu = new MenuPrincipal();
        menu.exibir();
    }

}


