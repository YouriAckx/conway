class Storage {

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
