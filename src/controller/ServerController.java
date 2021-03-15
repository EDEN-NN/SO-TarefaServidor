package controller;

import java.util.concurrent.Semaphore;

public class ServerController extends Thread {

	private int tempoCalc;
	private int idThread = 0;
	Semaphore semaforo;
	private static int countTransa;
	private static int countCalc;

	public ServerController(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		Calculo();
		try {
			semaforo.acquire();
			Transacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void Transacao() {
		if (idThread % 3 == 1) {
			for (int i = 0; i<2; i++) {
				try {
					System.out.println("A thread " + idThread + " está fazendo uma transação");
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (idThread % 3 == 2 || idThread % 3 == 0) {
			for (int i = 0; i<3; i++) {
				try {
					System.out.println("A thread " + idThread + " está fazendo uma transação");
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		countTransa++;
		System.out.println("A thread #" + idThread + " foi a " + countTransa + " a acabar as transações");

	}

	private void Calculo() {
		if (idThread % 3 == 1) {
			for (int i = 0; i < 2; i++) {
				try {
					System.out.println("A thread " + idThread + " está fazendo calculos");
					tempoCalc = (int) ((Math.random() * 800) + 200);
					sleep((long) tempoCalc);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} else if (idThread % 3 == 2) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("A thread " + idThread + " está fazendo calculos");
					tempoCalc = (int) ((Math.random() * 1000) + 500);
					sleep((long) tempoCalc);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} else {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("A thread " + idThread + " está fazendo calculos");
					tempoCalc = (int) ((Math.random() * 1000) + 1000);
					sleep((long) tempoCalc);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		countCalc++;
		System.out.println("A thread #" + idThread + " foi a " + countCalc + " a acabar as transações");

	}
}
