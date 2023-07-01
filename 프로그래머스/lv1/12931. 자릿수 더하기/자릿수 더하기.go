import "fmt"

func solution(n int) int {
    n, sum := a(n)
    var sum_tmp int
    for n != 0 {
        n, sum_tmp = a(n)
        sum += sum_tmp
    }

    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    fmt.Printf(string(sum))

    return sum
}

func a(n int) (int, int) {
    return n/10 ,n % 10
}