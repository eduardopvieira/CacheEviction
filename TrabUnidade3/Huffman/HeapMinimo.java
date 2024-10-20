package Huffman;

import java.util.ArrayList;
import java.util.List;

public class HeapMinimo {

    private List<NodeHuffman> heap;  // Lista para armazenar o heap

    public HeapMinimo() {
        heap = new ArrayList<>(); 
    }

    public HeapMinimo(int n) {
        heap = new ArrayList<>(n); 
    }

    // Método para retornar o índice do pai
    private int indicePai(int i) {
        return (i - 1) / 2;
    }

    // Método para retornar o índice do filho esquerdo
    private int indiceFilhoEsquerdo(int i) {
        return 2 * i + 1;
    }

    // Método para retornar o índice do filho direito
    private int indiceFilhoDireito(int i) {
        return 2 * i + 2;
    }

    // Método para inserir um NodeHuffman no heap
    public void inserir(NodeHuffman node) {
        heap.add(node);  // Adiciona o novo nó no final do heap
        int i = heap.size() - 1;  // Índice do novo nó
        
        // Corrige a posição do novo nó, subindo no heap até encontrar a posição correta
        while (i != 0 && heap.get(indicePai(i)).freq > heap.get(i).freq) {
            // Troca o nó com o pai se a frequência for menor
            trocar(i, indicePai(i));
            i = indicePai(i);  // Atualiza o índice para o do pai
        }
    }

    // Método para remover e retornar o nó com a menor frequência (a raiz)
    public NodeHuffman removerMinimo() {
        if (heap.size() == 0) {
            throw new IllegalStateException("Heap vazio");
        }

        NodeHuffman raiz = heap.get(0);  // O nó com a menor frequência é sempre a raiz (índice 0)
        NodeHuffman ultimoElemento = heap.get(heap.size() - 1);  // Pega o último nó do heap

        heap.set(0, ultimoElemento);  // Coloca o último nó na raiz
        heap.remove(heap.size() - 1);  // Remove o último nó do heap
        
        heapify(0);  // Reorganiza o heap para manter a propriedade do heap mínimo
        return raiz;
    }

    // Método para reorganizar o heap, "descendo" o nó no índice dado até a posição correta
    private void heapify(int i) {
        int menor = i;  // Inicialmente, assumimos que o menor nó é o do índice atual
        int esquerdo = indiceFilhoEsquerdo(i);  // Índice do filho esquerdo
        int direito = indiceFilhoDireito(i);  // Índice do filho direito

        // Verifica se o filho esquerdo tem uma frequência menor que o nó atual
        if (esquerdo < heap.size() && heap.get(esquerdo).freq < heap.get(menor).freq) {
            menor = esquerdo;
        }

        // Verifica se o filho direito tem uma frequência menor que o menor valor encontrado
        if (direito < heap.size() && heap.get(direito).freq < heap.get(menor).freq) {
            menor = direito;
        }

        // Se o menor nó não estiver no índice atual, troca os nós e continua a organizar
        if (menor != i) {
            trocar(i, menor);
            heapify(menor);  // Continua organizando o heap recursivamente
        }
    }

    // Método para trocar dois nós no heap
    private void trocar(int i, int j) {
        NodeHuffman temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Método para exibir o heap (apenas para visualização)
    public void imprimirHeap() {
        for (NodeHuffman node : heap) {
            System.out.println(node);
        }
    }

    public int tamanho() {
        return this.heap.size();
    }
}
