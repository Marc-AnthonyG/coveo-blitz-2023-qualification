package codes.blitz.game.message.bot;

import java.util.List;

import codes.blitz.game.message.MessageType;
import codes.blitz.game.message.game.Action;

public record BotCommandMessage(MessageType type, Action action, Integer tick) {
    public BotCommandMessage(Action action, Integer tick)
    {
        this(MessageType.COMMAND, action, tick);
    }
}
