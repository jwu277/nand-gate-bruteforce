package bf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {

    private static final int TARGET_GATES = 9;

    // 2 bit -> 7 segment decoder: 6321
    private static final Set<Word> TARGET_SET = new HashSet<Word>(Arrays.asList(
            new Word(false, false, true, false),
            // new Word(false, true, false, true),
            new Word(false, false, false, true),
            new Word(true, false, false, false),
            new Word(false, true, true, true)));

    private static NandBlob search() {

        Queue<NandBlob> queue = new LinkedList<NandBlob>();
        queue.add(new NandBlob(TARGET_GATES, TARGET_SET.size()));

        int count = 0;

        while (!queue.isEmpty()) {

            if (count % 10000 == 0) {
                System.out.println(count);
            }

            NandBlob next = queue.remove();

            if (next.containsWords(TARGET_SET)) {
                return next;
            }

            queue.addAll(next.nextGen());
            count++;

        }

        return null;

    }

    public static void main(String[] args) {
        System.out.println("Computing...");
        System.out.println(search());
    }

}
