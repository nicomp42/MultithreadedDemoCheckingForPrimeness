/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package multithreadedDemoCheckingForPrimeness;

public class PartialPrimeChecker {
	public static Boolean checkForPartialPrimeness(long num, long start, long end) {
		Boolean thisPartIsPrime = true;	// Assume the best
		for (long i = start; i <= end; i += 2) {
			if (num % start == 0) {
				thisPartIsPrime = false;	// We found a divisor. The number is not prime. Give up. Sigh.
				break;
			}
		}
		return thisPartIsPrime;	
	}
}
