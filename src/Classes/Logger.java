package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {
    private static String path = "src/log.txt";

    public Logger() {
    }

    public void log(String operacao, String rotacao, int altura, int cod, ArrayList<Node> cache) {

        // MÉTODO PRA COLOCAR OS CÓDIGOS DOS NÓS DA CACHE EM UMA STRING
        StringBuilder cacheString = new StringBuilder();
        for (Node node : cache) {
            cacheString.append("[").append(node.getCodigo()).append("]");
        }

        String stringFinal = cacheString.toString();

        // MENSAGEM DO LOG
        String message = String.format(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "Operacao: %s no node de codigo %d\n" +
                        "Houve rotacao?: %s\n" +
                        "Altura da arvore: %d\n" +
                        "Cache: %s\n",
                operacao, cod, rotacao, altura, stringFinal);

        // ESCREVE NO ARQUIVO
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
