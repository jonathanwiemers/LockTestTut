package thread;

import lockObj.MyLock;

public class Printer implements Runnable {
	
	private MyLock lock;

	
	public Printer(MyLock lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.err.println("Printer started");
			synchronized (this.lock) {
				try {
					this.lock.wait();
					System.out.println(this.lock.getValue());
					
					//dem anderen Thread bescheid sagen
					this.lock.notify();
				} catch (InterruptedException e) {
					//wenn es eine Interrupt exception gibt, den Thread wieder laufen lassen.
					// ->Thread.State.RUNNABLE
					Thread.currentThread().interrupt();	
				}
			}
		}	
	}
}
