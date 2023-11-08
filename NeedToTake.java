package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println(
                    "Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

        StdIn.setFile(args[0]);
        StdOut.setFile(args[2]);
        int capacity = StdIn.readInt();
        HashMap<String, ArrayList<String>> courseMap = new HashMap<>(capacity, 1);
        String name;
        int input;
        ArrayList<String> current;

        // Creating new keys and putting empty arraylists as their values;
        for (int i = 0; i < capacity; i++) {
            ArrayList<String> list = new ArrayList<String>();
            name = StdIn.readString();
            courseMap.put(name, list);
        }
        input = StdIn.readInt();
        for (int i = 0; i < input; i++) {
            name = StdIn.readString();
            String prereq = StdIn.readString();
            current = courseMap.get(name);
            current.add(prereq);
        }
        // Creating an array with the positions of each key, giving them an index.
        ArrayList<String> indexMap = new ArrayList<String>();
        for (String key : courseMap.keySet()) {
            indexMap.add(key);
            current = courseMap.get(key);
            for (String c : current) {
                indexMap.add(c);
            }
        }
        StdIn.setFile(args[1]);
        boolean[] visited = new boolean[capacity];
        boolean[] visitedTarget = new boolean[capacity];
        bfs explore = new bfs();
        String target = StdIn.readString();
        input = StdIn.readInt();
        for (int i = 0; i < input; i++) {
            name = StdIn.readString();
            visited = explore.bfsExplore(name, indexMap, visited, courseMap);
        }
        visitedTarget = explore.bfsExplore(target, indexMap, visitedTarget, courseMap);

        ArrayList<String> needtoTake = new ArrayList<String>();
        for (int i = 0; i < capacity; i++) {
            if (visitedTarget[i] && (indexMap.indexOf(target) != i)) {
                if (!visited[i]) {
                    needtoTake.add(indexMap.get(i));
                }
            }
        }

        // Printing
        for (String c : needtoTake) {
            StdOut.println(c);
        }

    }
}
