package conway

import (
	"testing"
	"reflect"
)

func TestNeighbours(t *testing.T) {
	var tests = []struct {
		cell Cell
		want *CellSet
	}{
		{
		Cell{2, 4}, CellSetFrom(
			Cell{1, 3}, Cell{1, 4}, Cell{1, 5},
			Cell{2, 3},             Cell{2, 5},
			Cell{3, 3}, Cell{3, 4}, Cell{3, 5}),
		},
	}

	for _, c := range tests {
		got := neighbours(&c.cell)
		if !reflect.DeepEqual(got, c.want) {
			t.Errorf("neighbours(%q) == %q, want %q", c.cell, got, c.want)
		}
	}
}

func TestCountLivingNeighbours(t *testing.T) {
	var tests = []struct {
		neighbours, grid *CellSet
		count int
	}{
		{
			CellSetFrom(Cell{0, 1}),
			CellSetFrom(Cell{0, 0}),
			0,
		},
		{
			CellSetFrom(Cell{0, 0}, Cell{1, 0}),
			CellSetFrom(Cell{0, 0}, Cell{1, 0}),
			2,
		},
		{
			CellSetFrom(Cell{0, 0}),
			CellSetFrom(Cell{0, 0}, Cell{1, 0}),
			1,
		},
		{
			NewCellSet(),
			CellSetFrom(Cell{0, 0}, Cell{1, 0}),
			0,
		},
		{
			NewCellSet(),
			NewCellSet(),
			0,
		},
	}

	for _, c := range tests {
		got := countLivingNeighbours(c.neighbours, c.grid)
		if got != c.count {
			t.Errorf("countLivingNeighbours: neighbours %q; grid %q -> %d, want %d", c.neighbours, c.grid, got, c.count)
		}
	}

}
