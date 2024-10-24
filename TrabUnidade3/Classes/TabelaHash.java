package Classes;

public class TabelaHash {

    int M;
    int n;
    ListaAutoAjustavel[] tabela;

    public TabelaHash(int tam) {
        this.M = tam;
        this.n = 0;
        double fatorDeCargaMaximo = 7.5;

        int tamLista = (int) Math.ceil(fatorDeCargaMaximo);
        
        this.tabela = new ListaAutoAjustavel[this.M];

        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new ListaAutoAjustavel(tamLista);
        }
    }

    public int hash(int ch) {
        return ch % this.M;
    }

    public void inserir(Node no) {
        int h = this.hash(no.getKey());
        ListaAutoAjustavel lista = this.tabela[h];

        if (lista.buscar(no.getKey()) != null) {
            return;
        }
        n++;
        lista.inserir(no);
    }


    public Node buscar(int v) {
        int h = this.hash(v);
        ListaAutoAjustavel lista = this.tabela[h];  // Acessar a lista autoajustável correspondente
    
        Node no = lista.buscar(v);
        if (no != null) { // se node for encontrado
            return no; 
        }
        // se node nao for encontrdao
        return null; 
    }

    public Node remover(int v) {
        int h = this.hash(v);
        ListaAutoAjustavel lista = this.tabela[h];

        Node no = lista.buscar(v);
        if (no != null) {
            lista.remover(v);
            n--;
            return no;
        }

        return null;
    }

    public void imprimirTabelaHash() {
        for (int i = 0; i < this.M; i++) {
            System.out.print("[ " + i + " ] --> ");
            tabela[i].imprimir();
        }
    }

    public double fatorDeCarga() {
        System.out.println("Valor de n: " + n);
        System.out.println("Valor de M: " + M);
        double fator = (double) n / M;
        System.out.println("Fator de carga atual: " + fator);
        return fator;
    }

    public int contarNos() {
        int count = 0;
        for (int i = 0; i < this.M; i++) {
            count += tabela[i].size();
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
    
        double fatorDeCargaMaximo = 7.5;
        
        int novoTamLista = (int) Math.ceil(fatorDeCargaMaximo); 
    
        tabela = new ListaAutoAjustavel[M];
    
        for (int i = 0; i < M; i++) {
            tabela[i] = new ListaAutoAjustavel(novoTamLista);
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
    
    

    int proxPrimo(int numero) {
        while (!ehPrimo(numero)) {
            numero++;
        }
        return numero;
    }

    public int primoAnterior(int numero) {
        while (numero > 2) {
            if (ehPrimo(numero)) {
                return numero;
            }
            numero--;
        }
        return 2;
    }

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
                for (int j = 0; j < lista.size(); j++) {
                    Node no = lista.getNode(j);
                    if (no != null) {
                        printarNode(no);
                    }
                }
            }
        }
	}
}
