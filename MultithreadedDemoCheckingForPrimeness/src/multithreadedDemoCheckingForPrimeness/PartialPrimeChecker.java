/**
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package multithreadedDemoCheckingForPrimeness;

import java.math.BigInteger;

public class PartialPrimeChecker {
	/***
	 * Check a range of divisors to see if a number is prime
	 * @param num Number being checked for primeness
	 * @param start beginning of the range to check. Must be odd
	 * @param end end of the range, inclusive.
	 * @return True if no divisor found, false otherwise
	 */
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
	/***
	 * Check a range of divisors to see if a number is prime.
	 * Uses the BigInteger class so we can process big honking numbers
	 * @param num Number being checked for primeness
	 * @param start beginning of the range to check. Must be odd
	 * @param end end of the range, inclusive.
	 * @return True if no divisor found, false otherwise
	 */
	public static Boolean checkForPartialPrimeness(BigInteger num, BigInteger start, BigInteger end) {
		BigInteger i = new BigInteger(start.toByteArray());
		BigInteger two = new BigInteger("2");
		Boolean thisPartIsPrime = true;	// Assume the best
		while (true) {
//		for (long i = start; i <= end; i += 2) {
			if (num.mod(start).compareTo(BigInteger.ZERO) == 0) {
				thisPartIsPrime = false;	// We found a divisor. The number is not prime. Give up. Sigh.
				break;
			}
			i = i.add(two);
			if (i.compareTo(end) > 0) {break;}
		}
		return thisPartIsPrime;	
	}
}
