/**
 * A simple Conway's Game of Life in Groovy.
 * http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 *
 * No cycle detection. Infinite grid. No GUI.
 *
 * Copyright (C) 2014-2016 Youri Ackx under GNU General Public License.
 * See the LICENSE file and [http://www.gnu.org/licenses/].
 */
 class GameOfLife {

     /**
      * A grid is represented by the (x,y) coords of its living cells.
      * The grid is infinite. Therefore, coordinates are allowed to be negative.
      */
     private Set grid

     /** Neighbours deltas: [-1, -1] .. [1, 1] */
     private static Set deltas

     static { deltas = initDeltas() }

     private static Set initDeltas() {
         def n = [(-1..1), (-1..1)].combinations() as Set
         n.remove([0, 0])
         n
     }


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
             livingCellsNeighbours.addAll(neighbours)
             if (countLivingNeighbours(neighbours) in [2, 3]) {
                 newGrid << livingCell
             }
         }

         // Newborns (starting from all living cells neighbours)
         livingCellsNeighbours.each { newBornCandidate ->
             if (!grid.contains(newBornCandidate) // do not test alive cells
                    && countLivingNeighbours(neighbours(newBornCandidate)) == 3) {
                 newGrid << newBornCandidate
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
         deltas.collect { nx, ny -> [nx + x, ny + y] } as Set
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
