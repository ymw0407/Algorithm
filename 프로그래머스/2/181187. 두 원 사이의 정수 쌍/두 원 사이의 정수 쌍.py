import math

def solution(r1, r2):
    ans = 0
    # 0 ~ r2 - 1까지 진행 후 4배를 할 예정
    for x in range(r2):
        # r^2 = x^2 + y^2, 원의 방정식을 통해 구함.
        if x <= r1: # 만약 x가 r1보다 작거나 같을 땐, 작은 원과 큰 원이 같이 있을 때라는 것을 의미함.
            ans += math.floor(math.sqrt(r2**2 - x**2)) - math.ceil(math.sqrt(r1**2 - x**2)) + 1
        else:
            ans += math.floor(math.sqrt(r2**2 - x**2))
            
    return (ans - 1) * 4