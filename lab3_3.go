package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func main() {

	channel0 := make(chan string)
	channel1 := make(chan string)
	channel2 := make(chan string)

	var wg sync.WaitGroup

	for {
		isReciever := []bool{false, false, false}

		choice := rand.Intn(3)
		isReciever[choice] = true

		wg.Add(3)
		go smoker("tyutyun", isReciever[0], channel0, &wg)
		go smoker("paper", isReciever[1], channel1, &wg)
		go smoker("match", isReciever[2], channel2, &wg)

		if choice == 0 {
			material1 := <-channel1
			material2 := <-channel2
			channel0 <- material1
			channel0 <- material2
		}
		if choice == 1 {
			material1 := <-channel0
			material2 := <-channel2
			channel1 <- material1
			channel1 <- material2
		}
		if choice == 2 {
			material1 := <-channel0
			material2 := <-channel1
			channel2 <- material1
			channel2 <- material2
		}
		wg.Wait()
	}
}

func smoker(material string, isReciever bool, materials chan string, wg *sync.WaitGroup) {
	defer wg.Done()
	if isReciever {
		material2 := <-materials
		material3 := <-materials

		fmt.Printf("Smoker with %s got %s and %s\n", material, material2, material3)
		fmt.Printf("Smoker with %s is smoking...\n", material)
		time.Sleep(5 * time.Second)
		fmt.Printf("\nSmoker with %s finished smoking\n\n", material)
	} else {
		fmt.Printf("Smoker with %s send his material\n", material)
		materials <- material
	}
}
