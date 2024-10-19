package Classes;

public class KMP {

    int tabelaPrefixo[]; //array da tabela de prefixos

    public void buscar(String padrao, String texto) {
        int M = padrao.length();
        int N = texto.length ();
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
                System.out.println("Padrão encontrado no índice " + (i - j));
              j = tabelaPrefixo[j - 1];
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
    }

    void criarTabelaPrefixo(String padrao, int M) {
        int tamanhoPrefixo = 0;
        int i = 1;
        tabelaPrefixo[0] = 0; // tp[0] é sempre 0
        // loop calcula tp[i] para i = 1 até M - 1
        while (i < M) {
            if (padrao.charAt(i) == padrao.charAt(tamanhoPrefixo)) {
                tamanhoPrefixo++;
                tabelaPrefixo[i] = tamanhoPrefixo;
                i++;
            }
            else {  // (padrao[i] != padrao[tamanhoPrefixo]) {
                if (tamanhoPrefixo != 0) {
                tamanhoPrefixo = tabelaPrefixo[tamanhoPrefixo - 1];
                }
                else {  // se (tamanhoPrefixo == 0) {
                    tabelaPrefixo[i] = tamanhoPrefixo;
                    i++;
                }
            }
        }
    }
}