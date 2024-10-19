package Classes;


public class Servidor {
    
    TabelaHash tabela;

    public Servidor(int tam) {
        tabela = new TabelaHash(tam);
    }
    

    public Node buscar(int x) {
        return tabela.buscar(x);
    }


    public void inserir(Node no) {
        tabela.inserir(no);	
		if (tabela.fatorDeCarga() >= 7.5) { // *"Sedgewick recomenda escolher m tal que n/m fique entre 5 e 10."
			tabela.resize(true);
		}
    }


    public Node remover (int x) {
        Node removido = tabela.remover(x);
        
        if (removido == null) {
            System.out.println("Nenhum OS com esse código foi encontrado.");
            return removido;
        }
        
        System.out.println("OS de código " + x + " removido com sucesso!");

        if (tabela.fatorDeCarga() <= 2.5) {
            System.out.println("Sua tabela está com poucos elementos, portanto, será redimensionada para ficar menor.");
            tabela.resize(false);
        }

        return removido;
    }

    public void listarElementos() {
        tabela.listarElementos();
    }

    public void printarServer() {
        tabela.imprimirTabelaHash();
    }


}
