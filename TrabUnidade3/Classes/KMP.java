package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KMP {

    int tabelaPrefixo[];

    public boolean buscar(String padrao, String texto) {
        int M = padrao.length();
        int N = texto.length();
        tabelaPrefixo = new int[M];
        int j = 0; 

        criarTabelaPrefixo(padrao, M); 

        int i = 0;
        while (i < N) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Padrão '" + padrao + "' encontrado no índice " + (i - j));
                return true;
            }
            else if (i < N && padrao.charAt(j) != texto.charAt(i)) {
                if (j != 0) {
                    j = tabelaPrefixo[j - 1];
                }
                else {
                    i = i + 1;
                }
            }
        }
        System.out.println("Padrão '" + padrao + "' não encontrado.");
        return false; 
    }

    void criarTabelaPrefixo(String padrao, int M) {
        int tamanhoPrefixo = 0;
        int i = 1;
        tabelaPrefixo[0] = 0;

        while (i < M) {
            if (padrao.charAt(i) == padrao.charAt(tamanhoPrefixo)) {
                tamanhoPrefixo++;
                tabelaPrefixo[i] = tamanhoPrefixo;
                i++;
            }
            else {  
                if (tamanhoPrefixo != 0) {
                    tamanhoPrefixo = tabelaPrefixo[tamanhoPrefixo - 1];
                }
                else {
                    tabelaPrefixo[i] = tamanhoPrefixo;
                    i++;
                }
            }
        }
    }


    public String lerArquivo(String caminhoArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return conteudo.toString();
    }
}
