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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then
 * listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println(
                    "Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

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
        // Only for printing
        for (String key : courseMap.keySet()) {
            StdOut.print(key + " ");
            current = courseMap.get(key);
            for (String c : current) {
                StdOut.print(c + " ");
            }
            StdOut.println();
        }
    }

}
