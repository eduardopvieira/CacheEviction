import Classes.Cliente;
import Classes.Node;
import Classes.OS;
import Classes.Servidor;
import Exception.MyException;

public class Main {
    public static void main(String[] args) throws MyException {

        Servidor baseDados = new Servidor();
        Cliente cli = new Cliente(baseDados);

        OS os1 = new OS("Manutenção de Servidor", "Troca de hardware", "09:00");
        OS os2 = new OS("Atualização de Software", "Atualização do sistema operacional", "10:30");
        OS os3 = new OS("Verificação de Segurança", "Escaneamento de vulnerabilidades", "11:00");
        OS os4 = new OS("Instalação de Rede", "Configuração de rede interna", "13:15");
        OS os5 = new OS("Suporte ao Usuário", "Ajuda com problemas de login", "14:00");
        OS os6 = new OS("Backup de Dados", "Backup semanal dos dados", "15:45");
        OS os7 = new OS("Configuração de Impressora", "Instalação e configuração de impressoras", "16:30");
        OS os8 = new OS("Monitoramento de Sistema", "Monitoramento de desempenho do servidor", "17:15");

        Node no1 = new Node(1, os1);
        Node no2 = new Node(2, os2);
        Node no3 = new Node(3, os3);
        Node no4 = new Node(4, os4);
        Node no5 = new Node(5, os5);
        Node no6 = new Node(6, os6);
        Node no7 = new Node(7, os7);
        Node no8 = new Node(8, os8);

        baseDados.inserir(no1);
        baseDados.inserir(no2);
        baseDados.inserir(no3);
        baseDados.inserir(no4);
        baseDados.inserir(no5);
        baseDados.inserir(no6);
        baseDados.inserir(no7);
        baseDados.inserir(no8);

        cli.comecarSimulacao();

    }
}