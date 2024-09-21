package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Logger {

    Servidor baseDados;
    Cache cache;

    private static String path = "log.txt";

    public Logger(Servidor server, Cache cach) {
        this.baseDados = server;
        this.cache = cach;
    }

    public void log() {
        StringBuilder logString = new StringBuilder();

        logString.append("Tabela Hash:\n");
        for (int i = 0; i < baseDados.tabela.M; i++) {
            logString.append("[ ").append(i).append(" ] --> ");
            LinkedList<Node> lista = baseDados.tabela.tabela[i];
            if (lista.isEmpty()) {
                logString.append("null\n");
            } else {
                for (Node node : lista) {
                    logString.append(node.getKey()).append(" ");
                }
                logString.append("\n");
            }
        }
        logString.append("\n");

        // Logando a Cache
        logString.append("Cache:\n");
        for (int i = 0; i < 20; i++) {
            logString.append("[ ").append(i).append(" ] --> ");
            LinkedList<Node> lista = cache.getCache().tabela[i];
            if (lista.isEmpty()) {
                logString.append("null\n");
            } else {
                for (Node node : lista) {
                    logString.append(node.getKey()).append(" ");
                }
                logString.append("\n");
            }
        }
        logString.append("\n");


        // ESCREVE NO ARQUIVO
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(logString.toString());
            bw.write("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
