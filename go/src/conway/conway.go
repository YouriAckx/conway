package conway

/*
A simple Conway's Game of Life in Groovy.
http://en.wikipedia.org/wiki/Conway's_Game_of_Life

No cycle detection. Infinite grid. No GUI.

Copyright (C) 2014 Youri Ackx under GNU General Public License.
See the LICENSE file and [http://www.gnu.org/licenses/].
*/

// Return all the neighbours of the given cell, living or not.
func neighbours(cell *Cell) *CellSet {
	n := NewCellSet()
	for i := -1; i <= 1; i++ {
		for j := -1; j <= 1; j++ {
			if i != 0 || j != 0 {
				n.Add(Cell{i + cell.X, j + cell.Y})
			}
		}
	}
	return n
}

// Count the living amongst the given neighbours, for the given cell.
func countLivingNeighbours(neighbours, grid *CellSet) int {
	intersect := 0
	for _, neighbour := range neighbours.Cells() {
		for _, cell := range grid.Cells() {
			if neighbour == cell {
				intersect++
			}
		}
	}
	return intersect
}

// Advance Move to the next state
func Advance(grid *CellSet) *CellSet {
	newGrid := NewCellSet()

	// All neighbours of all living cells. They are all potential newborns.
	livingCellsNeighbours := NewCellSet()

	// Survivors and current neighbours
	for _, living := range grid.Cells() {
		neighbours := neighbours(&living)
		countLivingNeighbours := countLivingNeighbours(neighbours, grid)
		if countLivingNeighbours == 2 || countLivingNeighbours == 3 {
			newGrid.Add(living)
		}
		for _, neighbour := range neighbours.Cells() {
			livingCellsNeighbours.Add(neighbour)
		}
	}

	// New borns (starting from all living cells neighbours)
	for _, candidate := range livingCellsNeighbours.Cells() {
		found := false
		for _, cell := range grid.Cells() {
			if candidate == cell {
				found = true
				break
			}
		}
		if !found {
			countLivingNeighbours := countLivingNeighbours(neighbours(&candidate), grid)
			if countLivingNeighbours == 3 {
				newGrid.Add(candidate)
			}
		}
	}

	return newGrid
}
