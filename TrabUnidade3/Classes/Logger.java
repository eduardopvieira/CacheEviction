package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    Servidor baseDados;
    Cache cache;

    private static String path = "log.txt";

    public Logger(Servidor server, Cache cach) {
        this.baseDados = server;
        this.cache = cach;
    }

    public void log(Node adicionado) {
        StringBuilder logString = new StringBuilder();

        // Logando a tabela hash
        logString.append("Base de Dados:\n");
        if(adicionado != null) {
            logString.append("CADASTRO DA CHAVE " + adicionado.getKey() + ":\n");
        }
        for (int i = 0; i < baseDados.tabela.M; i++) {
            logString.append("[ ").append(i).append(" ] --> ");
            ListaAutoAjustavel lista = baseDados.tabela.tabela[i]; // Usando a ListaAutoAjustavel

            if (lista.size() == 0) { // Verifica se a lista está vazia
                logString.append("null\n");
            } else {
                // Itera sobre a lista autoajustável para logar as chaves dos Nodes
                for (int j = 0; j < lista.size(); j++) {
                    Node node = lista.getNode(j); // Acessa cada Node
                    if (node != null) {
                        logString.append(node.getKey()).append(" "); // Loga a chave do Node
                    }
                }
                logString.append("\n");
            }
        }
        logString.append("\n");

        // Logando a Cache (como uma lista autoajustável de 30 elementos)
        logString.append("Cache:\n");
        ListaAutoAjustavel cacheLista = cache.getCache(); // Obtendo a lista autoajustável da cache

        if (cacheLista.size() == 0) {
            logString.append("Cache vazia.\n");
        } else {
            // Itera sobre a lista autoajustável da cache
            for (int i = 0; i < cacheLista.size(); i++) {
                Node node = cacheLista.getNode(i); // Acessa cada Node da cache
                if (node != null) {
                    //logString.append("[ ").append(i).append(" ] --> ");
                    logString.append("[ ").append(node.getKey()).append(" ] ");
                    //logString.append("Nome: ").append(node.getOS().getNome()).append(", ");
                    //logString.append("Descrição: ").append(node.getOS().getDescricao()).append(", ");
                    //logString.append("Hora: ").append(node.getOS().getHora()).append("\n");
                } else {
                    logString.append("[ ").append(i).append(" ] --> null\n");
                }
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
