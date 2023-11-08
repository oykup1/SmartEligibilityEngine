package prereqchecker;

import java.util.*;

public class bfs {

    public bfs() {

    }

    public boolean[] bfsExplore(String course1, ArrayList<String> indexMap, boolean[] visited,
            HashMap<String, ArrayList<String>> courseMap) {
        Queue<String> myQueue = new Queue<String>();

        myQueue.enqueue(course1);
        while (!myQueue.isEmpty()) {
            String v = myQueue.dequeue();
            visited[indexMap.indexOf(v)] = true;
            ArrayList<String> list = courseMap.get(v);
            for (String c : list) {
                myQueue.enqueue(c);
            }
        }
        return visited;

    }

}
