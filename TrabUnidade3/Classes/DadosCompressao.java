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

    // public Node criarNodeAPartirDeString(String entrada) {
    //     int chave = -1;
    //     String nome = "";
    //     String descricao = "";
    //     String hora = "";
    
    //     // Usa regex para separar os campos
    //     String[] partes = entrada.split("(?<=: )");
    
    //     for (int i = 0; i < partes.length; i++) {
    //         if (partes[i].startsWith("Chave: ")) {
    //             chave = Integer.parseInt(partes[i].substring(7).trim()); // Remove "Chave: "
    //         } else if (partes[i].startsWith("Nome: ")) {
    //             nome = partes[i].substring(6).trim(); // Remove "Nome: "
    //         } else if (partes[i].startsWith("Descrição: ")) {
    //             descricao = partes[i].substring(11).trim(); // Remove "Descrição: "
    //         } else if (partes[i].startsWith("Hora: ")) {
    //             hora = partes[i].substring(6).trim(); // Remove "Hora: "
    //         }
    //     }
    
    //     if (chave == -1 || nome.isEmpty() || descricao.isEmpty() || hora.isEmpty()) {
    //         System.out.println("Erro ao criar Node: dados incompletos.");
    //         return null; // Retorna null se os dados estiverem incompletos
    //     }
    
    //     OS novo = new OS(nome, descricao, hora);
    //     return new Node(chave, novo);
    // }
    

}


