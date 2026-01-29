package bancoAvancado.app;

import java.util.Scanner;

import bancoAvancado.service.BancoService;

public class Main {
	public static void main(String[] args) {
		
		BancoService service = new BancoService();
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n---- MENU BANCÁRIO----");
			System.out.println("1 - Transferir");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Consultar saldo");
			System.out.println("0 - Sair");
			System.out.print("Escolha: ");
			
			int op = sc.nextInt();
			
			if(op == 1) {
				System.out.print("Conta origem: ");
				int o = sc.nextInt();
				
				System.out.print("Contadestino: ");
				int d = sc.nextInt();
				
				System.out.print("Valor: ");
				double v = sc.nextDouble();
				
				try {
					service.transferir(o, d, v);
					System.out.println("Transferência concluída!");
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			} else if(op == 2) {
				System.out.print("Conta: ");
				int c = sc.nextInt();
				
				System.out.print("Valor: ");
				double v = sc.nextDouble();
				
				try {
					service.depositar(c,v);
					System.out.println("Depósito concluído!");
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
				
			}
			
			else if(op == 3) {
				System.out.println("Conta: ");
				int c= sc.nextInt();
				
				System.out.print("Valor: ");
				double v = sc.nextDouble();
				
				try {
					service.sacar(c,v);
					System.out.println("Saque realizado!");
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			} 
			
			else if(op == 4) {
				System.out.println("Conta: ");
				int c= sc.nextInt();
				
				try {
					double saldo = service.consultarSaldo(c);
					System.out.println("Saldo atual: R$ " + saldo);
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			}
			
			else if(op == 0) {
				break;
			}
			
			else {
				System.out.println("Operação inválida!");
			}
			
			sc.close();
			
		}
	}
	
	
}
