package Metodos;
import java.util.*;

class GraphSearch {
    static class Node {
        String name;
        int cost;
        Node parent;
        
        Node(String name, int cost, Node parent) {
            this.name = name;
            this.cost = cost;
            this.parent = parent;
        }
    }
    
    static List<String> reconstructPath(Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(node.name);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
    
    static List<String> dfs(Map<String, List<Node>> graph, String start, String goal) {
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(new Node(start, 0, null));
        int nodesExplored = 0;
        
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visited.contains(node.name)) {
                visited.add(node.name);
                nodesExplored++;
                if (node.name.equals(goal)) {
                    System.out.println("DFS Nodes Explored: " + nodesExplored);
                    return reconstructPath(node);
                }
                for (Node neighbor : graph.getOrDefault(node.name, new ArrayList<>())) {
                    stack.push(new Node(neighbor.name, 0, node));
                }
            }
        }
        System.out.println("DFS Nodes Explored: " + nodesExplored);
        return null;
    }
    
    static List<String> bfs(Map<String, List<Node>> graph, String start, String goal) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, 0, null));
        int nodesExplored = 0;
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visited.contains(node.name)) {
                visited.add(node.name);
                nodesExplored++;
                if (node.name.equals(goal)) {
                    System.out.println("BFS Nodes Explored: " + nodesExplored);
                    return reconstructPath(node);
                }
                for (Node neighbor : graph.getOrDefault(node.name, new ArrayList<>())) {
                    queue.add(new Node(neighbor.name, 0, node));
                }
            }
        }
        System.out.println("BFS Nodes Explored: " + nodesExplored);
        return null;
    }
    
    static List<String> ucs(Map<String, List<Node>> graph, String start, String goal) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Map<String, Integer> visited = new HashMap<>();
        pq.add(new Node(start, 0, null));
        int nodesExplored = 0;
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited.containsKey(node.name) && visited.get(node.name) <= node.cost) continue;
            visited.put(node.name, node.cost);
            nodesExplored++;
            if (node.name.equals(goal)) {
                System.out.println("UCS Nodes Explored: " + nodesExplored);
                return reconstructPath(node);
            }
            for (Node neighbor : graph.getOrDefault(node.name, new ArrayList<>())) {
                pq.add(new Node(neighbor.name, node.cost + neighbor.cost, node));
            }
        }
        System.out.println("UCS Nodes Explored: " + nodesExplored);
        return null;
    }
    
    public static void main(String[] args) {
        Map<String, List<Node>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new Node("B", 1, null), new Node("C", 4, null)));
        graph.put("B", Arrays.asList(new Node("D", 2, null), new Node("E", 5, null)));
        graph.put("C", Arrays.asList(new Node("F", 3, null)));
        graph.put("D", Arrays.asList(new Node("G", 1, null)));
        graph.put("E", Arrays.asList(new Node("G", 2, null)));
        graph.put("F", Arrays.asList(new Node("G", 2, null)));
        graph.put("G", new ArrayList<>());
        
        List<String> dfsPath = dfs(graph, "A", "G");
        List<String> bfsPath = bfs(graph, "A", "G");
        List<String> ucsPath = ucs(graph, "A", "G");
        
        System.out.println("DFS Path: " + dfsPath);
        System.out.println("BFS Path: " + bfsPath);
        System.out.println("UCS Path: " + ucsPath);
        
        String bestMethod = "";
        int minExplored = Integer.MAX_VALUE;
        
        Map<String, Integer> results = new HashMap<>();
        results.put("DFS", dfsPath == null ? Integer.MAX_VALUE : dfsPath.size());
        results.put("BFS", bfsPath == null ? Integer.MAX_VALUE : bfsPath.size());
        results.put("UCS", ucsPath == null ? Integer.MAX_VALUE : ucsPath.size());
        
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (entry.getValue() < minExplored) {
                minExplored = entry.getValue();
                bestMethod = entry.getKey();
            }
        }
        
        System.out.println("Best Search Method: " + bestMethod + " with " + minExplored + " nodes explored.");
    }
}
