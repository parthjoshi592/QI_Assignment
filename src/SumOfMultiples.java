public class SumOfMultiples {

	public static void main(String[] args) {
		int upperLimit = 1000;
		int sumOfMultiples = 0;
		for(int i=0; i<upperLimit; i++) {
			if(i%3 == 0 || i%5 == 0) {
				sumOfMultiples = sumOfMultiples + i;
			}
		}
		System.out.println("Sum of multiples of 3 or 5 below 1000 is " + sumOfMultiples);
	}
}