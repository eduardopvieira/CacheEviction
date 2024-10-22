package Classes;

import Huffman.ArvoreHuffman;

public class Cache {
    
    private ListaAutoAjustavel cache = new ListaAutoAjustavel(30);

    public void adicionar (Node add) {
        cache.inserir(add);
    }

    public Node buscarCache(int indice) {
        return cache.buscar(indice);
    }

    public Node removerCache(int indice) {
        return cache.remover(indice);
    }

    public void printarCache() {
        cache.imprimir();
    }

    public ListaAutoAjustavel getCache() {
        return this.cache;
    }

    public DadosCompressao buscaComprimidaCache (int indice) {
       
        Node result = cache.buscar(indice);

        if (result == null) {
            System.out.println("Indice do OS nao encontrado para comprimir.");
            return null;
        }

        ArvoreHuffman arvh = new ArvoreHuffman();
        String msg = result.gerarMensagem();
        char[] vetorChar = new char[msg.length()];
        int[] vetorFreq = new int[msg.length()];

        arvh.contarCaractereFrequencia(msg, vetorChar, vetorFreq);
        arvh.construirArvore(vetorChar, vetorFreq);
        String comprimido = arvh.comprimir(msg);
        
        DadosCompressao dc = new DadosCompressao(arvh, comprimido);
        return dc;
    }

    public boolean atualizarCacheComprimido(DadosCompressao comprimido) {
        Node atualizar = comprimido.descomprimir();

        Node existe = cache.buscar(atualizar.getKey());

        if (existe != null) {
            existe.setOS(atualizar.getOS());
            System.out.println("OS de código " + atualizar.getKey() + " atualizado com sucesso na CACHE");
            return true;
        }

        System.out.println("Node nao encontrado na CACHE.");
        return false;
    }
   
}