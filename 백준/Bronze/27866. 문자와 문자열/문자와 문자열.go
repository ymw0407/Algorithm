package main

import "fmt"

func main() {
	var S string
	var i int 

	fmt.Scan(&S)
	fmt.Scan(&i)

	result := S[i-1]
	fmt.Println(string(result))
}
