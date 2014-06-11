package domain;

import lockObj.MyLock;
import thread.Calculator;
import thread.Printer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLock lock = new MyLock();
		Thread t1 = new Thread(new Calculator(lock));
		Thread t2 = new Thread(new Printer(lock));
		
		t1.start();
		t2.start();
		
	}

}
