package Testes;

import Classes.Node;
import Classes.OS;
import Huffman.ArvoreHuffman;

public class TesteCompressaoOS {

    public static void main(String[] args) {
        // Criando um node com chave e OS
        OS os2 = new OS("abcc", "aabbccc", "abc"); //4 a, 4 b, 6 c
        Node node1 = new Node(38, os2);

        // Gerar a mensagem concatenada
        String mensagem = node1.gerarMensagem();
        // Exibindo a mensagem
        System.out.println("Mensagem para compressão: " + mensagem);

        ArvoreHuffman arvh = new ArvoreHuffman();
        
        int n = mensagem.length();

        int[] frequencia = new int[n];
        char[] caracteres =  new char[n];

        arvh.contarCaractereFrequencia(mensagem, caracteres, frequencia);
        
        // for (int i = 0; i < frequencia.length; i++) {
 
        //     System.out.println(caracteres[i] + "  :  " + frequencia[i]);
        // }
       
        arvh.construirArvore(caracteres, frequencia);

        arvh.imprimirCodigos();


        System.out.println("codigo comprimido:");
        String codificado = arvh.codificar(mensagem);
        System.out.println(codificado);

        System.out.println("codigo descomprimido:");
        String descodificado = arvh.descomprimir(codificado);
        System.out.println(descodificado);

        System.out.println("Mensagem original dnv:");
        System.out.println(mensagem);

    }
}