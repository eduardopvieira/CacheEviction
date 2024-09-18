package Classes;

import java.util.LinkedList;

public class TabelaHash {

	int M; // tamanho da tabela
	LinkedList<Node>[] tabela;

	public TabelaHash(int tam) {
		this.M = tam;
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
}
