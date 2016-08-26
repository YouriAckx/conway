#!/usr/bin/env groovy

/*
A simple Conway's Game of Life in Groovy.
http://en.wikipedia.org/wiki/Conway's_Game_of_Life

No cycle detection. Infinite grid. No GUI.

Copyright (C) 2014-2016 Youri Ackx under GNU General Public License.
See the LICENSE file and [http://www.gnu.org/licenses/].
*/


// Check args
def checkArgs() {
    if (args.size() != 1) {
        println "Usage: groovy conway.groovy path_to_game"
        System.exit(1)
    }
}

// Load from file
def load(path) {
    def file = new File(path)
    if (!file.exists()) {
        println "File not found: ${file.absolutePath}"
        System.exit(1)
    }
    file
}


checkArgs()
def file = load(args[0])
def grid = Storage.load(file)

// Start the simulation
def game = new GameOfLife(grid)
while (true) {
    println game
    game.tick()
    if (game.isEmpty()) break
}
