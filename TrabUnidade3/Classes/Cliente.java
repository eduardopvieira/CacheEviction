package Classes;

import java.util.Scanner;

import Huffman.ArvoreHuffman;

public class Cliente {

    Servidor sv;
    Cache cacheSv = new Cache();

    public Cliente() {
    }

    public Cliente(Servidor sv) {
        this.sv = sv;
    }

    public void comecarSimulacao() {
        Logger logger = new Logger(this.sv, cacheSv);
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
            System.out.println("[7] - Printar cache");
            System.out.println("[8] - Printar base de dados");
            System.out.println("[9] - Buscar no Log");
            System.out.println("[10] - Sair");
            System.out.println("Decisão: ");
            Scanner sc = new Scanner(System.in);
            decisao = sc.nextInt();

            switch (decisao) {

                case 1:

                    System.out.println("Digite o código do OS a ser cadastrado:");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    if (buscarCacheSv(codigo) == null) {

                        // se nao achou na cache nem na base de dados, começa o cadastro

                        System.out.println("Digite o nome da operação: ");
                        String nome = sc.nextLine();

                        System.out.println("Digite a descrição: ");
                        String desc = sc.nextLine();

                        System.out.println("Digite o horário: ");
                        String hora = sc.nextLine();

                        OS os = new OS(nome, desc, hora);
                        Node no = new Node(codigo, os);

                        //! compressao:
                        DadosCompressao nodeComprimido = comprimirNode(no);

                        sv.inserir(nodeComprimido);

                        System.out.println("OS cadastrado com sucesso.");
                        logger.log(no, true, false, false, false);
                    } else {
                        System.out.println("O código digitado já existe na base de dados.");
                    }
                    break;

                case 2:
                    System.out.println("Listando todos os OS: ");
                    sv.tabela.listarElementos();
                    logger.log(null, false, false, false, false);
                    break;

                case 3:
                    System.out.println("Digite o código do OS a ser alterado: ");
                    int cod = sc.nextInt();

                    //! descompressão aqui
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

                        DadosCompressao comprimido = comprimirNode(alter);

                        //!mandando node comprimido para cache e servidor
                        cacheSv.atualizarCacheComprimido(comprimido);
                        sv.atualizarSvComprimido(comprimido);

                        System.out.println("Alteração realizada com sucesso.");
                        logger.log(alter, false, false, true, false);
                        break;
                    }
                    System.out.println("Nenhum OS encontrado com esse código.");
                    break;

                case 4:
                    System.out.println("Digite o código do OS a ser removido: ");
                    
                    Integer remov = sc.nextInt();
                    sc.nextLine();
                

                    cacheSv.removerCache(remov);
                    DadosCompressao removido = sv.removerComprimido(remov);
                    
                    if (removido != null) {
                        Node nodeRemovido = removido.descomprimir();
                        sv.remover(nodeRemovido.getKey());
                        System.out.println("Node de chave " + nodeRemovido.getKey() + " removido do servidor.");
                        logger.log(nodeRemovido, false, true, false, false);
                    } else {
                        System.out.println("Nenhum OS encontrado com esse código no servidor.");
                    }
                    
                    break;

                case 5:
                    System.out.println("Quantidade de registros: ");
                    System.out.println(sv.tabela.n);
                    break;

                case 6:

                    System.out.println("Digite o código do OS que deseja buscar:");
                    int buscar = sc.nextInt();
                    sc.nextLine();

                    //! descompressao nesse metodo:
                    Node retorno = buscarCacheSv(buscar);
                    
                    if (retorno != null) {
                        cacheSv.adicionar(retorno);
                        logger.log(retorno, false, false, false, true);
                    }

                    break;

                case 7:
                    System.out.println("Cache:");
                    cacheSv.printarCache();
                    break;

                case 8:
                    System.out.println("Base de dados:");
                    sv.printarServer();
                    break;

                case 9:
                    KMP kmp = new KMP();
                    String caminhoArquivo = "log.txt";  // Certifique-se de que o arquivo esteja no local correto
                    String conteudoLog = kmp.lerArquivo(caminhoArquivo);
                    
                    if (conteudoLog.isEmpty()) {
                        System.out.println("Arquivo de log está vazio ou não foi encontrado.");
                        break;
                    }
                
                    System.out.println("Qual operação a ser buscada?");
                    System.out.println("[1] - Cadastro");
                    System.out.println("[2] - Remoção");
                    int choice = sc.nextInt();
                    sc.nextLine();  // Limpar o buffer
                
                    switch(choice) {
                        case 1:
                            System.out.println("Digite a chave a ser buscada:");
                            if (sc.hasNextInt()) {
                                int chaveCadastrada = sc.nextInt();
                                String padraoCadastro = "CADASTRAR CHAVE " + chaveCadastrada;
                                kmp.buscar(padraoCadastro, conteudoLog);
                            } else {
                                System.out.println("Entrada inválida.");
                            }
                            break;
                        
                        case 2:
                            System.out.println("Digite a chave a ser buscada:");
                            if (sc.hasNextInt()) {
                                int chaveRemovida = sc.nextInt();
                                String padraoRemover = "DELETAR CHAVE " + chaveRemovida;
                                kmp.buscar(padraoRemover, conteudoLog);
                            } else {
                                System.out.println("Entrada inválida.");
                            }
                            break;
                        
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;                   
                
                case 10:
                    System.out.println("Encerrando programa.");
                    break;

                default:
                    System.out.println("Número nao reconhecido. Tente novamente.");

            }
        }
    }

    Node buscarCacheSv(int x) {
        
        //! recebe o node comprimido nesse metodo ---------v
        DadosCompressao nodeCacheComprimido = cacheSv.buscaComprimidaCache(x);
        
        if (nodeCacheComprimido == null) { // ? nao encontrou na cache

            System.out.println("Nada na cache. Resposta do servidor:");
            DadosCompressao nodeSvComprimido = sv.buscaComprimidaServer(x);

            if (nodeSvComprimido == null) { // ? nao encontrou nada no servidor ou cache

                System.out.println("Node nao encontrado no servidor.");
                return null;
            }

            //? encontrou no servidor

            Node retorno = nodeSvComprimido.descomprimir();
            System.out.println("ENCONTRADO NO SERVIDOR");
            System.out.println("NODE COMPRIMIDO:" + nodeSvComprimido.msgComprimida);
            System.out.println("NODE DESCOMPRIMIDO: " + retorno.gerarMensagem() );
            return retorno;

        }

        //? encontrou na cache

        Node retorno = nodeCacheComprimido.descomprimir();
        
        
        System.out.println("Encontrado na cache:");
        System.out.println("NODE COMPRIMIDO:" + nodeCacheComprimido.msgComprimida);
        System.out.println("NODE DESCOMPRIMIDO: " + retorno.gerarMensagem() );


        return retorno;

    }

    Node printarCacheSv(int x) {

        Node no = cacheSv.buscarCache(x);

        if (no == null) { // nao achou na cache

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
    

    private void inicializacao() {
        for (int i = 1; i <= 70; i++) {
            String nome = "Operação" + i;
            String descricao = "Desc" + i;
            String horario = String.format("%02d:%02d", (i % 24), (i % 60));
            OS os = new OS(nome, descricao, horario);
            Node node = new Node(i, os);
            sv.inserirInicializacao(node);
        }
        sv.printarServer();

        Node add;
        int cont = 0;
        int x = 1;
        while (cont < 30) {
            System.out.println("VALOR PROCURADO: " + x);
            add = sv.buscar(x);
            if (add != null) {
                System.out.println("ACHADO PRA ADICIONAR NA CACHE: " + add.gerarMensagem());
                cacheSv.adicionar(add);
                cont++;
                System.out.println("CONTADOR: " + cont);
                cacheSv.printarCache();
            } else {
                System.out.println("achou um nulo");
                System.out.println(cont);
            }
            x++;
        }
    }

    public DadosCompressao comprimirNode(Node no) {
        
        System.out.println("Comprimindo OS:");
        
        ArvoreHuffman arvh = new ArvoreHuffman();
        
        String msg = no.gerarMensagem();
        
        int[] arrayNums = new int[msg.length()];
        char[] arrayChar = new char[msg.length()];
        
        arvh.contarCaractereFrequencia(msg, arrayChar, arrayNums);
        
        arvh.construirArvore(arrayChar, arrayNums);
        
        String msgComprimida = arvh.comprimir(msg);
        
        DadosCompressao retorno = new DadosCompressao(arvh, msgComprimida);

        return retorno;
    }
}
