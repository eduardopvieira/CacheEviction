package Huffman;

public class ArvoreHuffman {
    NodeHuffman raiz;

    // Método para construir a árvore de Huffman a partir de arrays de caracteres e frequências
    public void construirArvore(int n, char[] arrayCaracteres, int[] arrayFrequencias) {
        // Cria o heap mínimo
        HeapMinimo heapMinimo = new HeapMinimo();

        // Insere todos os nós no heap
        for (int i = 0; i < n; i++) {
            NodeHuffman no = new NodeHuffman(arrayFrequencias[i], arrayCaracteres[i]);
            heapMinimo.inserir(no);
        }

        // Constrói a árvore de Huffman
        while (heapMinimo.tamanho() > 1) {
            // Remove os dois nós com menor frequência
            NodeHuffman x = heapMinimo.removerMinimo();
            NodeHuffman y = heapMinimo.removerMinimo();

            // Cria um novo nó que combina as frequências dos dois nós removidos
            NodeHuffman z = new NodeHuffman(x.freq + y.freq, '-');  // Usa '-' para nós internos
            z.esquerda = x;
            z.direita = y;

            // Insere o novo nó no heap
            heapMinimo.inserir(z);
        }

        // O último nó restante no heap é a raiz da árvore de Huffman
        raiz = heapMinimo.removerMinimo();
    }

    // Método para imprimir os códigos de Huffman gerados
    public void imprimirCodigo(NodeHuffman no, String s) {
        // Verifica se o nó é uma folha (nó sem filhos)
        if (no.esquerda == null && no.direita == null && isLetra(no.caractere)) {
            System.out.println(no.caractere + ": " + s);
            return;
        }

        // Recursivamente, percorre a árvore, adicionando '0' para a esquerda e '1' para a direita
        imprimirCodigo(no.esquerda, s + "0");
        imprimirCodigo(no.direita, s + "1");
    }

    // Função auxiliar para verificar se o caractere é uma letra
    private boolean isLetra(char c) {
        return Character.isLetter(c);  // Verifica se o caractere é uma letra
    }

    // Método para iniciar a impressão dos códigos da árvore de Huffman
    public void imprimirCodigos() {
        imprimirCodigo(raiz, "");
    }
}
