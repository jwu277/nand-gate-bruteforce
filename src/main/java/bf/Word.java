package bf;

public class Word {

    private boolean b0;
    private boolean b1;
    private boolean b2;
    private boolean b3;

    public Word(boolean a, boolean b, boolean c, boolean d) {
        b0 = a;
        b1 = b;
        b2 = c;
        b3 = d;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Word)) {
            return false;
        }

        Word other = (Word) obj;

        return (b0 == other.b0) && (b1 == other.b1) && (b2 == other.b2) && (b3 == other.b3);

    }

    @Override
    public int hashCode() {
        return (b0 ? 1 : 0) + (b1 ? 2 : 0) + (b2 ? 4 : 0) + (b3 ? 8 : 0);
    }

}
