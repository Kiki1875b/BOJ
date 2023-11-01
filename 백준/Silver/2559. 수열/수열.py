length, n = map(int, input().split())
nums = list(map(int, input().split()))


def max_sum(length1, n1):
    temp = 0
    sums = []
    for i in range(length1):
        if i < n1-1:
            temp += nums[i]
        else:
            temp += nums[i]
            sums.append(temp)
            temp-=nums[i-n1+1]

    return max(sums)
    
print(max_sum(length, n))