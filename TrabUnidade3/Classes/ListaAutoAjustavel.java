package Classes;

public class ListaAutoAjustavel {

    private Node[] lista;  // a lista propriamente dita
    private int tam;       // tamanho max
    private int tamAtual;  // qtd de nodes atual

    public ListaAutoAjustavel(int t) {
        tam = t;
        lista = new Node[tam];
        tamAtual = 0;
    }

    public void inserir(Node no) {
        if (no == null) {
            System.out.println("Node nao pode ser nulo.");
            return;
        }

        if (tamAtual < tam) {
            if (existe(no.getKey())) {
                System.out.println("O ELEMENTO DE CHAVE " + no.getKey() + " JA EXISTE LADRAO, NAO VAI SER BOTADO NA CACHE DNV");
                imprimir();
            } else {
                lista[tamAtual] = no;
                tamAtual++;
            }
        } else {
            System.out.println("Lista cheia.");
        }
    }

    private boolean existe(int ch) {
        for (int i = 0; i < tamAtual; i++) {
            if (lista[i].getKey() == ch) {
                transpor(i);
                return true;
            }
        }
        return false;
    }

    private void transpor(int index) {
        if (index > 0) {
            Node temp = lista[index];
            lista[index] = lista[index - 1];
            lista[index - 1] = temp;
        }
    }

    public Node remover(int ch) {
        int index = -1;
        
        for (int i = 0; i < tamAtual; i++) {
            if (lista[i].getKey() == ch) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            Node retorno = lista[index];
            for (int i = index; i < tamAtual - 1; i++) {
                lista[i] = lista[i + 1];  // move o resto dos elementos pra preencher espaço
            }
            lista[tamAtual - 1] = null;  // limpando o último elemento
            tamAtual--;
            System.out.println("Elemento de chave " + ch + " removido da lista auto ajustavel");
            return retorno;
        } else {
            System.out.println("Chave não encontrada");
            return null;
        }
    }

    public Node buscar(int ch) {
        for (int i = 0; i < tamAtual; i++) {            
            if (lista[i].getKey() == ch) {
                Node retorno = lista[i];
                transpor(i); 
                return retorno;
            }
        }
        System.out.println("Chave " + ch + " não encontrada na lista.");
        return null;
    }
    

    public int size() {
        return tamAtual;
    }

    public Node getNode(int index) {
        if (index >= 0 && index < tamAtual) {
            return lista[index];
        }
        return null; 
    }

    public void imprimir() {
        for (int i = 0; i < tamAtual; i++) {
            System.out.print(lista[i].getKey() + " ");
        }
        System.out.println();
    }
}
