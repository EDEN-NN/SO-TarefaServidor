package view;

import java.util.concurrent.Semaphore;

import controller.ServerController;

public class Main {

	public static void main(String[] args) {

		
		final int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		
		for (int idThread = 1; idThread < 21; idThread++) {

			Thread t = new ServerController(idThread, semaforo);
			t.start();
		}
	}
}
