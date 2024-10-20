package Classes;

import java.util.Random;

public class TabelaHash {

    int M; // tamanho da tabela
    int n; // número de elementos na tabela
    ListaAutoAjustavel[] tabela;

    public TabelaHash(int tam) {
        this.M = tam;
        this.n = 0;
        this.tabela = new ListaAutoAjustavel[this.M];

        // inicializa cada posição da tabela com uma ListaAutoAjustavel
        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new ListaAutoAjustavel(tam); // Define o tamanho máximo da lista ajustável
        }
    }

    // Função hash
    public int hash(int ch) {
        return ch % this.M;
    }

    // Inserir elemento na tabela hash
    public void inserir(Node no) {
        int h = this.hash(no.getKey());
        ListaAutoAjustavel lista = this.tabela[h];

        // Verifica se o valor já está inserido (não aceita duplicatas)
        if (lista.buscar(no.getKey()) != null) {
            return; // Valor já existe, não insere novamente
        }

        // Se não encontrou, adiciona o novo valor na ListaAutoAjustavel
        n++;
        lista.inserir(no.getKey(), no.getOS());
    }

    // Buscar elemento na tabela hash
    public Node buscar(int v) {
        int h = this.hash(v);
        ListaAutoAjustavel lista = this.tabela[h];

        // Busca o valor na lista autoajustável
        OS os = lista.buscar(v);
        if (os != null) {
            return new Node(v, os); // Retorna o node se encontrado
        }

        return null; // Se não encontrado
    }

    // Remover elemento da tabela hash
    public Node remover(int v) {
        int h = this.hash(v);
        ListaAutoAjustavel lista = this.tabela[h];

        // Remover o valor da lista autoajustável
        OS os = lista.buscar(v);
        if (os != null) {
            lista.remover(v); // Remove da lista autoajustável
            n--;
            return new Node(v, os); // Retorna o node removido
        }

        return null; // Se não encontrado
    }

    // Imprimir a tabela hash
    public void imprimirTabelaHash() {
        for (int i = 0; i < this.M; i++) {
            System.out.print("[ " + i + " ] --> ");
            tabela[i].imprimir();
        }
    }

    // Fator de carga
    public double fatorDeCarga() {
        System.out.println("Valor de n: " + n);
        System.out.println("Valor de M: " + M);
        double fator = (double) n / M;
        System.out.println("Fator de carga atual: " + fator);
        return fator;
    }

    // Contar elementos da tabela
    public int contarNos() {
        int count = 0;
        for (int i = 0; i < this.M; i++) {
            count += tabela[i].size(); // Usa o método size da ListaAutoAjustavel
        }
        return count;
    }

    // Redimensionar a tabela hash
    void resize(boolean aumentar) {

        System.out.println("TABELA ATUAL: ");
        imprimirTabelaHash();

        System.out.println("REDIMENSIONANDO!!!!!");

        ListaAutoAjustavel[] temp = tabela;
        if (aumentar) {
            M = proxPrimo(M * 2);
        } else {
            M = primoAnterior(M / 2);
        }
        tabela = new ListaAutoAjustavel[M];

        for (int i = 0; i < M; i++) {
            tabela[i] = new ListaAutoAjustavel(M);
        }

        for (ListaAutoAjustavel lista : temp) {
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    Node no = lista.getNode(i);
                    if (no != null) {
                        inserir(no);
                    }
                }
            }
        }

        temp = null;
        n = contarNos();
    }

    // Encontrar o próximo número primo
    int proxPrimo(int numero) {
        while (!ehPrimo(numero)) {
            numero++;
        }
        return numero;
    }

    // Encontrar o primo anterior
    public int primoAnterior(int numero) {
        while (numero > 2) {
            if (ehPrimo(numero)) {
                return numero;
            }
            numero--;
        }
        return 2;
    }

    // Verificar se um número é primo
    boolean ehPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Sortear um elemento aleatório da tabela
    public Node sortearElemento() {
        Random rand = new Random();
        Node noSorteado = null;

        while (noSorteado == null) {
            int indiceTabela = rand.nextInt(M);

            ListaAutoAjustavel lista = tabela[indiceTabela];
            if (lista.size() > 0) {
                int indiceLista = rand.nextInt(lista.size());
                noSorteado = lista.getNode(indiceLista);
            }
        }

        System.out.println("Elemento sorteado: ");
        printarNode(noSorteado);

        return noSorteado;
    }

    // Método auxiliar para imprimir um Node
    void printarNode(Node no) {
        System.out.println("=================");
        System.out.println("Código: " + no.getKey());
        System.out.println("Nome: " + no.getOS().getNome());
        System.out.println("Descrição: " + no.getOS().getDescricao());
        System.out.println("Hora: " + no.getOS().getHora());
    }

	public void listarElementos() {
        System.out.println("=== Listando todos os elementos da Tabela Hash ===");
        
        for (int i = 0; i < this.M; i++) {
            ListaAutoAjustavel lista = tabela[i];
            if (lista.size() > 0) {
                System.out.println("Posição [ " + i + " ]: ");
                for (int j = 0; j < lista.size(); j++) {
                    Node no = lista.getNode(j);
                    if (no != null) {
                        printarNode(no); // Imprime o conteúdo do Node
                    }
                }
            } else {
                System.out.println("Posição [ " + i + " ]: Vazia");
            }
        }
	}
}
