package testMVC;

import java.util.Observable;
import java.util.Random;

public class ContadorPOJO extends Observable implements Runnable {

	private Random r = new Random();
	private int indice;
	public static int valor = -1;

	public ContadorPOJO() {
		valor++;
		this.indice = valor;
	}

	public int getIndice() {
		return indice;
	}

	public void IniciarThread() {
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		try {
			while (true) {
				int num = r.nextInt(7000) + 1000;
				Thread.sleep(num);
				Incrementar();
			}
		} catch (Exception e) {
		}
	}

	private int numero = 36;

	public int getIncremento() {
		return numero;
	}

	public void Incrementar() {
		int num = r.nextInt(100) + 36;
		numero += num;
		setChanged();
		notifyObservers();

	}

}
