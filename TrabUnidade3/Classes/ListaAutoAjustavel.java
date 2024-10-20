package Classes;

public class ListaAutoAjustavel {

    private Node[] lista;  // Array de Nodes
    private int tam;       // Tamanho máximo da lista
    private int tamAtual;  // Número de elementos na lista atualmente

    public ListaAutoAjustavel(int t) {
        tam = t;
        lista = new Node[tam];  // Inicializa o array com o tamanho máximo
        tamAtual = 0;           // Lista começa vazia
    }

    // Método para inserir um novo elemento na lista
    public void inserir(int ch, OS valor) {
        if (tamAtual < tam) {
            if (existe(ch)) {
                System.out.println("Chave já existe. Elemento não será inserido novamente.");
            } else {
                lista[tamAtual] = new Node(ch, valor);
                tamAtual++;
            }
        } else {
            System.out.println("Lista cheia.");
        }
    }

    // Método para verificar se a chave já existe na lista
    private boolean existe(int ch) {
        for (int i = 0; i < tamAtual; i++) {
            if (lista[i].getKey() == ch) {
                transpor(i);  // Transpor o elemento para uma posição anterior
                return true;
            }
        }
        return false;
    }

    // Método de transposição (ajusta a posição dos elementos para facilitar acessos futuros)
    private void transpor(int index) {
        if (index > 0) {
            Node temp = lista[index];
            lista[index] = lista[index - 1];
            lista[index - 1] = temp;
        }
    }

    // Método para remover um elemento da lista
    public void remover(int ch) {
        int index = -1;
        
        // Procurar o índice do elemento com a chave fornecida
        for (int i = 0; i < tamAtual; i++) {
            if (lista[i].getKey() == ch) {
                index = i;
                break;
            }
        }

        // Se o elemento for encontrado, remover e ajustar a lista
        if (index != -1) {
            for (int i = index; i < tamAtual - 1; i++) {
                lista[i] = lista[i + 1];  // Move os elementos subsequentes para preencher o espaço
            }
            lista[tamAtual - 1] = null;  // Opcional: Limpar o último elemento
            tamAtual--;
            System.out.println("Elemento de chave " + ch + " removido da lista auto ajustavel");
        } else {
            System.out.println("Chave não encontrada");
        }
    }

    // Método para buscar um elemento pelo seu valor de chave
    public OS buscar(int ch) {
        for (int i = 0; i < tamAtual; i++) {
            if (lista[i].getKey() == ch) {
                transpor(i);  // Transpor o elemento para uma posição anterior
                return lista[i].getOS();
            }
        }
        return null; // Retorna null se o elemento não for encontrado
    }

    // Método para retornar o tamanho atual da lista (quantos elementos estão preenchidos)
    public int size() {
        return tamAtual;
    }

    // Método para retornar um Node em uma posição específica da lista
    public Node getNode(int index) {
        if (index >= 0 && index < tamAtual) {
            return lista[index];
        }
        return null;  // Retorna null se o índice for inválido
    }

    // Método para exibir a lista
    public void imprimir() {
        System.out.println("--- Lista Atual ---");
        for (int i = 0; i < tamAtual; i++) {
            System.out.println(lista[i].getKey() + " ");
        }
        System.out.println();
    }
}
