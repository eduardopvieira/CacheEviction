package Classes;

import Exception.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cliente {

    Servidor sv = new Servidor(5);
    Logger logger = new Logger();
    Cache cacheSv = new Cache();

    public Cliente() {
    }

    public Cliente(Servidor sv) {
        this.sv = sv;
    }

    public void comecarSimulacao() throws MyException {
        inicializacao();

        int decisao = 0;
        while (decisao != 10) {

            System.out.println("================ INTERFACE ================");
            System.out.println("[1] - Cadastrar OS");
            System.out.println("[2] - Listar todas as OS");
            System.out.println("[3] - Alterar OS");
            System.out.println("[4] - Remover OS");
            System.out.println("[5] - Quantidade de registros");
            System.out.println("[6] - Buscar OS");
            System.out.println("[7] - Mostrar fator de carga");
            System.out.println("[8] - Printar cache");
            System.out.println("[9] - Sair");
            System.out.println("Decisão: ");
            Scanner sc = new Scanner(System.in);
            decisao = sc.nextInt();

            switch (decisao) {

                case 1:

                    System.out.println("Digite o código do OS a ser cadastrado:");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    if (buscarCacheSv(codigo) == null) {

                        //se nao achou na cache nem na base de dados, começa o cadastro
    
                        System.out.println("Digite o nome da operação: ");
                        String nome = sc.nextLine();
    
                        System.out.println("Digite a descrição: ");
                        String desc = sc.nextLine();
    
                        System.out.println("Digite o horário: ");
                        String hora = sc.nextLine();
    
                        OS os = new OS(nome, desc, hora);
                        Node no = new Node(codigo, os);
    
                        sv.inserir(no);
                        // logger.log("Inserção", no.getRotacao(), sv.raiz.getAlturaNo(), no.getCodigo(),
                        //         cacheSv.cache);
                        System.out.println("OS cadastrado com sucesso.");
                        System.out.println("Cache: ");
                        cacheSv.printarCache();
                    } else {
                        System.out.println("O código digitado já existe na base de dados.");
                    }
                    break;
                    
                    
                case 2:
                    System.out.println("Listando todos os OS: ");
                    //sv.listarElementos();
                    sv.tabela.imprimirTabelaHash();
                    break;


                case 3:
                    System.out.println("Digite o código do OS a ser alterado: ");
                    int cod = sc.nextInt();
                    Node alter = buscarCacheSv(cod);
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
                    Node removido = sv.remover(remov);

                    if (removido != null) {
                        // tirando da hash
                        sv.remover(remov);

                        // removendo da cache usando o codigo do node
                        Node temp = cacheSv.removeCache(remov);
                        if (temp != null) {
                            adicionarRandomCache();
                        }

                        // Log da operação
                        // logger.log("Remoção", removido.getRotacao(), sv.raiz.getAlturaNo(), removido.getCodigo(),
                        //         cacheSv.cache);
                    } else {
                        System.out.println("Nenhum OS encontrado com esse código.");
                    }
                    System.out.println("Cache: ");
                    cacheSv.printarCache();
                    break;

                case 5:
                    System.out.println("Quantidade de registros: ");
                    System.out.println(sv.tabela.n);
                    break;

                case 6:

                    System.out.println("Digite o código do OS que deseja buscar:");
                    int buscar = sc.nextInt();
                    sc.nextLine();

                    Node retorno = printarCacheSv(buscar);
                    if (retorno != null) {
                        cacheSv.addCache(retorno);
                    }

                    System.out.println("Cache: ");
                    cacheSv.printarCache();
                    break;

                case 7:
                    System.out.println("Printando fator de carga:");
                    sv.tabela.fatorDeCarga();
                    break;

                case 8:
                    cacheSv.printarCache();
                    break;
                
                case 9:
                    System.out.println("Encerrando programa.");
                    break;


                default:
                    System.out.println("Número nao reconhecido. Tente novamente.");

            }
        }
    }

    Node buscarCacheSv(int x) {
        Node retorno = cacheSv.buscarCache(x);
        
        if (retorno == null) { //nao achou na cache
            System.out.println("Nada na cache. Resposta do servidor:");
            return sv.buscar(x); //vai retornar a resposta do servidor
        }
        System.out.println("Encontrado na cache:");
        return retorno; //achou na cache
        
    }

    Node printarCacheSv(int x) {
        
        Node no = cacheSv.buscarCache(x);
        
        if (no == null) { //nao achou na cache
            
            no = sv.buscar(x);
            
            if (no == null) {
                System.out.println("Código não encontrado.");
            } else {
                System.out.println("Encontrado na base de dados:");
                sv.tabela.printarNode(no);    
            }

        } else {
            System.out.println("Encontrado na cache:");
            sv.tabela.printarNode(no);
        }
        
        return no;
    }

    private void adicionarRandomCache() {
        Node novo = sv.tabela.sortearElemento();
        while (cacheSv.buscarCache(novo.getKey()) != null) {
            novo = sv.tabela.sortearElemento();
        }
        cacheSv.addCache(novo);
        System.out.println("novo valor adicionado a cache com sucesso");
        cacheSv.printarCache();
    }

    private void inicializacao() {
         // Loop para criar e inserir 70 dados
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
            sv.inserir(node);

        }

        Node add;
        for (int x = 1; x <= 20; x++) {
            add = sv.buscar(x);
            cacheSv.addCache(add);
        }
    }

}
