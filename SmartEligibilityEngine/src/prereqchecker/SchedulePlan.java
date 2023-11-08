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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the
 * course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println(
                    "Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
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

        // scheduling

        ArrayList<String> schedule = new ArrayList<String>();
        int semesters = 0;
        int size = needtoTake.size();
        while (schedule.size() != size) {
            Iterator<String> itr = needtoTake.iterator();
            while (itr.hasNext()) {
                String element = itr.next();
                if (!schedule.contains(element)) {
                    ArrayList<String> list = courseMap.get(element);
                    int counter = list.size();
                    for (String c : list) {
                        if (visited[indexMap.indexOf(c)]) {
                            counter--;
                        }
                    }
                    if (counter == 0)
                        schedule.add(element);
                }

            }
            for (String c : schedule) {
                int i = indexMap.indexOf(c);
                if (i != -1) {
                    visited[i] = true;
                }
            }
            schedule.add("new");
            size++;
            semesters++;
        }
        // Printing schedule
        StdOut.println(semesters);
        for (String c : schedule) {
            if (c == "new") {
                StdOut.println();
            } else {
                StdOut.print(c + " ");
            }
        }
    }
}
