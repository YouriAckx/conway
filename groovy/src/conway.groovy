#!/usr/bin/env groovy

/*
A simple Conway's Game of Life in Groovy.
http://en.wikipedia.org/wiki/Conway's_Game_of_Life

No cycle detection. Infinite grid. No GUI.
 
Copyright (C) 2014 Youri Ackx under GNU General Public License.
See the LICENSE file and [http://www.gnu.org/licenses/].
*/


/** Return all the neighbours of the given cell, living or not. */
Set neighbours(cell) {
    def (int x, int y) = cell
    Set n = [(-1..1), (-1..1)].combinations()
    n.remove([0, 0])
    
    return n.collect {
        def (nx, ny) = it
        [nx + x, ny + y]
    } as Set
}


/** Count the living neighbours of the given cell. */
int countLivingNeighbours(Set neighbours, Set grid) {
    neighbours.intersect(grid).size() 
}


/** Advance the grid to its next state. */
Set advance(Set grid) {
    // All neighbours of all living cells. They are all potential newborns.
    Set livingCellsNeighbours = [] as Set

    // New grid after transition
    Set newGrid = [] as Set

    // Survivors and current neighbours
    grid.each { livingCell ->
        Set neighbours = neighbours(livingCell)
        def countLivingNeighbours = countLivingNeighbours(neighbours, grid)
        if (countLivingNeighbours in [2, 3]) {
            newGrid.add(livingCell)
        }
        livingCellsNeighbours.addAll(neighbours)
    }

    // New borns (starting from all living cells neighbours)
    livingCellsNeighbours.each { newBornCandidate ->
        if (!grid.contains(newBornCandidate)) {          // do not test alive cells
            def countLivingNeighbours = countLivingNeighbours(neighbours(newBornCandidate), grid)
            if (countLivingNeighbours == 3) {
                newGrid.add newBornCandidate
            }
        }
    }

    return newGrid
}


// A grid is represented by the (x,y) coords of its living cells.
// The grid is infinite. Therefore, coordinates are allowed to be negative.

// Basic grid
Set basic1 = [[0, 0], [1, 0], [2, 0], [1, 1], [2, 1]] as Set
//Another grid
Set basic2 = [[2, 2], [0, 1], [1, 1], [1, 0]] as Set
// Block
Set block = [[0, 0], [1, 0], [0, 1], [1, 1]] as Set
// Blinker
Set blinker = [[1, 1], [2, 1], [3, 1]] as Set
// Glider
Set glider =  [[1, 0], [2, 1], [0, 2], [1, 2], [2, 2]] as Set
// Die hard
Set diehard = [[1, 2], [2, 2], [2, 3], [7, 1], [6, 3], [7, 3], [8, 3]] as Set

Set grid = basic1

// Start the simulation
while (!grid.empty) {
    println grid
    grid = advance grid
}
println grid
