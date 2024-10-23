package Classes;

public class ListaAutoAjustavel {

    private Node[] lista;  // a lista propriamente dita
    private int tam;       // tamanho max
    private int tamAtual;  // qtd de nodes atual

    //? é uma LAA estática com transposição (anotando aqui pra nao dar um branco no video)

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

        if (existe(no.getKey())) {
            System.out.println("O ELEMENTO DE CHAVE " + no.getKey() + " JÁ EXISTE, NÃO SERÁ ADICIONADO NOVAMENTE.");
            imprimir();
            return;
        }

        if (tamAtual < tam) {
            lista[tamAtual] = no;
            tamAtual++;
        } else {
            System.out.println("Lista cheia, substituindo o último valor (chave " + lista[tam - 1].getKey() + ") por " + no.getKey());
            removerUltimo();
            lista[tamAtual] = no;
            tamAtual++; //tem q ter esse tamAtual++ pq o remover ultimo decrementa ele
        }
    }

    private void transpor(int index) {
        if (index > 0) {
            imprimir();
            
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
                lista[i] = lista[i + 1];
            }
            lista[tamAtual - 1] = null;
            tamAtual--;
            return retorno;
        } else {
            System.out.println("Chave não encontrada na cache.");
            return null;
        }
    }

    private void removerUltimo() {
        if (tamAtual > 0) {
            lista[tamAtual - 1] = null;
            tamAtual--;
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
        System.out.println("Chave " + ch + " não encontrada na CACHE.");
        return null;
    }

    public boolean existe(int ch) {
        for (int i = 0; i < tamAtual; i++) {            
            if (lista[i].getKey() == ch) {
                return true;
            }
        }
        return false;
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
            System.out.print("[" + lista[i].getKey() +"] ");
        }
        System.out.println();
    }
}