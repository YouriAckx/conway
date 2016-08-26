#!/usr/bin/env groovy

/*
A simple Conway's Game of Life in Groovy.
http://en.wikipedia.org/wiki/Conway's_Game_of_Life

No cycle detection. Infinite grid. No GUI.

Copyright (C) 2014 Youri Ackx under GNU General Public License.
See the LICENSE file and [http://www.gnu.org/licenses/].
*/


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


// Load from file
def file = new File(args[0])
if (!file.exists()) {
    println "File not found: ${file.absolutePath}"
    System.exit(1)
}
def grid = Storage.load(file)

// Start the simulation
def game = new GameOfLife(grid)
println game

while (!game.isEmpty()) {
    game.tick()
    println game
}
