import (
    "sort"
    "fmt"
)

func solution(array []int) int {
    sort.Ints(array)
    fmt.Println(len(array))
    return array[len(array)/2]
}