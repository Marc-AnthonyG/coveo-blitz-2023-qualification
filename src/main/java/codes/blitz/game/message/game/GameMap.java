package codes.blitz.game.message.game;

import java.util.List;

import codes.blitz.game.message.exception.PositionOutOfMapException;

public record GameMap(int rows, int columns, int[][] topology, int depth, TideLevels tideLevels, List<Position> ports) {
}