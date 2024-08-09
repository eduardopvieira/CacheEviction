package Classes;

import java.util.ArrayList;

public class Cache {
    ArrayList<Node> cache;
    int capacidade = 3;

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
        // Verifica se o nó já está na cache
        if (buscarCache(novoElemento.getCodigo()) != null) {
            // Se já estiver na cache, não faz nada
            return;
        }

        if (cache.size() >= capacidade) {
            cache.removeFirst(); // Remove o primeiro elemento se a capacidade for atingida
        }
        cache.addLast(novoElemento); // Adiciona o novo elemento
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