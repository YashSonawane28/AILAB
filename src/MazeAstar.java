import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Node {
    int x, y;
    int cost;
    int heuristic;
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.cost = 0;
        this.heuristic = 0;
        this.parent = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}

public class MazeAstar {

    public static int calculateHeuristic(Node node, Node goal) {
        return Math.abs(node.x - goal.x) + Math.abs(node.y - goal.y);
    }

    public static List<Node> findPath(int[][] grid, Node start, Node goal) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost + a.heuristic));
        Set<Node> closedSet = new HashSet<>();

        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                return buildPath(current);
            }

            closedSet.add(current);

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValidMove(newX, newY, grid)) {
                    Node neighbor = new Node(newX, newY);

                    if (!closedSet.contains(neighbor) || current.cost + 1 < neighbor.cost) {
                        neighbor.cost = current.cost + 1;
                        neighbor.heuristic = calculateHeuristic(neighbor, goal);
                        neighbor.parent = current;

                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor);
                        }
                    }
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private static boolean isValidMove(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0;
    }

    private static List<Node> buildPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        Node start = new Node(0, 0);
        Node goal = new Node(4, 4);

        List<Node> path = findPath(grid, start, goal);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
