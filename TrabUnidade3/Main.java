import Classes.Cliente;
import Classes.Servidor;

public class Main {
    public static void main(String[] args) {

        Servidor baseDados = new Servidor(2);
        Cliente cli = new Cliente(baseDados);
        cli.comecarSimulacao();

    }
}