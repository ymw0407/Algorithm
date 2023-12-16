def solution(seq, k):
    end = 0
    ans = [0, len(seq)]
    s = 0
    
    for start in range(len(seq)): # start를 1씩 늘림
        while s < k and end < len(seq):
            s += seq[end]
            end += 1
        
        if s == k: # s와 k가 같을땐... 
            ans = [start, end - 1] if (ans[1] - ans[0]) > (end - start - 1) else ans

        s -= seq[start]
    
    return ans