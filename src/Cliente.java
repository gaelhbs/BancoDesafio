public class Cliente {
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipoConta = tipoConta;
    }

    private static String nome;
    private String cpf;
    private String tipoConta;

    public Cliente() {

    }

    public String getTipoConta() {
        return tipoConta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome;
    }
}
