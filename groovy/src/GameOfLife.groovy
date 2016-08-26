/**
 * A simple Conway's Game of Life in Groovy.
 * http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 *
 * No cycle detection. Infinite grid. No GUI.
 *
 * Copyright (C) 2014 Youri Ackx under GNU General Public License.
 * See the LICENSE file and [http://www.gnu.org/licenses/].
 */
 class GameOfLife {

     /**
      * A grid is represented by the (x,y) coords of its living cells.
      * The grid is infinite. Therefore, coordinates are allowed to be negative.
      */
     private Set grid


     /**
      * Instanciate a game based on a set reprensentation of cells.
      * @param grid The game reprensentation
      */
     public GameOfLife(Set grid) {
         this.grid = grid.collect()
     }


     /** Test if the grid is empty (no living cells) */
     public isEmpty() {
         grid.isEmpty()
     }


     /** Advance the grid to its next state. */
     public void tick() {
         // All neighbours of all living cells. They are all potential newborns.
         Set livingCellsNeighbours = [] as Set

         // New grid after transition
         Set newGrid = [] as Set

         // Survivors and current neighbours
         grid.each { livingCell ->
             Set neighbours = neighbours(livingCell)
             int livingNeighboursCount = countLivingNeighbours(neighbours)
             if (livingNeighboursCount in [2, 3]) {
                 newGrid.add(livingCell)
             }
             livingCellsNeighbours.addAll(neighbours)
         }

         // New borns (starting from all living cells neighbours)
         livingCellsNeighbours.each { newBornCandidate ->
             if (!grid.contains(newBornCandidate)) {          // do not test alive cells
                 int livingNeighboursCount = countLivingNeighbours(neighbours(newBornCandidate))
                 if (livingNeighboursCount == 3) {
                     newGrid.add newBornCandidate
                 }
             }
         }
         grid = newGrid
     }


     /**
      * Return all the neighbours of the given cell, living or not.
      * @param cell A cell
      */
     private Set neighbours(cell) {
         def (int x, int y) = cell
         // TODO Combinations can be done only once
         Set n = [(-1..1), (-1..1)].combinations()
         n.remove([0, 0])

         return n.collect {
             def (nx, ny) = it
             [nx + x, ny + y]
         } as Set
     }


     /**
      * Count the living neighbours of the given cell.
      * @param cell A cell
      */
     private int countLivingNeighbours(Set neighbours) {
         neighbours.intersect(grid).size()
     }


     @Override
     public String toString() {
         grid.toString()
     }
 }
