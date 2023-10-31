def findPrimePairs(n):
        def primelist(num):
            isPrime = [True] * (num + 1)
            isPrime[0], isPrime[1] = False, False
            for i in range(int((num)**(1/2)) + 1):
                if isPrime[i] == True:
                    for j in range(i*i, num + 1, i):
                        isPrime[j] = False
            return isPrime
        
        isPrime = primelist(n)

        for i in range(2, n//2 + 1):
            if isPrime[i] and isPrime[n-i]:
                answer = [i, n-i]
                
        print(str(answer[0]) + " " + str(answer[1]))

trials = int(input())

while trials > 0:
    findPrimePairs(int(input()))
    trials -= 1