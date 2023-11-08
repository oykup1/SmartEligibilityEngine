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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println(
                    "Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        StdIn.setFile(args[0]);

        StdOut.setFile(args[2]);
        int capacity = StdIn.readInt();
        HashMap<String, ArrayList<String>> courseMap = new HashMap<>(capacity, 1);
        String name;
        int input;
        ArrayList<String> current;
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

        // Utilizing bfs.java too see if course2 is apart of course1 path
        String course1 = StdIn.readString();
        String course2 = StdIn.readString();
        boolean[] visited = new boolean[capacity];
        bfs explore = new bfs();
        visited = explore.bfsExplore(course1, indexMap, visited, courseMap);
        if (visited[indexMap.indexOf(course2)]) {
            StdOut.print("YES");
        } else {
            StdOut.print("NO");
        }

    }
}
