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

    public void log(Node vitima, boolean cadastro, boolean delecao, boolean alteracao, boolean busca) {
        StringBuilder logString = new StringBuilder();

        logString.append("Base de Dados:\n");
        if(vitima != null && cadastro == true) {
            logString.append("CADASTRAR CHAVE " + vitima.getKey() + ":\n");
        }

        if(vitima != null && delecao == true) {
            logString.append("DELETAR CHAVE " + vitima.getKey() + ":\n");
        }
        
        if(vitima != null && alteracao == true) {
            logString.append("ALTERAR CHAVE " + vitima.getKey() + ":\n");
        }

        if(vitima != null && busca == true) {
            logString.append("BUSCA CHAVE " + vitima.getKey() + ":\n");
        }



        for (int i = 0; i < baseDados.tabela.M; i++) {
            logString.append("[ ").append(i).append(" ] --> ");
            ListaAutoAjustavel lista = baseDados.tabela.tabela[i];

            if (lista.size() == 0) {
                logString.append("null\n");
            } else {
                for (int j = 0; j < lista.size(); j++) {
                    Node node = lista.getNode(j);
                    if (node != null) {
                        logString.append(node.getKey()).append(" ");
                    }
                }
                logString.append("\n");
            }
        }
        logString.append("\n");

        logString.append("Cache:\n");
        ListaAutoAjustavel cacheLista = cache.getCache();

        if (cacheLista.size() == 0) {
            logString.append("Cache vazia.\n");
        } else {
            for (int i = 0; i < cacheLista.size(); i++) {
                Node node = cacheLista.getNode(i);
                if (node != null) {
                    logString.append("[ ").append(node.getKey()).append(" ] ");
                } else {
                    logString.append("[ ").append(i).append(" ] --> null\n");
                }
            }
        }
        logString.append("\n");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(logString.toString());
            bw.write("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
