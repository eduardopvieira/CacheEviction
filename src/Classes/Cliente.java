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
        int decisao = 0;
        while (decisao != 7) {

            
            System.out.println("================ INTERFACE ================");
            System.out.println("[1] - Cadastrar OS");
            System.out.println("[2] - Listar todas as OS");
            System.out.println("[3] - Alterar OS");
            System.out.println("[4] - Remover OS");
            System.out.println("[5] - Quantidade de registros");
            System.out.println("[6] - Buscar OS");
            System.out.println("[7] - Sair");
            System.out.println("Decisão: ");
            Scanner sc = new Scanner(System.in);
            decisao = sc.nextInt();

            switch(decisao) {
                case 1:
                    System.out.println("Digite o código do OS a ser cadastrado:");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    if (sv.buscarNode(codigo) != null) {
                        throw new MyException("Esse código já existe na base de dados.");
                    } else {
                        System.out.println("Digite o nome da operação: ");
                    String nome = sc.nextLine();

                    System.out.println("Digite a descrição: ");
                    String desc = sc.nextLine();

                    System.out.println("Digite o horário: ");
                    String hora = sc.nextLine();

                    OS os = new OS(nome, desc, hora);
                    Node no = new Node(codigo, os);

                    sv.inserir(no);
                    }
                                        
                    break;

                case 2:
                    System.out.println("Listando todos os OS: ");
                    sv.ordem();
                    break;

                case 3:
                    System.out.println("Digite o código do OS a ser alterado: ");
                    int cod = sc.nextInt();
                    Node alter = sv.buscarNode(cod);
                    if (alter != null) {

                        System.out.println("Qual parâmetro você quer alterar?");
                        System.out.println("1 - Código");
                        System.out.println("2 - Nome");
                        System.out.println("3 - Descrição");
                        System.out.println("4 - Horário");
                        System.out.println("5 - Todos");
                        int choice = sc.nextInt();
                        sc.nextLine(); 
                    
                        int newCod = cod;
                        String newNome = alter.getOS().getNome(); 
                        String newDesc = alter.getOS().getDescricao();
                        String newHora = alter.getOS().getHora(); 
                    
                        switch (choice) {
                            case 1:
                                System.out.println("Digite o novo código do OS:");
                                newCod = sc.nextInt();
                                sc.nextLine();
                    
                                if (newCod != cod && sv.buscarNode(newCod) != null) {
                                    throw new MyException("Já existe um OS com esse código.");
                                }
                                break;

                            case 2:
                                System.out.println("Digite o novo nome da operação:");
                                newNome = sc.nextLine();
                                break;

                            case 3:
                                System.out.println("Digite a nova descrição:");
                                newDesc = sc.nextLine();
                                break;
                            case 4:
                                System.out.println("Digite o novo horário:");
                                newHora = sc.nextLine();
                                break;
                            case 5:
                                // System.out.println("Digite o novo código do OS:");
                                // newCod = sc.nextInt();
                                // sc.nextLine(); // Consumir o newline
                    
                                // if (newCod != cod && sv.buscarNode(newCod) != null) {
                                //     throw new MyException("Já existe um OS com esse código.");
                                // }

                                // alter.setCodigo(newCod);
                    
                                System.out.println("Digite o novo nome da operação:");
                                newNome = sc.nextLine();
                                
                                System.out.println("Digite a nova descrição:");
                                newDesc = sc.nextLine();
                    
                                System.out.println("Digite o novo horário:");
                                newHora = sc.nextLine();

                                break;
                            default:
                                System.out.println("Opção inválida. Nenhuma alteração realizada.");
                                return;
                        }
                    

                        OS newOs = new OS(newNome, newDesc, newHora);
                        //Node newNode = new Node(newCod, newOs);
                        alter.setOS(newOs);
                        System.out.println("Alteração realizada com sucesso.");
                        break;
                    }
                
                case 4:
                    System.out.println("Digite o código do OS a ser removido: ");
                    int remov = sc.nextInt();
                    sv.remover(remov);
                    break;

                case 5:
                    System.out.println("Quantidade de registros: ");
                    break;
                
                case 6:
                    System.out.println("Digite o código do OS que deseja buscar:");
                    int buscar = sc.nextInt();
                    sc.nextLine();
                    Node achado = sv.buscarNode(buscar);
                    achado.getConteudo();
                
                case 7:
                    System.out.println("Encerrando programa.");
                    break;
                default:
                    System.out.println("Número nao reconhecido. Tente novamente.");
            }
        }
    }
}
