package Classes;

import java.util.LinkedList;
import java.util.Random;

public class TabelaHash {

	int M; // tamanho da tabela
	int n; //numero de elementos na tabela
	LinkedList<Node>[] tabela;

	public TabelaHash(int tam) {
		this.M = tam;
		this.n = 0;
		this.tabela = new LinkedList[this.M];

		// inicializa cada posição da tabela com uma lista encadeada vazia
		for (int i = 0; i < this.M; i++) {
			this.tabela[i] = new LinkedList<>();
		}
	}

	
	// vo ter q fzr a funçao hash pro esquema aqui
	public int hash(int ch) {
		return ch % this.M;
	}


	public void inserir(Node no) {
		int h = this.hash(no.getKey());
		LinkedList<Node> lista = this.tabela[h];

		// VERIFICA SE VALOR JA ESTÁ INSERIDO PQ A TABELA HASH NAO ACEITA DUPLICATAS
		for (Node node : lista) {
			if (node.getKey() == no.getKey()) {
				return; // valor ja existe
			}
		}

		// se viu que já nao ta, adiciona o novo valor na lista encadeada <3
		n++;
		lista.add(no);
	}



	public Node buscar(int v) {
		int h = this.hash(v); // calcula o indice pra ver a linkedlist correspondente
		LinkedList<Node> lista = this.tabela[h];

		// buscando o valor
		for (Node node : lista) {
			if (node.getKey() == v) {
				return node; // achou, brabo
			}
		}
		// se nao achou eh pq nao existe
		return null;
	}


	//! Função original, explicar no video

	public Node remover(int v) {
		int valor = hash(v);
		LinkedList<Node> lista = this.tabela[valor];

		for(Node node : lista) {
			if (node.getKey() == v) {
				lista.remove(node); //remove da linkedlist
				n--;
				return node; //retorna o removido
			}
		}

		//nao achou eh pq nao existe
		return null;
	}




	// printando a tabela hash
	public void imprimirTabelaHash() {
		for (int i = 0; i < this.M; i++) {
			System.out.print("[ " + i + " ] --> ");

			LinkedList<Node> lista = this.tabela[i];
			if (lista.isEmpty()) {
				System.out.println("null");
			} else {
				for (Node node : lista) {
					System.out.print(node.getKey() + " ");
				}
				System.out.println();
			}
		}
	}

	void listarElementos() {
		for (int i = 0; i < M; i++) {
			for ( Node no : tabela[i]) {
				printarNode(no);
			}
		}
	}


	public double fatorDeCarga() {
		System.out.println("Valor de n: " + n);
		System.out.println("Valor de M: " + M);
		double fator = (double) n / M;
		System.out.println("Fator de carga atual: " + fator);
		return fator;
	}

	void resize(boolean aumentar) {
		
		System.out.println("TABELA ATUAL: ");
		imprimirTabelaHash();

		System.out.println("REDIMENSIONANDO!!!!!");
		
		LinkedList<Node>[] temp = tabela;
		if (aumentar) {
			M = proxPrimo(M * 2);
		} else {
			M = primoAnterior(M / 2);
		}
		tabela = new LinkedList[M];
		
		for (int i = 0; i < M; i++) {
			tabela[i] = new LinkedList<>();
		}

		for (LinkedList<Node> lista: temp) {
			if (lista != null) {
				for (Node no: lista) {
					inserir(no);
				}
			}
		}

		temp = null;

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
		return 2; //se nao encontrar nenhum primo, retorna 2 q é o menor possivel
	}
	
	boolean ehPrimo(int numero) {
		if (numero <= 1) return false;
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) return false;
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


	public Node sortearElemento() {
        Random rand = new Random();
        Node noSorteado = null;

		//EQNUANTO O NO SORTEADO FOR NULO:
        while (noSorteado == null) {

            // PRIMEIRO PASSO: SORTEIA UM NUMERO DENTRO DO TAMANHO M DO MEU VETOR
            int indiceTabela = rand.nextInt(M);
            
			LinkedList<Node> lista = tabela[indiceTabela]; // PEGA A LISTA SORTEADA

            // VERIFICA SE ELA TA VAZIA
            if (!lista.isEmpty()) {
                //3: SE NAO ESTIVER....
                int indiceLista = rand.nextInt(lista.size()); // SORTEIA UM NUMERO DENTRO DA LISTA
                noSorteado = lista.get(indiceLista); // PEGA O NO SORTEADO
            }
			//SE ESTIVER VAZIA, NO SORTEADO É NULO E COMEÇA DNV
        }

        System.out.println("Elemento sorteado: ");
        printarNode(noSorteado);
        
        // Aqui você pode adicionar o elemento sorteado à cache
        // Exemplo:
        // cacheSv.addCache(noSorteado);

        return noSorteado;
    }

	int size() {
		return this.n;
	}
	

}
