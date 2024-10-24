package Classes;

public class Node {

    private int key;
    private OS cont;
    Node next;

    public Node() {
    }

    public Node(int key, OS valor) {
        this.key = key;
        this.cont = valor;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int x) {
        this.key = x;
    }

    public OS getOS() {
        return this.cont;
    }

    public void setOS(OS os) {
        this.cont = os;
    }

    public String gerarMensagem() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chave: ").append(this.key);
        
        if (this.cont != null) {
            sb.append(" ").append(this.cont.gerarMensagemOS());
        }

        return sb.toString();
    }

    public void printarNode() {
        System.out.println("=================");
        System.out.println("Chave: " + this.key);
        System.out.println("Nome: " + this.getOS().getNome());
        System.out.println("Descrição: " + this.getOS().getDescricao());
        System.out.println("Hora: " + this.getOS().getHora());

    }

}
