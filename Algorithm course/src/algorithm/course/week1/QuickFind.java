/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.course.week1;

/**
 *
 * @author Jota
 */
public class QuickFind {

    private int[] id;

    public QuickFind(int N) {
        id = new int[10];
        for (int i = 0; i < 10; i++) {
            id[i] = i;
        }
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);
        qf.setUnions("2-7 6-7 7-0 7-9 3-0 0-1");
        qf.print();
    }

    public void quickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    private void print() {
        for (int i = 0; i < id.length; i++) {
            System.out.print(id[i] + " ");
        }
    }

    private void setUnions(String source) {
        String[] pq = source.split(" ");
        for (String union : pq) {
            String[] value = union.split("-");
            this.union(Integer.parseInt(value[0].trim()), Integer.parseInt(value[1].trim()));
        }

    }
}
