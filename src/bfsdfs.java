import java.util.*;
public class bfsdfs{
    public static class edge{
        int source;
        int destination;

        public edge(int s,int d)
        {
            this.source=s;
            this.destination=d;
        }

    }

    public static void DFS(ArrayList<edge> graph[],int V)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Starting vertex ");
        int start = sc.nextInt();
        Boolean visited[] = new Boolean[V];
        for(int i=0;i<visited.length;i++)
        {
            visited[i]=false;
        }
        Stack<Integer> s = new Stack();
        s.push(start);
        while(!s.isEmpty())
        {
            int vertex = s.pop();
            if(visited[vertex]==false)
            {
                System.out.print(vertex+" ");
                visited[vertex]=true;
                for(int i=graph[vertex].size()-1;i>=0;i--)
                {
                    edge e = graph[vertex].get(i);
                    s.push(e.destination);
                }
            }
        }

    }

    public static void BFS(ArrayList<edge> graph[],int V)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Starting vertex ");
        int start = sc.nextInt();
        Boolean visited[] = new Boolean[V];
        for(int i=0;i<visited.length;i++)
        {
            visited[i]=false;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty())
        {
            int vertex = q.remove();
            if(visited[vertex]==false)
            {
                System.out.print(vertex+" ");
                visited[vertex]=true;
                for(int i=0;i<graph[vertex].size();i++)
                {
                    edge e = graph[vertex].get(i);
                    q.add(e.destination);
                }
            }
        }
    }

    public static void creategraph(ArrayList<edge> graph[])
    {
        for(int i=0;i<graph.length;i++)
        {
            graph[i]=new ArrayList<edge>();
        }
        graph[0].add(new edge(0,1));
        graph[0].add(new edge(0,2));
        graph[1].add(new edge(1,0));
        graph[1].add(new edge(1, 3));
        graph[2].add(new edge(2, 0));
        graph[2].add(new edge(2, 4));
        graph[3].add(new edge(3, 1));
        graph[3].add(new edge(3, 4));
        graph[3].add(new edge(3, 5));
        graph[4].add(new edge(4, 2));
        graph[4].add(new edge(4, 3));
        graph[4].add(new edge(4, 5));
        graph[5].add(new edge(5, 3));
        graph[5].add(new edge(5, 4));
        graph[5].add(new edge(5, 6));
        graph[6].add(new edge(6, 5));

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*

            1 ------ 3
           /         | \
          0          |   5 -- 6
           \         | /
            2 ------ 4

        */
        int V=7;
        ArrayList<edge> graph[] = new ArrayList[V];
        creategraph(graph);
        System.out.println("BFS Traversal : ");
        BFS(graph,V);
        System.out.println("\nDFS Traversal : ");
        DFS(graph,V);




    }
}