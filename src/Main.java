import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        Banco banco = new Banco();
        int opcao;

        do {
            System.out.println("Menu");
            System.out.println("1 - Criar nova conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Acessar conta");
            System.out.println("4 - Realizar transferência");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Banco.criarNovaConta();
                    break;
                case 2:
                    Banco.listarContas();
                    break;
                case 3:
                    Banco.acessarConta();
                    break;
                case 4:
                    System.out.print("Digite o número da conta de origem: ");
                    int numeroContaOrigem = scanner.nextInt();
                    Conta contaOrigem = Banco.buscarConta(numeroContaOrigem);

                    if (contaOrigem instanceof Transferencia) {
                        System.out.print("Digite o valor a ser transferido: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine(); // Consumir quebra de linha

                        System.out.print("Digite o número da conta de destino: ");
                        int numeroContaDestino = scanner.nextInt();
                        Conta contaDestino = Banco.buscarConta(numeroContaDestino);

                        if (contaDestino != null) {
                            ((Transferencia) contaOrigem).transferir(valor, contaDestino);
                        } else {
                            System.out.println("Conta de destino não encontrada.");
                        }
                    } else {
                        System.out.println("Conta de origem não encontrada ou não suporta transferências.");
                    }
                    break;
                case 5:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }


}