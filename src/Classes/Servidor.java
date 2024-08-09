package Classes;

import Exception.MyException;

public class Servidor {

    Node raiz = null;
    int qtdNos;
    int alturaArv;

    public Servidor() {
    }

    public void inserir(Node no) throws MyException {
        raiz = inserir(raiz, no);
    }

    private Node inserir(Node arv, Node no) throws MyException {

        int cod = no.getCodigo();
        OS cont = no.getOS();

        if (arv == null) {
            return new Node(cod, cont);
        }

        if (cod < arv.getCodigo()) {
            arv.esq = inserir(arv.esq, no);
        }

        else if (cod > arv.getCodigo()) {
            arv.dir = inserir(arv.dir, no);
        } else {
            return arv;
        }

        arv.setAlturaNo(1 + maior(altura(arv.esq), altura(arv.dir)));

        // Calculando fator de balanceamento (fb)
        int fb = ObterFB(arv);
        int fbSubNoEsq = ObterFB(arv.esq);
        int fbSubNoDir = ObterFB(arv.dir);

        // Rotação direita simples//
        if (fb > 1 && fbSubNoEsq >= 0) {
            no.setRotacao("Rotação direita simples");
            return rotacaoDireitaSimples(arv);
        }

        // Rotação esquerda simples
        if (fb < -1 && fbSubNoDir <= 0) {
            no.setRotacao("Rotação esquerda simples");
            return rotacaoEsquerdaSimples(arv);
        }

        // Rotação dupla direita
        if (fb > 1 && fbSubNoEsq < 0) {
            no.setRotacao("Rotação dupla direita");
            rotacaoDireitaDupla(arv);
        }

        // Rotação dupla esquerda
        if (fb < -1 && fbSubNoDir > 0) {
            no.setRotacao("Rotação dupla esquerda");
            rotacaoEsquerdaDupla(arv);
        }

        return arv;
    }

    private int altura(Node no) {
        if (no == null) {
            return -1;
        }
        return no.getAlturaNo();
    }

    private int maior(int a, int b) {
        return (a > b) ? a : b;
    }

    private int ObterFB(Node no) {
        if (no == null) {
            return 0;
        }

        return altura(no.esq) - altura(no.dir);
    }

    private Node rotacaoDireitaSimples(Node x) {
        Node y = x.esq; // Nó a esquerda do X passado como parametro
        Node z = y.dir; // Nó a direita do Y anterior

        y.dir = x;
        x.esq = z;

        x.setAlturaNo(maior(altura(x.esq), altura(x.dir)) + 1);
        y.setAlturaNo(maior(altura(y.esq), altura(y.dir)) + 1);

        return y;
    }

    private Node rotacaoEsquerdaSimples(Node x) {
        Node y = x.dir; // Nó a direita do X passado como parametro
        Node z = y.esq; // Node a esquerda do nó anterior

        y.esq = x;
        x.dir = z;

        // Atualizar as alturas

        x.setAlturaNo(maior(altura(x.esq), altura(x.dir)) + 1);
        y.setAlturaNo(maior(altura(y.esq), altura(y.dir)) + 1);

        // y vai ser a nova raiz, entao retornar y.
        return y;
    }

    private Node rotacaoDireitaDupla(Node no) {
        no.esq = rotacaoEsquerdaSimples(no.esq);
        return rotacaoDireitaSimples(no);
    }

    private Node rotacaoEsquerdaDupla(Node no) {
        no.dir = rotacaoDireitaSimples(no.dir);
        return rotacaoEsquerdaSimples(no);
    }

    public void remover(int ch) {
        raiz = remover(raiz, ch);
    }

    private Node remover(Node no, int ch) {

        // percorrendo a arvore
        if (no == null) {
            return no;
        }

        if (ch < no.getCodigo()) {
            no.esq = remover(no.esq, ch);
        }

        else if (ch > no.getCodigo()) {
            no.dir = remover(no.dir, ch);
        }

        else {

            // CASO SEJA O NÓ ALVO E ELE NAO TEM FILHOS

            if (no.esq == null && no.dir == null) {
                no = null;
            }

            // NÓ SÓ TEM FILHO A DIREITA
            else if (no.esq == null) {
                Node temp = no;
                no = temp.dir;
                temp = null;
            }

            // Node SO TEM FILHO A ESQUERDA
            else if (no.dir == null) {
                Node temp = no;
                no = temp.esq;
                temp = null;
            }

            /*
             * Nó com 2 filhos: pegue o sucessor do percurso em ordem
             * Menor chave da subárvore direita do nó
             */
            else {
                Node temp = menorChave(no.dir);
                no.setCodigo(temp.getCodigo());
                no.setOS(temp.getOS()); // Atualize também o conteúdo
                no.dir = remover(no.dir, temp.getCodigo());
            }
        }

        if (no == null) {
            return no;
        }

        no.setAlturaNo(1 + maior(altura(no.esq), altura(no.dir)));

        // Calculando fator de balanceamento (fb)
        int fb = ObterFB(no);
        int fbSubOrdServEsq = ObterFB(no.esq);
        int fbSubOrdServDir = ObterFB(no.dir);

        // Rotação direita simples//
        if (fb > 1 && fbSubOrdServEsq >= 0) {
            no.setRotacao("Rotação direita simples");
            return rotacaoDireitaSimples(no);
        }

        // Rotação esquerda simples
        if (fb < -1 && fbSubOrdServDir <= 0) {
            no.setRotacao("Rotação esquerda simples");
            return rotacaoEsquerdaSimples(no);
        }

        // Rotação dupla direita
        if (fb > 1 && fbSubOrdServEsq < 0) {
            no.setRotacao("Rotação dupla direita");
            rotacaoDireitaDupla(no);
        }

        // Rotação dupla esquerda
        if (fb < -1 && fbSubOrdServDir > 0) {
            no.setRotacao("Rotação dupla esquerda");
            rotacaoEsquerdaDupla(no);
        }

        return no;

    }

    public int contarNos(Node no) {
        if (no == null) {
            return 0;
        }
        return 1 + contarNos(no.esq) + contarNos(no.dir);
    }

    public Node menorChave(Node no) {
        if (no == null) {
            return null;
        }

        Node temp = no;
        while (temp.esq != null) {
            temp = temp.esq;
        }
        return temp;
    }

    public void ordem() {
        this.ordem(getRaiz());
    }

    private void ordem(Node no) {
        if (no != null) {
            ordem(no.esq);
            no.getConteudo();
            ordem(no.dir);
        }
    }

    public Node getRaiz() {
        return this.raiz;
    }

    public void listarOrdem(Node arv) {

        if (arv != null) {
            listarOrdem(arv.esq);
            arv.getConteudo();
            listarOrdem(arv.dir);
        }
    }

    public Node buscarNode(int cod) {
        return buscarNode(raiz, cod);
    }

    private Node buscarNode(Node arv, int cod) {
        if (arv == null) {
            return null;
        }

        if (cod > arv.getCodigo()) {
            return buscarNode(arv.dir, cod);
        } else if (cod < arv.getCodigo()) {
            return buscarNode(arv.esq, cod);
        } else if (cod == arv.getCodigo()) {
            return arv;
        }
        return null;
    }
}
