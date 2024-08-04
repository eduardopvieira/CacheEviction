package Classes;
import Exception.MyException;
import java.util.Scanner;

public class Cliente {

    Servidor sv = new Servidor();

    public Cliente() {}

    public Cliente(Servidor sv) {
        this.sv = sv;
    }

    public void comecarSimulacao() throws MyException {
        System.out.println("================ INTERFACE ================");
        System.out.println("1 - Cadastrar OS");
        System.out.println("2 - Listar todas as OS");
        System.out.println("3 - Alterar OS");
        System.out.println("4 - Remover OS");
        System.out.println("5 - Quantidade de registros");
        System.out.println("6 - Sair");
        System.out.println("Decisão: ");
        Scanner sc = new Scanner(System.in);
        int decisao = sc.nextInt();

        switch(decisao) {
            case 1:
                System.out.println("Digite o código do OS a ser cadastrado:");
                int codigo = sc.nextInt();
                sc.nextLine();

                System.out.println("Digite o nome da operação: ");
                String nome = sc.nextLine();

                System.out.println("Digite a descrição: ");
                String desc = sc.nextLine();

                System.out.println("Digite o horário: ");
                String hora = sc.nextLine();

                OS os = new OS(codigo, nome, desc, hora);

                sv.inserir(os.getCodigo(), os);
                break;

            case 2:
                System.out.println("Listando todos os OS: ");
                sv.ordem();
                break;

            case 3:
                System.out.println("Digite o código do OS a ser alterado: ");
                int cod = sc.nextInt();
                OS alter = sv.buscarOS(cod);
                System.out.println("Digite");
                break;

            case 4:
                System.out.println("Digite o código do OS a ser removido: ");
                int remov = sc.nextInt();
                sv.remover(remov);
                break;

            case 5:
                System.out.println("Quantidade de registros: ");
                break;
            case 6:
                break;
            default:
                System.out.println("Número nao reconhecido. Tente novamente.");
        }
    }

}
