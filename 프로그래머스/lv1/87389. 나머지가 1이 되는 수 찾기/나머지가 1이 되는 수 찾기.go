func solution(n int) int {
    var i int
    for i=1; n%i != 1; i++ {
        println(i)
    }
    
    return i
}