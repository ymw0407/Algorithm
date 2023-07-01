import math

def solution(n):
    answer = 0
    
    if n == 0:
        return 0
    
    test = []
    for i in range(1, n+1):
        if n % i == 0:
            test.append(i)
            answer += i
    
    print(test)
    return answer