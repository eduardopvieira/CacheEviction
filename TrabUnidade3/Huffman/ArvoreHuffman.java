package Huffman;

public class ArvoreHuffman {

    public NodeHuffman raiz;
    private char[] caracteres; // armazena os caracteres
    private String[] codigos; // armazena os codigos de huffman da arvore
    private int indice = 0; // Índice para preencher os arrays
    HeapMinimo heapMinimo = new HeapMinimo();

    public void contarCaractereFrequencia(String mensagem, char[] arrayCaracteres, int[] arrayFrequencias) {
        int numCaracteresUnicos = 0;

        for (int i = 0; i < mensagem.length(); i++) {
            char caractereAtual = mensagem.charAt(i);

            int indice = buscarIndice(arrayCaracteres, numCaracteresUnicos, caractereAtual);

            if (indice == -1) { // caso o caractere nao esteja no array ele é adicionado ao final
                arrayCaracteres[numCaracteresUnicos] = caractereAtual;
                arrayFrequencias[numCaracteresUnicos] = 1;
                numCaracteresUnicos++;
            } else { // caso o caractere ja esteja no array, aí so incrementa a frequencia msm
                arrayFrequencias[indice]++;
            }
        }

        for (int j = 0; j < arrayCaracteres.length; j++) {
            System.out.println(arrayCaracteres[j] + " : " + arrayFrequencias[j]);
        }
    }

    // Método auxiliar para buscar o índice de um caractere no array de caracteres
    // únicos
    private int buscarIndice(char[] arrayCaracteres, int tamanhoAtual, char caractere) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (arrayCaracteres[i] == caractere) {
                return i; // Retorna o índice onde o caractere já foi encontrado
            }
        }
        return -1; // Retorna -1 se o caractere não for encontrado
    }

    // Método para construir a árvore de Huffman e gerar os códigos
    public void construirArvore(char[] arrayCaracteres, int[] arrayFrequencias) {
        // Inicializa os arrays para armazenar os caracteres e seus códigos
        caracteres = new char[arrayCaracteres.length];
        codigos = new String[arrayCaracteres.length];

        // Inserir os nós no heap mínimo
        for (int i = 0; i < arrayCaracteres.length; i++) {
            NodeHuffman no = new NodeHuffman(arrayFrequencias[i], arrayCaracteres[i]);
            heapMinimo.inserir(no);
        }

        // Construir a árvore de Huffman
        while (heapMinimo.tamanho() > 1) {
            NodeHuffman x = heapMinimo.removerMinimo();
            NodeHuffman y = heapMinimo.removerMinimo();

            NodeHuffman z = new NodeHuffman(x.freq + y.freq, '-'); // Usa '-' para nós internos
            z.esquerda = x;
            z.direita = y;

            heapMinimo.inserir(z);
        }

        // A raiz será o nó restante no heap
        raiz = heapMinimo.removerMinimo();

        // Gerar os códigos de Huffman a partir da raiz
        gerarCodigos(raiz, "");
    }

    // Método auxiliar para gerar os códigos de Huffman e armazená-los nos arrays
    private void gerarCodigos(NodeHuffman no, String codigo) {
        if (no == null) {
            return;
        }

        // Se for uma folha (nó sem filhos), armazena o caractere e o código
        if (no.esquerda == null && no.direita == null && isCharValido(no.caractere)) {
            caracteres[indice] = no.caractere;
            codigos[indice] = codigo;
            indice++;
        }

        gerarCodigos(no.esquerda, codigo + "0");
        gerarCodigos(no.direita, codigo + "1");
    }

    public String codificar(String texto) {
        StringBuilder codigoHuffman = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caractereAtual = texto.charAt(i);
            for (int j = 0; j < caracteres.length; j++) {
                if (caracteres[j] == caractereAtual) {
                    codigoHuffman.append(codigos[j]);
                    break;
                }
            }
        }

        return codigoHuffman.toString();
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
            return;
        }

        // Verifica se o nó é uma folha (nó sem filhos)
        if (no.esquerda == null && no.direita == null && isCharValido(no.caractere)) {
            System.out.println(no.caractere + ": " + s);
            return;
        }

        imprimirCodigo(no.esquerda, s + "0");
        imprimirCodigo(no.direita, s + "1");
    }

    // Função auxiliar para verificar se o caractere é uma letra
    private boolean isCharValido(char c) {
        return (Character.isLetterOrDigit(c) || c == ' ' || c == ':' || c == '/'); // Verifica se o caractere é uma
                                                                                   // letra
    }

    // Método para iniciar a impressão dos códigos da árvore de Huffman
    public void imprimirCodigos() {
        imprimirCodigo(raiz, "");
    }
}
