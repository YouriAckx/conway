Conway's Game of Life
=====================

A simple [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) in [Groovy](http://groovy.codehaus.org/) and in [Go](http://golang.org/).

* No cycle detection
* Infinite grid
* No GUI

## Groovy

About 50 lines of code. Internally, the grid is represented by a `Set` in which each element
is a living cell as a `[x, y]`.

The initial game state is loaded from a plain-text file (see [samples](samples)).

    $ cat samples/die-hard.txt
    .........
    .......*.
    .**......
    ..*...***

Runs with Groovy 2.4.7 or above.

    src $ ./conway.groovy ../../samples/basic.txt
    [[0, 0], [1, 0], [2, 0], [1, 1], [2, 1]]
    [[0, 0], [2, 0], [2, 1], [1, -1], [0, 1]]
    [[0, 0], [2, 0], [1, -1]]
    [[1, -1], [1, 0]]
    []

⚠️ WARNING! In some versions of Groovy, the Collection `.intersect()` method is broken around `2.4.2` up to `2.4.6`.

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
