#!/usr/bin/env groovy

/*
A simple Conway's Game of Life in Groovy.
http://en.wikipedia.org/wiki/Conway's_Game_of_Life

No cycle detection. Infinite grid. No GUI.
 
Copyright (C) 2014 Youri Ackx under GNU General Public License.
See the LICENSE file and [http://www.gnu.org/licenses/].
*/


/** Return all neighbours a the given cell. */
def neighbours(cell) {
    def (x, y) = cell
    def n = [(-1..1), (-1..1)].combinations()
    n.remove([0, 0])
    n.collect {
        def (nx, ny) = it
        [nx + x, ny + y]
    }
}


/** Count the living neighbours of the given cell. */
def countLivingNeighbours(neighbours, grid) {
    neighbours.intersect(grid).size() 
}


/** Advance the grid to its next state. */
def advance(grid) {
    // All neighbours of all living cells. They are all potential newborns.
    def livingCellsNeighbours = [] as Set
    // New grid after transition
    def newGrid = []

    // Survivors
    grid.each { livingCell ->
        def neighbours = neighbours(livingCell)
        def countLivingNeighbours = countLivingNeighbours(neighbours, grid)
        if (countLivingNeighbours in [2, 3]) {
            newGrid.add livingCell 
        }
        livingCellsNeighbours.addAll neighbours
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
def basic1 = [[0, 0], [1, 0], [2, 0], [1, 1], [2, 1]]
//Another grid
def basic2 = [[2, 2], [0, 1], [1, 1], [1, 0]]
// Block
def block = [[0, 0], [1, 0], [0, 1], [1, 1]]
// Blinker
def blinker = [[1, 1], [2, 1], [3, 1]]
// Glider
def glider =  [[1, 0], [2, 1], [0, 2], [1, 2], [2, 2]]
// DIe hard
def diehard = [[1, 2], [2, 2], [2, 3], [7, 1], [6, 3], [7, 3], [8, 3]]

def grid = diehard

// Start the simulation
while (!grid.empty) {
    println grid
    grid = advance grid
}
println grid
