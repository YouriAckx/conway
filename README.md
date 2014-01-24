Conway's Game of Life
=====================

A simple [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) in [Groovy](http://groovy.codehaus.org/) and in [Go](http://golang.org/).

* No cycle detection
* Infinite grid
* No GUI

## Groovy

About 40 lines of code. Unlike in the usual examples, the grid is defined by set of cells rather than a 2 dimensional array. Each cell is a [x, y] pair:

    // Blinker
    Set blinker = [[1, 1], [2, 1], [3, 1]] as Set

Runs with Groovy 2.2.1:

    youri@gyros conway> ./conway.groovy
    [[0, 0], [1, 0], [2, 0], [1, 1], [2, 1]]
    [[0, 0], [2, 0], [2, 1], [1, -1], [0, 1]]
    [[0, 0], [2, 0], [1, -1]]
    [[1, -1], [1, 0]]
    []

## Go

About 65 lines of code, plus 70 for a simple Set implementation. Runs with Go 1.2:

    youri@gyros src> go run conway.go
    [(0, 0) (1, 0) (2, 0) (1, 1) (2, 1)]
    [(0, 0) (2, 0) (2, 1) (1, -1) (0, 1)]
    [(0, 0) (2, 0) (1, -1)]
    [(1, -1) (1, 0)]
    []

Unit tests:

    youri@gyros conway> go test
    PASS
    ok    _/Users/youri/conway/go/src/conway    0.015s