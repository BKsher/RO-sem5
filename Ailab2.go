package main

import (
	"fmt"
	"math/rand"
	"sync"
)

const (
	monastery1 = "Guan-In"
	monastery2 = "Guan-Yan"
)

var wg sync.WaitGroup

type monk struct {
	name   string
	energy int
}

func main() {
	// create slices of monks for each monastery
	monastery1Monks := []monk{
		{"Monk1", rand.Intn(100)},
		{"Monk2", rand.Intn(100)},
		{"Monk3", rand.Intn(100)},
		{"Monk4", rand.Intn(100)},
	}

	monastery2Monks := []monk{
		{"Monk5", rand.Intn(100)},
		{"Monk6", rand.Intn(100)},
		{"Monk7", rand.Intn(100)},
		{"Monk8", rand.Intn(100)},
	}

	// start the tournament
	winner := startTournament(monastery1Monks, monastery2Monks)

	// print the winner
	fmt.Printf("The winner is %s\n", winner.name)
}

func startTournament(monks1, monks2 []monk) monk {
	// create a slice of all monks
	monks := append(monks1, monks2...)

	// loop until only one monk is left
	for len(monks) > 1 {
		// create a slice to store the winners of this round
		var winners []monk

		// loop through pairs of monks and start a go routine for each pair
		for i := 0; i < len(monks); i += 2 {
			wg.Add(1)
			go func(m1, m2 monk) {
				// determine the winner and append to the winners slice
				winner := determineWinner(m1, m2)
				winners = append(winners, winner)
				wg.Done()
			}(monks[i], monks[i+1])
		}

		// wait for all fights to finish
		wg.Wait()

		// set the monks slice to the winners slice for the next round
		monks = winners
	}

	// return the remaining monk as the winner
	return monks[0]
}

func determineWinner(m1, m2 monk) monk {
	if m1.energy > m2.energy {
		return m1
	}
	return m2
}
