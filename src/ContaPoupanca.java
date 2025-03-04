public class ContaPoupanca extends Conta{

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }


    @Override
    public void transferir(double valor, IConta contaDestino) {
    }

    @Override
    public void pix(double valor, String chavePix) {

    }


    @Override
    public void transferirTed(double valor, IConta contaDestino) {
        System.out.println("Transferência realizada com sucesso! O pagamento será efetuado em até 1 dia útil.");
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Extrato da Conta Poupança");
        imprimirInfosComuns();
    }
}
