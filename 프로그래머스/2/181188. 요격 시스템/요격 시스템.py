def solution(targets):
    targets.sort(key = lambda x: [x[0], x[1]])
    # print(targets)
    arr = [0, 0]
    ans = 0
    for target in targets:
        s, e = target[0], target[1]
        
        if arr[1] <= s:
            ans += 1
            # print(target)
            arr = [s, e]
        else:
            arr = [max(arr[0], s), min(arr[1], e)]        
    
    return ans