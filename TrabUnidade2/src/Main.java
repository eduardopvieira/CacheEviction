import Classes.Cliente;
import Classes.Node;
import Classes.OS;
import Classes.TabelaHash;
import Exception.MyException;

public class Main {
    public static void main(String[] args) throws MyException {

        TabelaHash baseDados = new TabelaHash(2);
        TabelaHash cache = new TabelaHash(20);
        //Cliente cli = new Cliente(baseDados);

        // Loop para criar e inserir 60 dados
        for (int i = 1; i <= 70; i++) {
            // Cria um nome de operação fictício
            String nome = "Operação " + i;

            // Cria uma descrição fictícia
            String descricao = "Descrição da operação " + i;

            // Cria um horário fictício (pode ser ajustado conforme necessário)
            String horario = String.format("%02d:%02d", (i % 24), (i % 60));

            // Cria uma instância de OS com os dados gerados
            OS os = new OS(nome, descricao, horario);

            // Cria uma instância de Node com um código incremental
            Node node = new Node(i, os);

            // Insere o nó na base de dados
            baseDados.inserir(node);

        }

        baseDados.imprimirTabelaHash();


    }
}