public class Cell {
    private boolean token;
    private char discColor;

    public Cell() {
        token = false;
    }

    public boolean hasDisc() {
        return token;
    }

    public void addToken() {
        this.token = true;
    }

    public void makeWhite() {
        this.discColor = 'W';
    }

    public void makeBlack() {
        this.discColor = 'B';
    }

    public char getDiscColor() {
        return discColor;
    }
    public void flipColor(){

    }
}
