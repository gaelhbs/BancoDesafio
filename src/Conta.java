import java.util.Scanner;

public abstract class Conta implements IConta , Transferencia {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numeroConta = SEQUENCIAL++;
        this.cliente = cliente;
    }

    protected double saldo;
    protected int agencia;
    protected int numeroConta;
    protected Cliente cliente;

    public Conta() {

    }

    public int getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o método de transferência: ");
        System.out.println("1 - TED");
        System.out.println("2 - PIX");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        switch (opcao) {
            case 1:
                System.out.println("Insira o número da conta de destino: ");
                int numeroConta = scanner.nextInt();
                System.out.println("Insira a agência da conta de destino: ");
                int agencia = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                Conta contaTed = Banco.buscarConta(numeroConta);
                if (contaTed == null) {
                    System.out.println("Conta não encontrada para TED!");
                    return;
                }

                this.transferirTed(valor, contaTed);
                this.sacar(valor);
                contaTed.depositar(valor);
                break;

            case 2:
                System.out.println("Insira o CPF do destinatário: ");
                String chavePix = scanner.nextLine();

                Conta contaPix = Banco.buscarContaPorCpf(chavePix);
                if (contaPix == null) {
                    System.out.println("Chave PIX não encontrada!");
                    return;
                }

                this.pix(valor, chavePix);
                this.sacar(valor);
                contaPix.depositar(valor);
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }


    protected void imprimirInfosComuns() {
        System.out.println("Titular: " + Cliente.getNome());
        System.out.println("Saldo: " + saldo);
        System.out.println("Agencia: " + agencia);
        System.out.println("Numero da Conta: " + numeroConta);
    }
}
