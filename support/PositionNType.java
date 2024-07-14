package support;

public class PositionNType{
    public Position getPosition() {
        return position;
    }
    public EnemyType getType() {
        return type;
    }
    private Position position;
    private EnemyType type;
    public PositionNType(Position position, EnemyType type) {
        this.position = position;
        this.type = type;
    }
}
