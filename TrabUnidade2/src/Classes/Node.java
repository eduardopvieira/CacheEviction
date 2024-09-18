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

}
