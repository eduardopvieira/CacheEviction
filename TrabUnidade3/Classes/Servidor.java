package Classes;

import Huffman.ArvoreHuffman;

public class Servidor {
    
    TabelaHash tabela;

    public Servidor(int tam) {
        tabela = new TabelaHash(tam);
    }
    

    public Node buscar(int x) {
        return tabela.buscar(x);
    }

    public DadosCompressao buscaComprimidaServer(int x) {
        Node no = buscar(x);

        if (no == null) {
            System.out.println("Indice do node nao encontrado para compressão no servidor.");
            return null;
        }

        ArvoreHuffman arvh = new ArvoreHuffman();
        String msg = no.gerarMensagem();
        char[] vetorChar = new char[msg.length()];
        int[] vetorFreq = new int[msg.length()];

        arvh.contarCaractereFrequencia(msg, vetorChar, vetorFreq);
        arvh.construirArvore(vetorChar, vetorFreq);
        String comprimido = arvh.comprimir(msg);
        
        DadosCompressao dc = new DadosCompressao(arvh, comprimido);
        return dc;

    }


    public void inserir(DadosCompressao nodeComprimido) {

        String msgCodificada = nodeComprimido.msgComprimida;
        ArvoreHuffman arvh = nodeComprimido.arvh;

        System.out.println("MENSAGEM COMPRIMIDA: " + msgCodificada);
        String decodificada = arvh.descomprimir(msgCodificada);

        System.out.println("MENSAGEM DECODIFICADA: " + decodificada);

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

        tabela.inserir(no);	
		if (tabela.fatorDeCarga() >= 7.5) { // *"Sedgewick recomenda escolher m tal que n/m fique entre 5 e 10."
			tabela.resize(true);
		}
    }


    public Node remover (int x) {
        Node removido = tabela.remover(x);
        
        if (removido == null) {
            System.out.println("Nenhum OS com esse código foi encontrado.");
            return removido;
        }
        
        System.out.println("OS de código " + x + " removido com sucesso!");

        if (tabela.fatorDeCarga() <= 2.5) {
            System.out.println("Sua tabela está com poucos elementos, portanto, será redimensionada para ficar menor.");
            tabela.resize(false);
        }

        return removido;
    }

    public DadosCompressao removerComprimido (int x) {
        
        ArvoreHuffman arvh = new ArvoreHuffman();

        Node removido = tabela.remover(x);
        
        if (removido == null) {
            System.out.println("Nenhum OS com esse código foi encontrado.");
            return null;
        }

        String msg = removido.gerarMensagem();
        int[] arrayFreq = new int[msg.length()];
        char[] arrayChar = new char[msg.length()];

        arvh.contarCaractereFrequencia(msg, arrayChar, arrayFreq);
        arvh.construirArvore(arrayChar, arrayFreq);
        String comprimido = arvh.comprimir(msg);
        
        
        System.out.println("OS de código " + x + " removido com sucesso!");

        if (tabela.fatorDeCarga() <= 2.5) {
            System.out.println("Sua tabela está com poucos elementos, portanto, será redimensionada para ficar menor.");
            tabela.resize(false);
        }

        DadosCompressao retorno = new DadosCompressao(arvh, comprimido);

        return retorno;
    }


    public void listarElementos() {
        tabela.listarElementos();
    }

    public void printarServer() {
        tabela.imprimirTabelaHash();
    }

    public void inserirInicializacao(Node no) {

        tabela.inserir(no);	
		if (tabela.fatorDeCarga() >= 7.5) { // *"Sedgewick recomenda escolher m tal que n/m fique entre 5 e 10."
			tabela.resize(true);
		}
    }

    public boolean atualizarSvComprimido(DadosCompressao comprimido) {
        Node atualizar = comprimido.descomprimir();

        Node existe = tabela.buscar(atualizar.getKey());

        if (existe != null) {
            existe.setOS(atualizar.getOS());
            System.out.println("OS de código " + atualizar.getKey() + " atualizado com sucesso no SERVIDOR.");
            return true;
        }
        
        System.out.println("Node nao encontrado no servidor.");
        return false;
    }


}
