import java.util.*;

class Pair {
    int p1;
    int p2;
    List<Pair> path;

    Pair(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
        path = new ArrayList<>();

    }

    Pair(int p1, int p2, List<Pair> _path) {
        this.p1 = p1;
        this.p2 = p2;
        path = new ArrayList<>();
        path.addAll(_path);
        path.add(new Pair(this.p1, this.p2));
    }

}

public class waterjug{

    public static void getPathIfPossible(int jug1, int jug2, int target) {
        Queue<Pair> q = new LinkedList<>();
        boolean visited[][] = new boolean[jug1 + 1][jug2 + 1];

        Pair initialstate = new Pair(0, 0);
        initialstate.path.add(new Pair(0, 0));

        q.offer(initialstate);
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            if (curr.p1 > jug1 || curr.p2 > jug2 || visited[curr.p1][curr.p2]) {
                continue;

            }

            visited[curr.p1][curr.p2] = true;
            if (curr.p1 == target || curr.p2 == target) {
                if (curr.p1 == target) {
                    curr.path.add(new Pair(curr.p1, 0));

                } else {
                    curr.path.add(new Pair(0, curr.p2));
                }

                for (int i = 0; i < curr.path.size(); i++) {
                    System.out.println(curr.path.get(i).p1 + "," + curr.path.get(i).p2);
                }

                return;

            }

            q.offer(new Pair(jug1, 0, curr.path));
            q.offer(new Pair(0, jug2, curr.path));

            q.offer(new Pair(jug1, curr.p2, curr.path));
            q.offer(new Pair(curr.p1, jug2, curr.path));

            q.offer(new Pair(curr.p1, 0, curr.path));
            q.offer(new Pair(0, curr.p2, curr.path));

            int emptyJug = jug2 - curr.p2;
            int amountTransferred = Math.min(curr.p1, emptyJug);
            int p1 = curr.p1 - amountTransferred;
            int p2 = curr.p2 + amountTransferred;
            q.offer(new Pair(p1, p2, curr.path));

            emptyJug = jug1 - curr.p1;
            amountTransferred = Math.min(curr.p2, emptyJug);
            p1 = curr.p1 + amountTransferred;
            p2 = curr.p2 - amountTransferred;
            q.offer(new Pair(p1, p2, curr.path));

        }

        System.out.println("No solution found");
    }

    public static void main(String args[]) {
        int jug1 = 4;
        int jug2 = 3;
        int target = 2;

        getPathIfPossible(jug1, jug2, target);

    }
}