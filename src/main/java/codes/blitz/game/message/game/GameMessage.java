package codes.blitz.game.message.game;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GameMessage(int currentTick, int totalTicks, GameMap map, Position currentLocation,
        Position spawnLocation, List<Integer> visitedPortIndices, List<Integer> tideSchedule, boolean isOver) {
}
