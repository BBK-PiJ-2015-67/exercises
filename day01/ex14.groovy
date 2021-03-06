// 13 up to 1000 primes
//
//

// start with 3 as we know 2 is prime and 1 is not
def primeOutput = [2];
int primes = 1;
int targetPrimes = 1000;
int num = 3;

while (primes < targetPrimes) {
    boolean isPrime;
    int i = num;

    while (i > 2) {
        i--;
        if (num % i == 0) {
            isPrime = false;
            break;
        } else {
            isPrime = true;
        }
    }

    if (isPrime) {
        primeOutput.push(num)
        primes++;
    }

    num++;
}

println primeOutput.join(", ");
println "total prime numbers: " + primes;
