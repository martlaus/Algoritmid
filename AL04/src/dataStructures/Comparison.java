package dataStructures;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by mart on 19.10.15.
 */
public class Comparison {

    public static PriorityQueue<Integer> ascPriorityQueue(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);

        for (int i = 0; i < n; i++)
            queue.add(i);

        return queue;
    }

    public static PriorityQueue<Integer> randomPriorityQueue(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++)
            queue.add(random.nextInt(n));

        return queue;
    }

    public static TreeSet<Integer> ascTree(int n) {
        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < n; i++)
            tree.add(i);

        return tree;
    }

    public static TreeSet<Integer> randomTree(int n) {
        TreeSet<Integer> tree = new TreeSet<>();
        Random random = new Random();

        for (int i = 0; i < n; i++)
            tree.add(random.nextInt(n));

        return tree;
    }

    public static Hashtable<Integer, Integer> ascHashTable(int n) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>(n);

        for (int i = 0; i < n; i++)
            hashtable.put(i, i);

        return hashtable;
    }

    public static Hashtable<Integer, Integer> randomHashTable(int n) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int value = random.nextInt();

            hashtable.put(value, value);
        }

        return hashtable;
    }

    public static ArrayList<AbstractMap.SimpleEntry<String, Consumer<Integer>>> getListOfMethods() {
        ArrayList<AbstractMap.SimpleEntry<String, Consumer<Integer>>> methods = new ArrayList<>();

        methods.add(new AbstractMap.SimpleEntry<>("ascPriorityQueue", Comparison::ascPriorityQueue));
        methods.add(new AbstractMap.SimpleEntry<>("randomPriorityQueue", Comparison::randomPriorityQueue));
        methods.add(new AbstractMap.SimpleEntry<>("ascTree", Comparison::ascTree));
        methods.add(new AbstractMap.SimpleEntry<>("randomTree", Comparison::randomTree));
        methods.add(new AbstractMap.SimpleEntry<>("ascHashTable", Comparison::ascHashTable));
        methods.add(new AbstractMap.SimpleEntry<>("randomHashTable", Comparison::randomHashTable));

        return methods;
    }

    public static List<String> dataStructureComparison(int n) {
        ArrayList<AbstractMap.SimpleEntry<String, Consumer<Integer>>> methods = getListOfMethods();
        ArrayList<AbstractMap.SimpleEntry<Long, String>> results = new ArrayList<>();

        long startTime;

        for (AbstractMap.SimpleEntry<String, Consumer<Integer>> entry : methods) {
            startTime = System.nanoTime();

            entry.getValue().accept(n);

            long duration = System.nanoTime() - startTime;

            results.add(new SimpleEntry<>(duration, entry.getKey()));

        }

        Collections.sort(results, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        return results.stream().map(SimpleEntry::getValue).collect(Collectors.toList());
    }

    public static List<Node> dataStructureComparisonTimesK(int n, int k) {
        ArrayList<SimpleEntry<String, Consumer<Integer>>> methods = getListOfMethods();
        ArrayList<SimpleEntry<Long, String>> first = new ArrayList<>();
        ArrayList<SimpleEntry<Long, String>> second = new ArrayList<>();
        ArrayList<Node> finalResults = new ArrayList<>();

        long startTime;

        for (AbstractMap.SimpleEntry<String, Consumer<Integer>> entry : methods) {
            startTime = System.nanoTime();

            entry.getValue().accept(n);

            long duration = System.nanoTime() - startTime;

            first.add(new AbstractMap.SimpleEntry<>(duration, entry.getKey()));
        }

        for (AbstractMap.SimpleEntry<String, Consumer<Integer>> entry : methods) {
            startTime = System.nanoTime();

            entry.getValue().accept(n * k);

            long duration = System.nanoTime() - startTime;

            second.add(new AbstractMap.SimpleEntry<>(duration, entry.getKey()));
        }

        for (int i = 0; i < methods.size(); i++) {
            float times = second.get(i).getKey() / first.get(i).getKey();
            finalResults.add(new Node(methods.get(i).getKey(), times));
        }

        return finalResults;
    }
}
