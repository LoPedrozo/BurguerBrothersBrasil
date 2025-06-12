package View;

import java.util.Scanner;

public class InputHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static int lerInt(String texto) {
        int num = 0;
        boolean valido = false;
        do {
            try {
                System.out.print(texto);
                num = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        } while (!valido);
        return num;
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static float lerFloat(String texto) {
        float num = 0;
        boolean valido = false;
        do {
            try {
                System.out.print(texto);
                num = Float.parseFloat(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        } while (!valido);
        return num;
    }


    public static char lerChar(String texto) {
        char caractere = ' ';
        boolean valido = false;
        do {
            System.out.print(texto);
            String entrada = scanner.nextLine();
            if (entrada.length() == 1) {
                caractere = entrada.charAt(0);
                valido = true;
            } else {
                System.out.println("Digite apenas um caractere.");
            }
        } while (!valido);
        return caractere;
    }
}
