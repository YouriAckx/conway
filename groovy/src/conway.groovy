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

// Check Groovy intersect bug
def checkIntersectBug() {
    def foo = [[1], [2], [3]]
    def bar = [[2], [3], [4]]
    if (foo.intersect(bar).size() != 2) {
        println '''Warning!

The version of Groovy that you are running contains a bug on Collections.intersect().
Upgrade to 2.4.7 or later.
See http://stackoverflow.com/questions/35493088/groovy-strange-collectionintersect-behaviour

This program will not function correctly under this version.
Exiting'''
        System.exit(1)
    }
}

checkArgs()
checkIntersectBug()
def file = load(args[0])
def grid = Storage.load(file)

// Start the simulation
def game = new GameOfLife(grid)
while (true) {
    println game
    game.tick()
    if (game.isEmpty()) break
}
