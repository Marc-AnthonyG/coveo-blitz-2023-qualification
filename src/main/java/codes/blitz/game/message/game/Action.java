package codes.blitz.game.message.game;

public record Action(ActionKind kind, Position position, Direction direction) {

    public Action(ActionKind kind)
    {
        this(kind, null, null);
    }

    public Action(ActionKind kind, Position position)
    {
        this(kind, position, null);
    }

    public Action(ActionKind kind, Direction direction)
    {
        this(kind, null, direction);
    }
}
