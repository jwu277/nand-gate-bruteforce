package bf;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NandBlob {

    // Inputs
    private NandNode in0 = new NandNode(0);
    private NandNode in1 = new NandNode(1);

    private int idCounter = 2;

    private Queue<NandNode> blob = new LinkedList<NandNode>();

    private Set<Word> words = new HashSet<Word>();

    public NandBlob() {
        blob.add(in0);
        blob.add(in1);
    }

    private NandNode searchById(int id) {

        for (NandNode n : blob) {
            if (n.id == id) {
                return n;
            }
        }

        return null;

    }

    private Word getWord(NandNode n) {

        in0.setVal(false);
        in1.setVal(false);
        boolean res0 = n.eval();

        in0.setVal(false);
        in1.setVal(true);
        boolean res1 = n.eval();

        in0.setVal(true);
        in1.setVal(false);
        boolean res2 = n.eval();

        in0.setVal(true);
        in1.setVal(true);
        boolean res3 = n.eval();

        return new Word(res0, res1, res2, res3);

    }

    private void addNode(int idL, int idR) {

        NandNode n = new NandNode(searchById(idL), searchById(idR), idCounter);

        blob.add(n);
        words.add(getWord(n));

        idCounter++;

    }

    public Set<NandBlob> nextGen() {

        Set<NandBlob> next = new HashSet<NandBlob>();

        for (NandNode m : blob) {
            for (NandNode n : blob) {
                next.add(nextBlob(m.id, n.id));
            }
        }

        return next;

    }

    private NandBlob nextBlob(int id0, int id1) {

        NandBlob next = copy();
        next.addNode(id0, id1);
        return next;
    }

    private NandBlob copy() {

        NandBlob cpy = new NandBlob();

        for (NandNode n : blob) {

            if (n.isRoot) {
                continue;
            }

            cpy.addNode(n.left.id, n.right.id);

        }

        return cpy;

    }

    public boolean containsWords(Set<Word> target) {

        for (Word w : target) {
            if (!words.contains(w)) {
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString() {

        String str = "";

        for (NandNode n : blob) {
            str += n.toString() + "\n";
        }

        return str;

    }

    private class NandNode {

        private NandNode left;
        private NandNode right;

        private boolean isRoot;

        private boolean val; // for root nodes only

        private int id;

        private NandNode(int id) {
            isRoot = true;
            this.id = id;
        }

        private NandNode(NandNode l, NandNode r, int id) {
            left = l;
            right = r;
            isRoot = false;
            this.id = id;
        }

        private void setVal(boolean v) {
            val = v;
        }

        private boolean eval() {
            if (isRoot) {
                return val;
            }
            else {
                return !(left.eval() && right.eval());
            }
        }

        @Override
        public String toString() {
            if (isRoot) {
                return id + ": ROOT";
            }
            else {
                return id + ": " + "(" + left.id + ", " + right.id + ")";
            }
        }

    }

}
