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
  
        if (buscarCache(novoElemento.getKey()) != null) {
            return;
        }
        if (cache.size() == 20) {
            //tirar um elemento aleatorio atualmente na cache
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(20);
            while (buscarCache(indiceAleatorio) != null) {
                indiceAleatorio = rand.nextInt(20);
            }

            removeCache(cache[i]);
        }
        cache.inserir(novoElemento);
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
}