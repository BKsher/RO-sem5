package main

import (
	"fmt"
	"sync"
	"time"
)

var energy [8]int

func main() {
	energy = [8]int{1, 2, 3, 4, 5, 6, 7, 8}
	currentParticipents := []int{1, 2, 3, 4, 5, 6, 7, 8}
	var winners []int
	totalParticipents := 8
	round := 1

	result := make(chan int)
	var wg sync.WaitGroup

	for totalParticipents > 1 {
		fmt.Printf("Participents of round %d are: ", round)
		fmt.Println(currentParticipents)
		winners = nil
		for i := 0; i < totalParticipents; i += 2 {
			wg.Add(1)
			go fight(currentParticipents[i], currentParticipents[i+1], &wg, result)
			winners = append(winners, <-result)
		}
		wg.Wait()
		fmt.Printf("Winners of round %d are: ", round)
		fmt.Println(winners)
		fmt.Println()
		currentParticipents = winners
		totalParticipents /= 2
		round++
	}
}

func fight(a, b int, wg *sync.WaitGroup, result chan<- int) {
	defer wg.Done()
	if energy[a-1] > energy[b-1] {
		result <- a
	} else {
		result <- b
	}
	time.Sleep(4 * time.Second)
}
