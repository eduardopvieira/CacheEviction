package Classes;

public class Servidor {

    OS raiz = null;

    public Servidor() {}


    public void inserir(int ch, OS cont) {
        raiz = inserir(raiz, ch, cont);
    }

    private OS inserir(OS ordServ, int ch, OS cont) {
        if (ordServ == null) {
            return new OS(ch, cont);
        }

        if (ch < ordServ.getCodigo()) {
            ordServ.esq = inserir(ordServ.esq, ch, cont);
        }

        else if (ch > ordServ.getCodigo()) {
            ordServ.dir = inserir(ordServ.dir, ch, cont);
        }
        else {
            return ordServ;
        }

        ordServ.alturaOS = 1 + maior(altura(ordServ.esq), altura(ordServ.dir));

        //Calculando fator de balanceamento (fb)
        int fb = ObterFB(ordServ);
        int fbSubordServEsq = ObterFB(ordServ.esq);
        int fbSubordServDir = ObterFB(ordServ.dir);

        //Rotação direita simples//
        if (fb > 1 && fbSubordServEsq >= 0) {
            return rotacaoDireitaSimples(ordServ);
        }

        //Rotação esquerda simples
        if (fb < -1 && fbSubordServDir <= 0) {
            return rotacaoEsquerdaSimples(ordServ);
        }

        //Rotação dupla direita
        if (fb > 1 && fbSubordServEsq < 0) {
            rotacaoDireitaDupla(ordServ);
        }

        //Rotação dupla esquerda
        if (fb < -1 && fbSubordServDir > 0) {
            rotacaoEsquerdaDupla(ordServ);
        }

        return ordServ;
    }


    private int altura(OS ordServ) {
        if (ordServ == null) {
            return -1;
        }
        return ordServ.getAlturaOS();
    }

    private int maior(int a, int b) {
        return (a < b) ? a : b;
    }

    private int ObterFB(OS ordServ) {
        if (ordServ == null) {
            return 0;
        }

        return altura(ordServ.esq) - altura(ordServ.dir);
    }

    private OS rotacaoDireitaSimples(OS x) {
        OS y = x.esq;  //Nó a esquerda do X passado como parametro
        OS z = y.dir;  //Nó a direita do Y anterior

        y.dir = x;
        x.esq = z;

        x.alturaOS = maior(altura(x.esq), altura(x.dir)) + 1;
        y.alturaOS = maior(altura(y.esq), altura(y.dir)) + 1;

        return y;
    }

    private OS rotacaoEsquerdaSimples(OS x) {
        OS y = x.dir; //Nó a direita do X passado como parametro
        OS z = y.esq; //OS a esquerda do nó anterior

        y.esq = x;
        x.dir = z;

        //Atualizar as alturas

        x.alturaOS = maior(altura(x.esq), altura(x.dir)) + 1;
        y.alturaOS = maior(altura(y.esq), altura(y.dir)) + 1;

        //y vai ser a nova raiz, entao retornar y.
        return y;
    }

    private OS rotacaoDireitaDupla(OS ordServ) {
        ordServ.esq = rotacaoEsquerdaSimples(ordServ.esq);
        return rotacaoDireitaSimples(ordServ);
    }

    private OS rotacaoEsquerdaDupla(OS ordServ) {
        ordServ.dir = rotacaoDireitaSimples(ordServ.dir);
        return rotacaoEsquerdaSimples(ordServ);
    }

    public void remover (int ch, String v) {
        raiz = remover(raiz, ch, v);
    }
    private OS remover(OS ordServ, int ch, String v) {


        //Percorrendo a ordServore
        if (ordServ == null) {
            return ordServ;
        }

        if (ch < ordServ.getCodigo()) {
            ordServ.esq = remover(ordServ.esq, ch, v);
        }

        else if (ch > ordServ.getCodigo()) {
            ordServ.dir = remover(ordServ.dir, ch, v);
        }

        else {

            //CASO NÓ NAO TENHA FILHOS

            if (ordServ.esq == null && ordServ.dir == null) {
                ordServ = null;
            }

            //NÓ SÓ TEM FILHO A DIREITA
            else if (ordServ.esq == null) {
                OS temp = ordServ;
                ordServ = temp.dir;
                temp = null;
            }

            //OS SO TEM FILHO A ESQUERDA
            else if (ordServ.dir == null) {
                OS temp = ordServ;
                ordServ = temp.esq;
                temp = null;
            }

            /*Nó com 2 filhos: pegue o sucessor do percurso em ordem
              Menor chave da subárvore direita do nó */
            else {
                OS temp = menorChave(ordServ.dir);

                ordServ.codigo = temp.codigo;
                temp.codigo = ch;

                ordServ.dir = remover(ordServ.dir, ch, v);
            }
        }
        if(ordServ == null) {
            return ordServ;
        }

        ordServ.alturaOS = 1 + maior(altura(ordServ.esq), altura(ordServ.dir));

        //Calculando fator de balanceamento (fb)
        int fb = ObterFB(ordServ);
        int fbSubOrdServEsq = ObterFB(ordServ.esq);
        int fbSubOrdServDir = ObterFB(ordServ.dir);

        //Rotação direita simples//
        if (fb > 1 && fbSubOrdServEsq >= 0) {
            return rotacaoDireitaSimples(ordServ);
        }

        //Rotação esquerda simples
        if (fb < -1 && fbSubOrdServDir <= 0) {
            return rotacaoEsquerdaSimples(ordServ);
        }

        //Rotação dupla direita
        if (fb > 1 && fbSubOrdServEsq < 0) {
            rotacaoDireitaDupla(ordServ);
        }

        //Rotação dupla esquerda
        if (fb < -1 && fbSubOrdServDir > 0) {
            rotacaoEsquerdaDupla(ordServ);
        }

        return ordServ;

    }

    public OS menorChave(OS ordServ) {
        if (ordServ == null) {
            return null;
        }

        OS temp = ordServ;
        while (temp.esq != null) {
            temp = temp.esq;
        }
        return temp;
    }

    public void ordem() {
        this.ordem(getRaiz());
    }

    private void ordem(OS ordServ) {
        if(ordServ != null) {
            ordem(ordServ.esq);
            System.out.print(ordServ.getCodigo() + " ");
            ordem(ordServ.dir);
        }
    }

    public OS getRaiz() {
        return this.raiz;
    }

    public void listarOrdem(OS arv) {

        if (arv != null) {
            listarOrdem(arv.esq);
            arv.getConteudo();
            listarOrdem(arv.dir);
        }
    }

}
