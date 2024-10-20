package Testes;

import Huffman.ArvoreHuffman;

class TesteHuffman {
    
    public static void main(String[] args) {
        char[] vetorDeCaracteres = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] vetorDeFrequencias = { 5, 9, 12, 13, 16, 45 };
        ArvoreHuffman arv = new ArvoreHuffman();
        arv.construirArvore(vetorDeCaracteres, vetorDeFrequencias);
        arv.imprimirCodigo(arv.raiz, "");
    }
}

