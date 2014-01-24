package conway

import (
	"fmt"
	"strings"
)

type CellSet struct {
	set map[Cell]bool
}

func NewCellSet() *CellSet {
	return &CellSet{make(map[Cell]bool)}
}

func CellSetFrom(cells ...Cell) *CellSet {
	set := NewCellSet()
	for _, cell := range cells {
		set.Add(cell)
	}
	return set
}

func (set *CellSet) Add(cell Cell) bool {
	_, found := set.set[cell]
	set.set[cell] = true
	return !found	//False if it existed already
}

func (set *CellSet) Contains(cell Cell) bool {
	_, found := set.set[cell]
	return found	//true if it existed already
}

func (set *CellSet) Remove(cell Cell) {
	delete(set.set, cell)
}

func (set *CellSet) Size() int {
	return len(set.set)
}

func (set *CellSet) Intersect(other *CellSet) *CellSet {
	intersect := NewCellSet()
	for _, cell := range set.Cells() {
		for _, otherCell := range other.Cells() {
			if cell == otherCell {
				intersect.Add(cell)
			}
		}
	}
	return intersect
}


func (cellSet *CellSet) Cells() []Cell {
	cells := []Cell{}
	for cell, _ := range cellSet.set {
		cells = append(cells, cell)
	}
	return cells
}

func (cellSet *CellSet) ToString() string {
	s := "["
	for cell, _ := range cellSet.set {
		s = s + fmt.Sprintf("(%d, %d) ", cell.X, cell.Y)
	}
	s = strings.TrimRight(s, " ")
	s = s + "]"
	return s
}