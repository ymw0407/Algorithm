func solution(arr []int) float64 {
    sum := 0
    for _, ar := range arr {
        sum += ar
    }
    return float64(sum) / float64(len(arr))
}