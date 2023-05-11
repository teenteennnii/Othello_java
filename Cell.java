public class Cell {
    private char discColor = ' ';

    public void makeWhite() {
        this.discColor = 'W';
    }

    public void makeBlack() {
        this.discColor = 'B';
    }

    public char getDiscColor() {
        return discColor;
    }

    public void flipColor() {
        if (this.getDiscColor() == 'W') {
            this.discColor = 'B';
        }
        else {
            this.discColor = 'W';
        }
    }
}
