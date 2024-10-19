package Classes;

import java.util.Random;

public class Cache {
    
    private TabelaHash cache;

    public Cache () {
        cache = new TabelaHash(20);
    }



    public Node buscarCache(int buscar) {
        return cache.buscar(buscar);
    }



    public void addCache(Node novoElemento) {

        // Verifica se o elemento já está na cache
        if (buscarCache(novoElemento.getKey()) != null) {
            return;
        }
    
        // Aplica a função hash para encontrar a posição correspondente
        int posicaoHash = novoElemento.getKey() % 20;
    
        // Se a cache já estiver cheia (tamanho 20)
        if (cache.size() == 20) {
            // Remove um elemento aleatório da cache
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(20); // Gera um índice aleatório entre 0 e 19
            while (cache.buscar(indiceAleatorio) == null) {
                indiceAleatorio = rand.nextInt(20);
            }
    
            Node elementoRemovido = removeCache(indiceAleatorio);
            System.out.println("Elemento removido da posição " + indiceAleatorio + ": " + elementoRemovido.getKey());
        }
    
        // Insere o novo elemento na posição da função hash
        cache.inserir(novoElemento); // Insere o novo elemento na posição calculada
        System.out.println("Novo elemento adicionado na posição da cache " + posicaoHash + ": " + novoElemento.getKey());
    }




    public Node removeCache(int codigo) {
        Node no = cache.remover(codigo);
        if (no != null) {
            System.out.println("Elemento removido do cache: " + no.getKey());
        }
        return no;
    }



    public void printarCache() {
        cache.imprimirTabelaHash();
    }


    public TabelaHash getCache() {
        return this.cache;
    }
}