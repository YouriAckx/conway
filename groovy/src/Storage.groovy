/**
 * Persistence
 */
class Storage {

    /**
     * Load a game from file.
     * @param f File to load
     * @return The game as a Set of [x, y] living cells.
     */
    public static Set load(File f) {
        def grid = [] as Set
        f.eachLine { line, y ->
            line.eachWithIndex { c, x ->
                if (c == '*') {
                    grid << [x, y - 1]
                }
            }
        }

        grid
    }
}
