package Huffman;

public class ArvoreHuffman {

    public NodeHuffman raiz;

    // ! ESSE METODO NAO EXISTIA, EXPLICAR NO VIDEO

    public void contarFrequencias(String mensagem, char[] arrayCaracteres, int[] arrayFrequencias) {
        int n = 0;

        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);

            int index = encontrarCaractere(arrayCaracteres, c, n);

            if (index == -1) {
                arrayCaracteres[n] = c;
                arrayFrequencias[n] = 1;
                n++;
            } else {
                arrayFrequencias[index]++;
            }
        }
    }

    // ! ESSA FUNÇAO NAO EXISTIA, EXPLICAR NO VIDEO

    private int encontrarCaractere(char[] arrayCaracteres, char c, int tamanhoAtual) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (arrayCaracteres[i] == c) {
                return i; // Retorna o índice se encontrar
            }
        }
        return -1; // Retorna -1 se o caractere não for encontrado
    }

    public void construirArvore(int n, char[] arrayCaracteres, int[] arrayFrequencias) {
        HeapMinimo heapMinimo = new HeapMinimo();

        for (int i = 0; i < n; i++) {
            NodeHuffman no = new NodeHuffman(arrayFrequencias[i], arrayCaracteres[i]);
            heapMinimo.inserir(no);
        }

        while (heapMinimo.tamanho() > 1) {
            NodeHuffman x = heapMinimo.removerMinimo();
            NodeHuffman y = heapMinimo.removerMinimo();

            NodeHuffman z = new NodeHuffman(x.freq + y.freq, '-'); // Usa '-' para nós internos
            z.esquerda = x;
            z.direita = y;

            heapMinimo.inserir(z);
        }
        raiz = heapMinimo.removerMinimo();
    }

    public String descomprimir(String codigo) {
        StringBuilder textoDescomprimido = new StringBuilder();
        NodeHuffman atual = raiz;

        // Percorre cada bit do código comprimido
        for (int i = 0; i < codigo.length(); i++) {
            char bit = codigo.charAt(i);

            // Move para a esquerda ou direita na árvore de Huffman
            if (bit == '0') {
                atual = atual.esquerda;
            } else if (bit == '1') {
                atual = atual.direita;
            }

            // Se encontrar um nó folha (nó sem filhos), adiciona o caractere ao texto
            // descomprimido
            if (atual.esquerda == null && atual.direita == null) {
                textoDescomprimido.append(atual.caractere);
                atual = raiz; // Volta à raiz para decodificar o próximo símbolo
            }
        }

        return textoDescomprimido.toString();
    }

    public void imprimirCodigo(NodeHuffman no, String s) {
        // Verifica se o nó é nulo
        if (no == null) {
            return; // Não faz nada se o nó for nulo
        }

        // Verifica se o nó é uma folha (nó sem filhos)
        if (no.esquerda == null && no.direita == null && isLetra(no.caractere)) {
            System.out.println(no.caractere + ": " + s);
            return;
        }

        // Recursivamente, percorre a árvore, adicionando '0' para a esquerda e '1' para
        // a direita
        imprimirCodigo(no.esquerda, s + "0");
        imprimirCodigo(no.direita, s + "1");
    }

    // Função auxiliar para verificar se o caractere é uma letra
    private boolean isLetra(char c) {
        return Character.isLetter(c); // Verifica se o caractere é uma letra
    }

    // Método para iniciar a impressão dos códigos da árvore de Huffman
    public void imprimirCodigos() {
        imprimirCodigo(raiz, "");
    }
}
