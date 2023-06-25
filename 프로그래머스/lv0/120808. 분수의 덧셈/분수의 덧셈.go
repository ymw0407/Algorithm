func solution(numer1 int, denom1 int, numer2 int, denom2 int) []int {
    denom := lcm(denom1, denom2)
    numer := denom / denom1 * numer1 + denom / denom2 * numer2 
    
    gcdVal := gcd(denom, numer)
    
    numer = numer / gcdVal
    denom = denom / gcdVal
    
    ans := []int{numer, denom}
    
    return ans
}

func gcd(a, b int) int {
    for b != 0 {
        r := a % b
        a = b
        b = r
    }
    return a
}

func lcm(a, b int) int {
    gcdVal := gcd(a, b)
    ret := a * b / gcdVal
    return ret
}