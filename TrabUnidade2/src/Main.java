import Classes.Cliente;
import Classes.Servidor;
import Exception.MyException;

public class Main {
    public static void main(String[] args) throws MyException {

        Servidor baseDados = new Servidor(2);
        Cliente cli = new Cliente(baseDados);

        cli.comecarSimulacao();


    }
}