package Huffman;

public class HeapMinimo {

    private NodeHuffman[] heap;
    private int capacidade;
    private int tamanho;

    public HeapMinimo(int n) {
        this.capacidade = n;
        this.heap = new NodeHuffman[n];
        this.tamanho = 0;
    }

    private int indicePai(int i) {
        return (i - 1) / 2;
    }

    private int indiceFilhoEsquerdo(int i) {
        return 2 * i + 1;
    }

    private int indiceFilhoDireito(int i) {
        return 2 * i + 2;
    }

    public void inserir(NodeHuffman node) {
        if (tamanho == capacidade) {
            throw new IllegalStateException("Heap cheio");
        }

        heap[tamanho] = node;
        int i = tamanho;
        tamanho++;

        while (i != 0 && heap[indicePai(i)].freq > heap[i].freq) {
            trocar(i, indicePai(i));
            i = indicePai(i);
        }
    }

    public NodeHuffman removerMinimo() {
        if (tamanho == 0) {
            throw new IllegalStateException("Heap vazio");
        }

        NodeHuffman raiz = heap[0];
        NodeHuffman ultimoElemento = heap[tamanho - 1]; 

        heap[0] = ultimoElemento;  
        tamanho--;

        heapify(0); 
        return raiz;
    }

    // Método para reorganizar o heap, "descendo" o nó no índice dado até a posição correta
    private void heapify(int i) {
        int menor = i;  // Inicialmente, assumimos que o menor nó é o do índice atual
        int esquerdo = indiceFilhoEsquerdo(i);  // Índice do filho esquerdo
        int direito = indiceFilhoDireito(i);  // Índice do filho direito

        // filho esquerdo tem uma frequência menor que o nó atual?
        if (esquerdo < tamanho && heap[esquerdo].freq < heap[menor].freq) {
            menor = esquerdo;
        }

        // filho direito tem uma frequência menor que o menor valor encontrado?
        if (direito < tamanho && heap[direito].freq < heap[menor].freq) {
            menor = direito;
        }

        // se o menor no n estiver no indice atual, troca os nos e continua a organizar
        if (menor != i) {
            trocar(i, menor);
            heapify(menor);  // Continua organizando o heap recursivamente
        }
    }

    // Método para trocar dois nós no heap
    private void trocar(int i, int j) {
        NodeHuffman temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Método para exibir o heap (apenas para visualização)
    public void imprimirHeap() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(heap[i]);
        }
    }

    public int tamanho() {
        return this.tamanho;
    }
}
