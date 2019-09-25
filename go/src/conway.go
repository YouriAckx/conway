package main

import (
	"conway"
	"fmt"
)

func main() {
	// Basic grid
	basic := conway.CellSetFrom(
		conway.Cell{0, 0}, conway.Cell{1, 0}, conway.Cell{2, 0},
		conway.Cell{1, 1}, conway.Cell{2, 1})
	/*
		// Blinker
		blinker := conway.CellSetFrom(
				conway.Cell{1, 1}, conway.Cell{2, 1}, conway.Cell{3, 1})

		// Glider
		glider :=  conway.CellSetFrom(
				conway.Cell{1, 0}, conway.Cell{2, 1}, conway.Cell{0, 2},
				conway.Cell{1, 2}, conway.Cell{2, 2})

		// Die hard
		diehard := conway.CellSetFrom(
				conway.Cell{1, 2}, conway.Cell{2, 2}, conway.Cell{2, 3},
				conway.Cell{7, 1}, conway.Cell{6, 3}, conway.Cell{7, 3},
				conway.Cell{8, 3})
	*/

	grid := basic
	fmt.Println(grid.ToString())
	for grid.Size() > 0 {
		grid = conway.Advance(grid)
		fmt.Println(grid.ToString())
	}
}
