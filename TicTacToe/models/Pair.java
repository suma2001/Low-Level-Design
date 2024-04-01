package TicTacToe.models;

public class Pair {
    private int first, second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    Pair values() {
        return new Pair(first, second);
    }

    @Override
    public String toString() {
        return STR."\{first}, \{second}";
    }
}
