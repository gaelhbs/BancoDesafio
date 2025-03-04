import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Banco extends Conta implements Transferencia {
    static Scanner scanner = new Scanner(System.in);
    private String nome;
    private static List<Conta> contas;
    private static Map<String, Conta> contasPorCpf;
    private static int numeroContaAtual = 1000;

    public Banco() {
        super();
        contas = new ArrayList<>();
        contasPorCpf = new HashMap<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContas() {
        contas.toString();
        return "";
    }

    public void setContas(List<Conta> contas) {
        Banco.contas = contas;
    }

    protected static void criarNovaConta() {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do Cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int tipoConta = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        Conta novaConta;
        if (tipoConta == 1) {
            novaConta = new ContaCorrente(cliente);
        } else {
            novaConta = new ContaPoupanca(cliente);
        }

        novaConta.numeroConta = numeroContaAtual++;
        contas.add(novaConta);
        contasPorCpf.put(cpf, novaConta); // Adiciona ao mapa de CPF

        System.out.println("Conta criada com sucesso! Número da conta: " + novaConta.numeroConta);
    }

    protected static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        } else {
            System.out.println("Listagem de contas");
            for (Conta conta : contas) {
                System.out.println(conta);
            }
        }
    }

    protected static void acessarConta() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            System.out.println("Menu da conta " + conta.numeroConta);
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Imprimir Extrato");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor do saque: ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 2:
                    System.out.print("Digite o valor do depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;
                case 3:
                    conta.imprimirExtrato();
                    break;
            }
            System.out.println();
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    protected static Conta buscarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.numeroConta == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    protected static Conta buscarContaPorCpf(String cpf) {
        for (Conta conta : contas) {
            if (conta.cliente.getCpf().equals(cpf)) {
                System.out.println(conta);
            }
        }

        return null;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
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

    @Override
    public void pix(double valor, String chavePix) {

    }

    @Override
    public void transferirTed(double valor, IConta contaDestino) {

    }

    @Override
    public void imprimirExtrato() {

    }
}