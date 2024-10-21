package Classes;

public class OS {
    
    private String nome;
    private String descricao;
    private String hora; //decidi deixar string por se tratar de uma simulação e não ter nenhuma operação envolvendo
                         //as horas especificamente

    public OS () {}

    public OS (String nome, String desc, String hora) {
        this.nome = nome;
        this.descricao = desc;
        this.hora = hora;
    }

    public OS (OS conteudo) {
        this.nome = conteudo.getNome();
        this.descricao = conteudo.getDescricao();
        this.hora = conteudo.getHora();
    }


    public String getNome() {return this.nome;}
    
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return this.descricao;}

    public void setDescricao(String desc) {this.descricao = desc;}

    public String getHora() {return this.hora;}

    public void setHora(String hora) {this.hora = hora;}

    public String gerarMensagemOS() {
        return "Nome: " + this.nome +
        " Descrição: " + this.descricao +
        " Hora: " + this.hora; 
    }

}
