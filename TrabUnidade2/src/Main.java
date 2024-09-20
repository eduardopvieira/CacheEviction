import Classes.Cliente;
import Classes.Node;
import Classes.OS;
import Classes.Servidor;
import Classes.TabelaHash;
import Exception.MyException;

public class Main {
    public static void main(String[] args) throws MyException {

        Servidor baseDados = new Servidor(2);
        Cliente cli = new Cliente(baseDados);

        cli.comecarSimulacao();


    }
}