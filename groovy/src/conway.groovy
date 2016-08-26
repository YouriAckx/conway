#!/usr/bin/env groovy

/*
A simple Conway's Game of Life in Groovy.
http://en.wikipedia.org/wiki/Conway's_Game_of_Life

No cycle detection. Infinite grid. No GUI.

Copyright (C) 2014 Youri Ackx under GNU General Public License.
See the LICENSE file and [http://www.gnu.org/licenses/].
*/


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
