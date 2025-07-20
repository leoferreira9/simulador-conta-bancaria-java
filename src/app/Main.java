package app;

import model.Conta;
import model.SaldoInsuficienteException;
import model.ValorInvalidoException;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do titular da conta: ");
        String nomeTitular = sc.nextLine();
        Conta conta = new Conta(nomeTitular);

        System.out.println("\nConta criada com sucesso para " + nomeTitular + ". Bem-vindo(a)!");

        int opcao = 1;
        while(opcao != 0){

            exibirMenu();

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("\nErro: digite apenas números para escolher a opção!");
                sc.nextLine();
                continue;
            }

            switch (opcao){
                case 0:
                    System.out.println("\nEncerrando o sistema...");
                    break;
                case 1:
                    realizarDeposito(sc, conta);
                    break;
                case 2:
                    realizarSaque(sc, conta);
                    break;
                case 3:
                    System.out.printf("\nSaldo atual: R$%.2f\n", conta.getSaldo());
                    break;
                case 4:
                    if(conta.getTransacoes().isEmpty()){
                        System.out.println("\nNão há nenhuma movimentação na conta!");
                    } else {
                        System.out.printf("\n--- HISTÓRICO DA CONTA n° %d (%s) ---\n", conta.getNumero(), conta.getTitular());
                        int[] i = {1};
                        conta.getTransacoes().forEach(t -> System.out.printf("Transação %d: %s\n", i[0]++, t.formatar()));
                    }
                    break;
                case 5:
                    System.out.println(conta);
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente!");
            }
        }
        sc.close();
    }

    public static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Ver Saldo");
        System.out.println("4 - Ver Histórico");
        System.out.println("5 - Informações da Conta");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    public static void realizarDeposito(Scanner sc, Conta conta) {
        boolean depositoEfetuado = false;
        while(!depositoEfetuado){
            System.out.print("\nQual valor deseja depositar? (Digite 0 para cancelar): ");
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                if(valor == 0){
                    System.out.println("\nOperação de depósito cancelada.");
                    break;
                }
                conta.depositar(valor);
                System.out.printf("\nDepósito de R$%.2f realizado com sucesso!\n", valor);
                depositoEfetuado = true;
            }catch (InputMismatchException e){
                System.out.println("\nO valor de depósito deve ser um número.");
                sc.nextLine();
            } catch (ValorInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void realizarSaque(Scanner sc, Conta conta) {
        boolean saqueEfetuado = false;
        while(!saqueEfetuado){
            System.out.print("\nQual valor deseja sacar? (Digite 0 para cancelar): ");
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                if(valor == 0){
                    System.out.println("Operação de saque cancelada.");
                    break;
                }
                conta.sacar(valor);
                System.out.printf("\nSaque de R$%.2f realizado com sucesso!\n", valor);
                saqueEfetuado = true;
            }catch (InputMismatchException e){
                System.out.println("\nO valor de saque deve ser um número.");
                sc.nextLine();
            } catch (ValorInvalidoException e){
                System.out.println(e.getMessage());
            } catch (SaldoInsuficienteException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
