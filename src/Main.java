import Classes.Cliente;
import Classes.OS;
import Classes.Servidor;
import Exception.MyException;

public class Main {
    public static void main(String[] args) throws MyException {



        Servidor baseDados = new Servidor();
        Cliente cli = new Cliente(baseDados);

        OS os1 = new OS(1, "Manutenção de Servidor", "Troca de hardware", "09:00");
        OS os2 = new OS(2, "Atualização de Software", "Atualização do sistema operacional", "10:30");
        OS os3 = new OS(3, "Verificação de Segurança", "Escaneamento de vulnerabilidades", "11:00");
        OS os4 = new OS(4, "Instalação de Rede", "Configuração de rede interna", "13:15");
        OS os5 = new OS(5, "Suporte ao Usuário", "Ajuda com problemas de login", "14:00");
        OS os6 = new OS(6, "Backup de Dados", "Backup semanal dos dados", "15:45");
        OS os7 = new OS(7, "Configuração de Impressora", "Instalação e configuração de impressoras", "16:30");
        OS os8 = new OS(8, "Monitoramento de Sistema", "Monitoramento de desempenho do servidor", "17:15");

        baseDados.inserir(os1.getCodigo(), os1);
        baseDados.inserir(os2.getCodigo(), os2);
        baseDados.inserir(os3.getCodigo(), os3);
        baseDados.inserir(os4.getCodigo(), os4);
        baseDados.inserir(os5.getCodigo(), os5);
        baseDados.inserir(os6.getCodigo(), os6);
        baseDados.inserir(os7.getCodigo(), os7);
        baseDados.inserir(os8.getCodigo(), os8);

        cli.comecarSimulacao();

    }
}