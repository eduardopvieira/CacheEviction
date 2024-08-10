package Classes;

import java.util.ArrayList;

public class Cache {
    ArrayList<Node> cache;
    int capacidade = 20;

    public Cache() {
        this.cache = new ArrayList<>();
    }

    public Node buscarCache(int buscar) {
        for (Node node : cache) {
            if (node.getCodigo() == buscar) {
                return node;
            }
        }
        return null;
    }

    public void addCache(Node novoElemento) {
        // verifica se o nó já ta na cache
        if (buscarCache(novoElemento.getCodigo()) != null) {
            // Se já estiver na cache, não faz nada
            return;
        }

        if (cache.size() >= capacidade) {
            cache.removeFirst(); // removendo o primeiro elemento se a capacidade for atingida
        }
        cache.addLast(novoElemento); // adiciona o novo elemento no fim
    }

    public void removeCache(int codigo) {
        for (int i = 0; i < cache.size(); i++) {
            Node node = cache.get(i);

            if (node.getCodigo() == codigo) {
                cache.remove(i);
                break;
            }
        }
    }

    public void printarCache() {
        System.out.println("Conteúdo da Cache:");
        for (Node node : cache) {
            System.out.println("Código: " + node.getCodigo() + " || Nome: " + node.getOS().getNome());
        }
    }
}