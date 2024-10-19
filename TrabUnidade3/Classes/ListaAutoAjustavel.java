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

    // Método de transposição
    private void transpor(int index) {
        if (index > 0) {
            Node temp = lista[index];
            lista[index] = lista[index - 1];
            lista[index - 1] = temp;
        }
    }

    // Método para exibir a lista
    void imprimir() {
        System.out.println("--- Lista Atual ---");
        for(int i = 0; i < tamAtual; i++) {
            System.out.println(lista[i].getKey() + " ");
        }
        System.out.println();
    }
}
