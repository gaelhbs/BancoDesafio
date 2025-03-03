public class ContaCorrente extends Conta {

    @Override
    public void imprimirExtrato() {
        System.out.println("Extrato da Conta Corrente");
        imprimirInfosComuns();
    }
}
