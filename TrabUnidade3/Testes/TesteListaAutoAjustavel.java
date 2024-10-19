package Testes;

import Classes.ListaAutoAjustavel;
import Classes.OS;

public class TesteListaAutoAjustavel {
    
    public static void main(String[] args) {
        // Inicializando uma lista autoajustável com tamanho máximo de 5 elementos
        ListaAutoAjustavel lista = new ListaAutoAjustavel(5);
        
        // Criando alguns objetos OS (simulando valores associados aos Nodes)
        OS valor1 = new OS("oi","oi","oi");
        OS valor2 = new OS("oi","oi","oi");
        OS valor3 = new OS("oi","oi","oi");
        OS valor4 = new OS("oi","oi","oi");
        OS valor5 = new OS("oi","oi","oi");
        OS valor6 = new OS("oi","oi","oi");

        // Inserindo elementos na lista
        lista.inserir(10, valor1);
        lista.inserir(20, valor2);
        lista.inserir(30, valor3);
        lista.inserir(40, valor4);
        lista.inserir(50, valor5);

        // Tentativa de inserir mais elementos além da capacidade da lista
        lista.inserir(60, valor6);  // Deverá exibir mensagem de lista cheia

        // Exibir a lista após as inserções
        System.out.println("\nLista após inserções:");
        lista.imprimir();

        // Testando a transposição (acessando um elemento que já existe)
        lista.inserir(20, valor2);  // Chave já existe, será movida
        System.out.println("\nLista após transposição:");
        lista.imprimir();

        // Tentativa de inserir uma chave duplicada (não deve ser adicionada novamente)
        lista.inserir(30, valor3);  // Chave já existe, será movida

        // Exibir a lista após a tentativa de inserção duplicada
        System.out.println("\nLista após tentativa de inserir chave duplicada:");
        lista.imprimir();
    }
}
