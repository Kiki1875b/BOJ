def primelist(num):
    isPrime = [True] * (num + 1)
    isPrime[0], isPrime[1] = False, False
    for i in range(int((num)**(1/2)) + 1):
        if isPrime[i] == True:
            for j in range(i*i, num + 1, i):
                isPrime[j] = False
    return isPrime
        
isPrime = primelist(10000)

trial = int(input())

while trial > 0:
    n = int(input())
    for i in range(n//2, 1, -1):
        if isPrime[i] and isPrime[n-i]:
            print(str(i) + " " + str(n-i))
            break
    trial -= 1