package bf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {

    // 2 bit -> 7 segment decoder: 6321
    private static final Set<Word> TARGET_SET = new HashSet<Word>(Arrays.asList(
            new Word(true, true, true, false),
            // new Word(true, false, false, false),
            new Word(false, true, true, true),
            new Word(true, false, true, false),
            new Word(true, true, false, true)));

    private static NandBlob search() {

        Queue<NandBlob> queue = new LinkedList<NandBlob>();
        queue.add(new NandBlob());

        while (!queue.isEmpty()) {

            NandBlob next = queue.remove();

            if (next.containsWords(TARGET_SET)) {
                return next;
            }

            queue.addAll(next.nextGen());

        }

        return null;

    }

    public static void main(String[] args) {
        System.out.println("Solution:");
        System.out.println(search());
    }

}
