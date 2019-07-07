/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * https://primes.utm.edu/curios/index.php?start=11&stop=16
 * https://primes.utm.edu
 */
package multithreadedDemoCheckingForPrimeness;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// final long aPrimeNumber = 2764787846358431197L; // This takes forever on the single-threaded path
		// final long aPrimeNumber = 10000019L;
		//final long aPrimeNumber = 5915587277L;
	 final long aPrimeNumber = 100123456789L;
		demo(true, aPrimeNumber);
	}

	public static void demo(Boolean runSingleThreadedExample, long aPrimeNumber) {

		long startTime, endTime;
		long limit = aPrimeNumber / 2;		// There is a better way to optimize this upper bound...
		if (runSingleThreadedExample) {
			startTime = System.currentTimeMillis();
			Boolean isPrime = PartialPrimeChecker.checkForPartialPrimeness(aPrimeNumber, 3,  limit);
			endTime = System.currentTimeMillis();
			System.out.println("Result = " + isPrime + ", Single threaded: Total execution time: " + (endTime - startTime) + " milliseconds.");
		}
		// Try it with however many cores are available.
		System.out.println("This machine has " + getNumberOfCores() + " cores.");
		ArrayList<PrimessThread> primenessThreads = new ArrayList<PrimessThread>();
		int numberOfCores = getNumberOfCores() - 2;
		long increment = limit / numberOfCores;
		long start, end;
		start = 3;
		end = start + increment;
		for (int i = 0; i < numberOfCores; i++) {
			primenessThreads.add(new PrimessThread(i + 1, aPrimeNumber, start, end));
			start = end + 1;
			end += increment;
			if (end > limit) {
				end = limit;
			}
		}
		// Launch all the threads to run in parallel
		startTime = System.currentTimeMillis();
		for (int i = 0; i < numberOfCores; i++) {
			primenessThreads.get(i).start();
		}

		// Wait for all the threads to finish
		for (int i = 0; i < numberOfCores; i++) {
			try {
				primenessThreads.get(i).join();
			} catch (InterruptedException ex) {
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println(
				numberOfCores + " threaded: Total execution time: " + (endTime - startTime) + " milliseconds.");
	}

	public static int getNumberOfCores() {
		int processors = Runtime.getRuntime().availableProcessors();
		// System.out.println("CPU cores: " + processors);
		return processors;
	}
}
