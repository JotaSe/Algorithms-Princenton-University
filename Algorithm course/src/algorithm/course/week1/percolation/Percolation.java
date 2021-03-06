/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.course.week1.percolation;

/**
 *
 * @author Jota
 */
public class Percolation {

    private boolean[] cells;
    private WeightedQuickUnionUF cellStorage;
    public int size, startPoint, endPoint;

    // create N-by-N grid, with all sites blocked
    /**
     * Constructor
     *
     * @param N sqrt of number of sites
     */
    public Percolation(int N) {

        size = N;
        startPoint = size * size;
        endPoint = startPoint + 1;
        cells = new boolean[N * N + 2];
        cellStorage = new WeightedQuickUnionUF(cells.length);
    }

    /**
     * open site (row i, column j) if it is not already
     *
     * @param i row
     * @param j column
     */
    public void open(int i, int j) {
//        if (isOpen(i, j)) {
//            return;
//        }
        int cell = getCellIndex(i, j);
        cells[cell] = true;

        //TOP
        checkTop(i, j, cell);

        //BOTTOM
        checkBottom(i, j, cell);

        //BORDERS
        checkBorders(i, j, cell);





    }

    /**
     * is site (row i, column j) open?
     *
     * @param i row
     * @param j column
     * @return is open?
     */
    public boolean isOpen(int i, int j) {
        checkBoundException(i, j);
        return cells[getCellIndex(i, j)];
    }

    private void checkBoundException(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size) {
            throw new IndexOutOfBoundsException();

        }
    }

    /**
     * is site (row i, column j) full?
     *
     * @param i row
     * @param j column
     * @return is full?
     */
    public boolean isFull(int i, int j) {
        checkBoundException(i, j);
        return cellStorage.connected(startPoint, getCellIndex(i, j));
    }

    /**
     * Check if system percolate
     *
     * @return does the system percolate?
     */
    public boolean percolates() {
        return cellStorage.connected(startPoint, endPoint);
    }

    /**
     * Get cell index, emulating a matrix N*N; from 1 to N and casting it as an
     * array and get the cell index
     *
     * @param row
     * @param column
     * @return cell index
     */
    private int getCellIndex(int row, int column) {
        return (size * (row - 1)) + column - 1;
    }

    private void union(int i, int j) {
        if (cellStorage.connected(i, j)) {
            return;
        }
        cells[i] = true;
        cells[j] = true;
        cellStorage.union(i, j);
    }

    /**
     * Check if is top row
     *
     * @param i
     * @param j
     * @param cell
     */
    private void checkTop(int i, int j, int cell) {
        int temp = i - 1;
        if (i > 1 && 1 < size) {
            if (isOpen(temp, j)) {
                union(getCellIndex(temp, j), cell);
            }
        } else if (i == 1) {
            union(cell, startPoint);
        }
    }

    /**
     * Check if is bottom row
     *
     * @param i
     * @param j
     * @param cell
     */
    private void checkBottom(int i, int j, int cell) {
        int temp = i + 1;
        if (i < size && i > 0) {
            if (isOpen(temp, j)) {
                union(getCellIndex(temp, j), cell);
            }
        } else if (i == size) {
            union(cell, endPoint);
        }
    }

    /**
     * Check if is a border
     *
     * @param i
     * @param j
     * @param cell
     */
    private void checkBorders(int i, int j, int cell) {
        int temp = j - 1;
        //left border
        if (j > 1 && j <= size) {
            if (isOpen(i, temp)) {
                union(getCellIndex(i, temp), cell);
            }
        }
        temp += 2;
        if (j < size && j >= 1) {
            if (isOpen(i, temp)) {
                union(getCellIndex(i, temp), cell);
            }
        }


    }
}
