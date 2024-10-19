package Testes;

import Classes.Node;
import Classes.OS;
import Huffman.ArvoreHuffman;

public class TesteCompressaoOS {

    public static void main(String[] args) {
        // Criando um node com chave e OS
        OS os1 = new OS("nome da os", "descricao", "23:59");
        Node node1 = new Node(37, os1);

        // Gerar a mensagem concatenada
        String mensagem = node1.gerarMensagem();
        int n = mensagem.length();

        // Exibindo a mensagem
        System.out.println("Mensagem para compressão: " + mensagem);

        ArvoreHuffman arvh = new ArvoreHuffman();
        int[] frequencia = new int[n];
        char[] caracteres = new char[n];
        
       arvh.contarFrequencias(mensagem, caracteres, frequencia);

       arvh.construirArvore(n, caracteres, frequencia);

       arvh.imprimirCodigos();

       for (int i = 0; i < frequencia.length; i++) {

           System.out.println(caracteres[i] + "  :  " + frequencia[i]);
       }

       System.out.println("codigo descomprimido:");

    }
}