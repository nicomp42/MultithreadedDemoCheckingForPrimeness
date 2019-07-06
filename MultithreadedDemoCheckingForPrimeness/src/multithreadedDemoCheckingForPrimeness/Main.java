/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * 
 * https://primes.utm.edu
 */
package multithreadedDemoCheckingForPrimeness;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		demo();
	}

	public static void demo() {
		System.out.println("This machine has " + getNumberOfCores() + " cores.");

		final long largest64BitPrime = 2764787846358431193L;
		long startTime = System.currentTimeMillis();
		Boolean thisPartIsPrime = true;
		for (long i = 3; i <= largest64BitPrime; i += 2) {
			if (largest64BitPrime % i == 0) {
				thisPartIsPrime = false;	// We found a divisor. The number is not prime. Give up. Sigh.
				break;
			}			
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Result = " + thisPartIsPrime + ", Single threaded: Total execution time: " + (endTime - startTime) + " milliseconds.");

		// Try it with however many cores are available.
		ArrayList<PrimessThread> primenessThreads = new ArrayList<PrimessThread>();
		int numberOfCores = getNumberOfCores() - 2;
		long increment = largest64BitPrime / numberOfCores;
		for (int i = 0; i < numberOfCores; i++) {
			long start, end;
			start = i * increment;
			end = start + increment;
			primenessThreads.add(new PrimessThread(largest64BitPrime, start, end));
		}
		// Launch all the threads to run in parallel
		startTime = System.currentTimeMillis();
		for (int i = 0; i < numberOfCores; i++) {
			primenessThreads.get(i).run();
		}

		// Wait for all the threads to finish
		for (int i = 0; i < numberOfCores; i++) {
			try {primenessThreads.get(i).join();} catch(InterruptedException ex) {}
		}
		endTime = System.currentTimeMillis();
		System.out.println(numberOfCores + " threaded: Total execution time: " + (endTime - startTime) + " milliseconds.");
	}

	public static int getNumberOfCores() {
		int processors = Runtime.getRuntime().availableProcessors();
		// System.out.println("CPU cores: " + processors);
		return processors;
	}
}
