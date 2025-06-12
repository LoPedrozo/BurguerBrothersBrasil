package Util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LoggerService {

    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Escreve uma mensagem de log no arquivo.
     * A mensagem inclui data e hora.
     * @param message A mensagem a ser registrada.
     */

    public static synchronized void log(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            LocalDateTime now = LocalDateTime.now();
            pw.println(dtf.format(now) + " - " + message);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}