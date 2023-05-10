public class Cell {
    private boolean token;

    public Cell() {
        token = false;
    }

    public boolean hasToken() {
        return token;
    }

    public void addToken() {
        this.token = true;
    }
}
