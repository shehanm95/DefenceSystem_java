package support;

public class Position{
    private int x,y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Position(Position Position) {
        this.x = Position.getX();
        this.y = Position.getY();
    }
    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;

    
}}