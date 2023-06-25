package main

import (
	"fmt"
	"math"
	"sort"
	"bufio"
	"os"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
        defer writer.Flush()

	var testcase int
	fmt.Fscanln(reader, &testcase)

	if testcase == 0 {
		fmt.Println(0)
		return
	}

	var intArray []int

	for i := 0; i < testcase; i++ {
		var n int
		fmt.Fscanln(reader, &n)
		intArray = append(intArray, n)
	}

	sort.Ints(intArray)
	sum := 0

	minus := int(math.Round(float64(testcase) * 0.15))

	for i := 0; i < testcase-minus*2; i++ {
		sum += intArray[i+minus]
	}
	fmt.Println(int(math.Round(float64(float64(sum) / float64(testcase - minus * 2)))))
}
