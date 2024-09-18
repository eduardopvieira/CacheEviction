package Classes;

import Exception.MyException;
import java.util.Scanner;

public class Cliente {

    TabelaHash sv = new TabelaHash();
    Logger logger = new Logger();
    Cache cacheSv = new Cache();

    public Cliente() {
    }

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

            switch (decisao) {

                case 1:

                    System.out.println("Digite o código do OS a ser cadastrado:");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    if (sv.buscarNode(codigo) != null) {
                        System.out.println("Esse código já existe na base de dados.");
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
                        cacheSv.addCache(no);
                        logger.log("Inserção", no.getRotacao(), sv.raiz.getAlturaNo(), no.getCodigo(),
                                cacheSv.cache);
                    }

                    System.out.println("Cache: ");
                    cacheSv.printarCache();
                    break;

                case 2:
                    System.out.println("Listando todos os OS: ");
                    sv.preOrdem();
                    break;

                case 3:
                    System.out.println("Digite o código do OS a ser alterado: ");
                    int cod = sc.nextInt();
                    Node alter = sv.buscarNode(cod);
                    if (alter != null) {

                        System.out.println("Qual parâmetro você quer alterar?");
                        System.out.println("1 - Nome");
                        System.out.println("2 - Descrição");
                        System.out.println("3 - Horário");
                        System.out.println("4 - Todos");
                        int choice = sc.nextInt();
                        sc.nextLine();

                        String newNome = alter.getOS().getNome();
                        String newDesc = alter.getOS().getDescricao();
                        String newHora = alter.getOS().getHora();

                        switch (choice) {
                            case 1:
                                System.out.println("Digite o novo nome da operação:");
                                newNome = sc.nextLine();
                                break;

                            case 2:
                                System.out.println("Digite a nova descrição:");
                                newDesc = sc.nextLine();
                                break;
                            case 3:
                                System.out.println("Digite o novo horário:");
                                newHora = sc.nextLine();
                                break;
                            case 4:

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
                        alter.setOS(newOs);
                        System.out.println("Alteração realizada com sucesso.");
                        break;
                    }
                    System.out.println("Nenhum OS encontrado com esse código.");
                    break;

                case 4:
                    System.out.println("Digite o código do OS a ser removido: ");
                    int remov = sc.nextInt();
                    sc.nextLine();
                    Node removido = sv.buscarNode(remov);

                    if (removido != null) {
                        // tirando da arvre
                        sv.remover(remov);

                        // removendo da cache usando o codigo do node
                        cacheSv.removeCache(remov);

                        // Log da operação
                        logger.log("Remoção", removido.getRotacao(), sv.raiz.getAlturaNo(), removido.getCodigo(),
                                cacheSv.cache);
                    } else {
                        System.out.println("Nenhum OS encontrado com esse código.");
                    }
                    System.out.println("Cache: ");
                    cacheSv.printarCache();
                    break;

                case 5:
                    System.out.println("Quantidade de registros: ");
                    System.out.println(sv.contarNos(sv.raiz));
                    break;

                case 6:
                    System.out.println("Digite o código do OS que deseja buscar:");
                    int buscar = sc.nextInt();
                    sc.nextLine();

                    Node result = cacheSv.buscarCache(buscar);
                    if (result != null) {
                        result.getConteudo();
                    } else {
                        result = sv.buscarNode(buscar);
                        if (result != null) {
                            cacheSv.addCache(result);
                            result.getConteudo();
                        } else {
                            System.out.println("Nenhum OS encontrado com esse código.");
                        }

                    }

                    System.out.println("Cache: ");
                    cacheSv.printarCache();
                    break;

                case 7:
                    System.out.println("Encerrando programa.");
                    break;
                default:
                    System.out.println("Número nao reconhecido. Tente novamente.");

            }
        }
    }
}
