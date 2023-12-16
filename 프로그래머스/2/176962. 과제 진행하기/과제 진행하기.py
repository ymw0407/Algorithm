def solution(plans):
    plans.sort(key=lambda x: x[1])
    
    for plan in plans:
        plan[2] = int(plan[2])
    
    works = []
    answer = []
    
    for i in range(len(plans)-1):
        works.insert(0, plans[i]) # 앞에 있는 일 일수록 먼저 처리해야되는 일임
        time = timeToString(plans[i+1][1]) - timeToString(plans[i][1]) # 내가 작업할 수 있는 시간
        
        while time != 0 and len(works) != 0: # 남은 시간이 없거나 일이 없을때...
            if works[0][2] > time: # 지금 하고 있는 일의 시간이 더 길다면...
                works[0][2] -= time
                break
            else: # 남은 시간이 내가 할 일보다 넉넉히 있을 때 -> 일을 완료
                time -= works[0][2]
                answer.append(works.pop(0)[0])
    
    answer.append(plans[-1][0])
    
    for work in works:
        answer.append(work[0])
    
    return answer


def timeToString(timeStr):
    time = int(timeStr[:2]) * 60 + int(timeStr[3:5])
    return time