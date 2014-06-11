package thread;

import java.util.concurrent.TimeUnit;

import lockObj.MyLock;

public class Calculator implements Runnable {

	
	private MyLock lock;
	private int value = 0;
	
	
	public Calculator(MyLock lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		while(true){
			System.err.println("Calculator started");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				//wenn es eine Interrupt exception gibt, den Thread wieder laufen lassen.
				Thread.currentThread().interrupt();			
			}
			synchronized (this.lock) {
				try {
					this.lock.setValue(this.value++);
					//dem anderen Thread bescheid sagen
					this.lock.notify();
					this.lock.wait();	
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Thread im Fehlerfall freigeben
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
