package Classes;

public class OS {
    int codigo;
    private String nome;
    private String descricao;
    private String hora; //decidi deixar string por se tratar de uma simulação e não ter nenhuma operação envolvendo
                         //as horas especificamente
    OS esq;
    OS dir;
    int alturaOS;

    public OS () {}

    public OS (int codigo, String nome, String desc, String hora) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = desc;
        this.hora = hora;
    }

    public OS (int codigo, OS conteudo) {
        this.codigo = codigo;
        this.nome = conteudo.getNome();
        this.descricao = conteudo.getDescricao();
        this.hora = conteudo.getHora();
    }

    public int getCodigo() {
        return this.codigo;
    }
    public void setCodigo(int cod) {this.codigo = cod;}

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return this.descricao;}
    public void setDescricao(String desc) {this.descricao = desc;}

    public String getHora() {return this.hora;}
    public void setHora(String hora) {this.hora = hora;}

    public int getAlturaOS() {return this.alturaOS;}

    public void getConteudo() {
        System.out.println("====================");
        System.out.println("Código: " + this.getCodigo() + " || Nome: " + this.getNome());
        System.out.println("Descrição: " + this.getDescricao());
        System.out.println("Altura: " + this.getAlturaOS() + " || Hora: " + this.getHora());
    }
}
