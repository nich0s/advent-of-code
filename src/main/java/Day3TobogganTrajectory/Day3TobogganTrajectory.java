package Day3TobogganTrajectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3TobogganTrajectory {

    private static final int RIGHT_MOVES = 3;
    private static final int DOWN_MOVES = 1;

    private static final char TREE = '#';

    public static void main(String[] args) {
        try {
            // all lines
            List<String> inputs = getInputs();

            // create the map
            int ARRAY_WIDTH = inputs.get(0).length();
            int ARRAY_DEPTH = inputs.size();
            char[][] map = new char[ARRAY_DEPTH][ARRAY_WIDTH];
            int mapY = 0;
            for (String line : inputs) {
                for (int c = 0; c < line.length(); c += 1) {
                    map[mapY][c] = line.charAt(c);
                }
                mapY += 1;
            }


            int treeCounter = 0;
            int x = 0;
            for (int y = DOWN_MOVES; y < ARRAY_DEPTH; y += DOWN_MOVES) {
                x += RIGHT_MOVES;
                if (x >= ARRAY_WIDTH) {
                    x -= ARRAY_WIDTH;
                }
                char position = map[y][x];
                if (position == TREE) {
                    treeCounter += 1;
                }
            }

            System.out.println(treeCounter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getInputs() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("C:\\w\\advent-of-code\\src\\main\\java\\Day3TobogganTrajectory\\input"))) {
            return lines.collect(Collectors.toUnmodifiableList());
        }
    }
}
