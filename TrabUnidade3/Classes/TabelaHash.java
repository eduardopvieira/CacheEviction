package Classes;

import java.util.Random;

public class TabelaHash {

    int M; // tamanho da tabela
    int n; // número de elementos na tabela
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

    // Função hash
    public int hash(int ch) {
        return ch % this.M;
    }

    // Inserir elemento na tabela hash
    public void inserir(Node no) {
        int h = this.hash(no.getKey());
        ListaAutoAjustavel lista = this.tabela[h];

        if (lista.buscar(no.getKey()) != null) {
            return;
        }
        n++;
        lista.inserir(no);
        System.out.println("NO DE CHAVE " + no.getKey() + " INSERIDOOOOOO UHU    (SOUT EM INSERIR NA HASH)");
    }


    public Node buscar(int v) {
        int h = this.hash(v);
        System.out.println("Hash calculada para " + v + ": " + h);
        ListaAutoAjustavel lista = this.tabela[h];  // Acessar a lista autoajustável correspondente
    
        Node no = lista.buscar(v);
        if (no != null) {
            System.out.println("Elemento encontrado na tabela hash: " + no.getKey());
            return no; // Retorna o node se encontrado
        }
    
        System.out.println("Elemento " + v + " não encontrado na tabela hash.");
        return null; // Se não encontrado
    }
    

    // public Node buscar(int v) {
    //     int h = this.hash(v);
    //     ListaAutoAjustavel lista = this.tabela[h];

    //     Node no = lista.buscar(v);
    //     if (no != null) {
    //         return no;
    //     }

    //     return null;
    // }

    // Remover elemento da tabela hash
    public Node remover(int v) {
        int h = this.hash(v);
        ListaAutoAjustavel lista = this.tabela[h];

        // Remover o valor da lista autoajustável
        Node no = lista.buscar(v);
        if (no != null) {
            lista.remover(v); // Remove da lista autoajustável
            n--;
            return no;
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
    
        ListaAutoAjustavel[] temp = tabela; // Armazena a tabela atual em temp
        if (aumentar) {
            M = proxPrimo(M * 2); // Aumenta para o próximo primo após dobrar o tamanho
        } else {
            M = primoAnterior(M / 2); // Diminui para o primo anterior após reduzir o tamanho pela metade
        }
    
        double fatorDeCargaMaximo = 7.5;
        
        int novoTamLista = (int) Math.ceil(fatorDeCargaMaximo); 
    
        tabela = new ListaAutoAjustavel[M];
    
        for (int i = 0; i < M; i++) {
            tabela[i] = new ListaAutoAjustavel(novoTamLista); // Nova lista com tamanho ajustado
        }
    
        for (ListaAutoAjustavel lista : temp) {
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    Node no = lista.getNode(i);
                    if (no != null) {
                        inserir(no); // Reinserir o nó na nova tabela
                    }
                }
            }
        }
    
        temp = null; // Libera a memória da tabela antiga
        n = contarNos(); // Atualiza o número total de elementos
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
