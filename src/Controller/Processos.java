package Controller;
import java.util.concurrent.Semaphore;

public class Processos extends Thread{
	Semaphore semaforo;
	private int threadId;
	Semaphore semaforo2;
	
	public Processos(int threadId, Semaphore semaforo, Semaphore semaforo2){
		this.threadId = threadId;
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
	}
	
	public void run(){
		
		int n = (int) (Math.random()*2)+1;
		
		if (n == 1) {
			Deposito();
		}
		if (n == 2) {
			Saque();
		}
	}

	private void Deposito() {
		
		try {
			semaforo2.acquire();
			System.out.println("A pessoa #"+threadId+ " está inserindo o código da conta.");
			int cod = (int) (Math.random()*900000)+100000;
			sleep(1000);
			System.out.println("A pessoa #"+threadId+ " inseriu o código: "+ cod);
			int saldo = (int) (Math.random()*1401)+200;
			System.out.println("O saldo da pessoa #"+threadId+ " é de: "+ saldo);
			
			System.out.println("A pessoa #"+threadId+ " está fazendo o depósito.");
			int deposito = (int) (Math.random()*101)+100;
			sleep(1000);
			System.out.println("A pessoa #"+threadId+ " fez o depósito de: "+ deposito);
			int nsaldo = saldo + deposito;
			System.out.println("O novo saldo da pessoa #"+threadId+ " é de: "+ nsaldo);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo2.release();
		}
	}

	private void Saque() {
		
		try {
			semaforo.acquire();
			System.out.println("A pessoa #"+threadId+ " está inserindo o código da conta.");
			int cod = (int) (Math.random()*900000)+100000;
			sleep(1000);
			System.out.println("A pessoa #"+threadId+ " inseriu o código: "+ cod);
			int saldo = (int) (Math.random()*1401)+200;
			System.out.println("O saldo da pessoa #"+threadId+ " é de: "+ saldo);
			
			int saque = (int) (Math.random()*101)+100;
			System.out.println("A pessoa #"+threadId+ " está fazendo o saque.");
			sleep(1000);
			System.out.println("A pessoa #"+threadId+ " fez o saque de: "+ saque);
			int nsaldo = saldo- saque;
			System.out.println("O novo saldo da pessoa #"+threadId+ " é de: "+ nsaldo);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo2.release();
		}
		
	}
	
}