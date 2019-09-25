package conway

import (
	"fmt"
	"strings"
)

// CellSet Set of cells
type CellSet struct {
	set map[Cell]bool
}

// NewCellSet Returns a new instance of `CellSet`
func NewCellSet() *CellSet {
	return &CellSet{make(map[Cell]bool)}
}

// CellSetFrom Create a new `CellSet` from the given cells
func CellSetFrom(cells ...Cell) *CellSet {
	set := NewCellSet()
	for _, cell := range cells {
		set.Add(cell)
	}
	return set
}

// Add a `Cell` to a `CellSet`
func (cs *CellSet) Add(cell Cell) bool {
	_, found := cs.set[cell]
	cs.set[cell] = true
	return !found
}

// Contains Check if the `CellSet` contains the given `Cell`
func (cs *CellSet) Contains(cell Cell) bool {
	_, found := cs.set[cell]
	return found
}

// Remove a `Cell`
func (cs *CellSet) Remove(cell Cell) {
	delete(cs.set, cell)
}

// Size of this `CellSet`
func (cs *CellSet) Size() int {
	return len(cs.set)
}

// Intersect returns a new `CellSet`
// containing the intersection of this CellSet with another one
func (cs *CellSet) Intersect(other *CellSet) *CellSet {
	intersect := NewCellSet()
	for _, cell := range cs.Cells() {
		for _, otherCell := range other.Cells() {
			if cell == otherCell {
				intersect.Add(cell)
			}
		}
	}
	return intersect
}

// Cells in this  `CellSet`
func (cs *CellSet) Cells() []Cell {
	cells := []Cell{}
	for cell := range cs.set {
		cells = append(cells, cell)
	}
	return cells
}

// ToString representation of this `CellSet`
func (cs *CellSet) ToString() string {
	s := "["
	for cell := range cs.set {
		s = s + fmt.Sprintf("(%d, %d) ", cell.X, cell.Y)
	}
	s = strings.TrimRight(s, " ")
	s = s + "]"
	return s
}
