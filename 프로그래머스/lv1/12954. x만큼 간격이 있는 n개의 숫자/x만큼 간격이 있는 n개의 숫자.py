def solution(x, n):
    answer = []
    for nn in range(1, n+1):
        answer.append(x * nn)
    return answer