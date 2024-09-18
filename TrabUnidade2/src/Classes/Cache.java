package Classes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cache {
    
    private TabelaHash cache;
    private int capacidade;
    

    public Cache () {}

    public Cache (int capacidade) {
        this.capacidade = capacidade;
        cache = new TabelaHash(capacidade);
    }



    public Node buscarCache(int buscar) {
        return cache.buscar(buscar);
    }



    public void addCache(Node novoElemento) {
  
        if (buscarCache(novoElemento.getKey()) != null) {
            return;
        }
        cache.inserir(novoElemento);
    }




    public void removeCache(int codigo) {
        Node no = cache.remover(codigo);
        if (no != null) {
            //TODO -> função pra adicionar outro valor quando esse sair
        }
    }



    public void printarCache() {
        cache.imprimirTabelaHash();
    }
}