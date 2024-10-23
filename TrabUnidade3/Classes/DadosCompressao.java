package Classes;

import Huffman.ArvoreHuffman;

public class DadosCompressao {
    ArvoreHuffman arvh;
    String msgComprimida;

    public DadosCompressao(ArvoreHuffman arvh, String msgComprimida) {
        this.arvh = arvh;
        this.msgComprimida = msgComprimida;
    }

    public Node descomprimir() {
        String decodificada = arvh.descomprimir(msgComprimida);
        String[] partes = decodificada.split(" ");

        int chave = -1;
        String nome = "";
        String descricao = "";
        String hora = "";

        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equals("Chave:")) {
                chave = Integer.parseInt(partes[i + 1]);
            } else if (partes[i].equals("Nome:")) {
                nome = partes[i + 1];
            } else if (partes[i].equals("Descricao:")) {
                descricao = partes[i + 1];
            } else if (partes[i].equals("Hora:")) {
                hora = partes[i + 1];
            }
        }
        OS novo = new OS(nome, descricao, hora);
        Node no = new Node(chave, novo);
        return no;
    }
}