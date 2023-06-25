func solution(numbers []int) []int {
    var ans []int
    
    for _, number := range numbers {
        ans = append(ans, number * 2)
    }
    
    return ans
}