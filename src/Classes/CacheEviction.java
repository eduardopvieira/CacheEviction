package Classes;

import java.util.LinkedList;

public class CacheEviction {
    LinkedList<Node> cache;
    int capacidade = 20;

    public CacheEviction () {
        this.cache = new LinkedList<>();
    }

    public void addCache(Node novoElemento) {
        if (cache.size() >= capacidade) {
            cache.removeFirst();
        }
        cache.addLast(novoElemento);
    }

    public Node buscarCache(Node busca) {
        //TODO
    }
}

