package multithreadedDemoCheckingForPrimeness;

public class PrimessThread extends Thread {
	/**
	 * Check a number for primeness, but just part of it.
	 *
	 */

	private long num, start, end;
	private Boolean thisPartIsPrime;
	private int threadNumber;

	public PrimessThread(int threadNumber, long num, long start, long end) {
		this.start = start;
		this.end = end;
		this.num = num;
		thisPartIsPrime = false;
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		System.out.println("Thread Number " + threadNumber + ": Starting thread from " + start + " to " + end);
		thisPartIsPrime = true;	// Assume the best
		for (long i = start; i <= end; i += 2) {
			if (num % start == 0) {
				thisPartIsPrime = false;	// We found a divisor. The number is not prime. Give up. Sigh.
				break;
			}
		}
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
