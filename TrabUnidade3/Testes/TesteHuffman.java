package Testes;

import Huffman.ArvoreHuffman;

class TesteHuffman {
    
    public static void main(String[] args) {
        int n = 6;
        char[] vetorDeCaracteres = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] vetorDeFrequencias = { 5, 9, 12, 13, 16, 45 };
        ArvoreHuffman arv = new ArvoreHuffman();
        arv.construirArvore(n, vetorDeCaracteres, vetorDeFrequencias);
        arv.imprimirCodigo(arv.raiz, "");
    }
}

