/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package multithreadedDemoCheckingForPrimeness;

import java.math.BigInteger;

public class PrimenessThreadBigInteger extends Thread {

	/**
	 * Check a number for primeness, but just part of it.
	 * Uses the BigInteger class so we can process big honking numbers
	 */

	private BigInteger num, start, end;
	private Boolean thisPartIsPrime;
	private int threadNumber;

	public PrimenessThreadBigInteger(int threadNumber, BigInteger num, BigInteger start, BigInteger end) {
		this.start = new BigInteger(start.toByteArray());
		this.end = new BigInteger(end.toByteArray());
		this.num = new BigInteger(num.toByteArray());
		thisPartIsPrime = false;
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		System.out.println("Thread Number " + threadNumber + ": Starting thread from " + start + " to " + end);
		thisPartIsPrime = PartialPrimeChecker.checkForPartialPrimeness(num,  start, end);
		System.out.println("Thread Number " + threadNumber + ": Result = " + thisPartIsPrime + ", number = " + num + ", thread counted from " + start + " to " + end);
	}
	/***
	 * Get the result of what the thread did
	 * @return True if the segment of the number that was checked is prime, false otherwise
	 */
	public Boolean isThisPartPrime() {
		return thisPartIsPrime;
	}
}
