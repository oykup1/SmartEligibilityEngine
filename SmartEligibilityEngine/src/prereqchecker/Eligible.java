package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println(
                    "Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
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
        // Using bfs and checking that given visited array to see which key's values
        // have all been visited.
        ArrayList<String> eligibleCourses = new ArrayList<String>();
        StdIn.setFile(args[1]);
        input = StdIn.readInt();
        boolean[] visited = new boolean[capacity];
        bfs explore = new bfs();
        for (int i = 0; i < input; i++) {
            name = StdIn.readString();
            visited = explore.bfsExplore(name, indexMap, visited, courseMap);
        }

        for (int i = 0; i < capacity; i++) {
            if (!visited[i]) {
                ArrayList<String> list = courseMap.get(indexMap.get(i));
                int counter = list.size();
                for (String c : list) {
                    if (visited[indexMap.indexOf(c)]) {
                        counter--;
                    }
                }
                if (counter == 0)
                    eligibleCourses.add(indexMap.get(i));
            }
        }
        // Printing out eligible courses
        for (String c : eligibleCourses) {
            StdOut.println(c);
        }

    }
}
