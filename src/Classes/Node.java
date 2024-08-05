package Classes;

public class Node {

    private int codigo;
    private OS cont;
    private int alturaNo;
    Node esq;
    Node dir;

    public Node() {}

    public Node(int codigo, OS cont) {
        this.codigo = codigo;
        this.cont = cont;
    }

    public void getConteudo() {
        System.out.println("====================");
        System.out.println("Código: " + this.getCodigo() + " || Nome: " + this.cont.getNome());
        System.out.println("Descrição: " + this.cont.getDescricao());
        System.out.println("Altura: " + this.getAlturaNo() + " || Hora: " + this.cont.getHora());
    }
    
    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int cod) {
        this.codigo = cod;
    }

    public int getAlturaNo() {
        return this.alturaNo;
    }

    public void setAlturaNo(int altura) {
        this.alturaNo = altura;
    }

    public OS getOS() {
        return this.cont;
    }

    public void setOS(OS os) {
        this.cont = os;
    }

}
