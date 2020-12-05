package Day3TobogganTrajectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3TobogganTrajectoryPart2 {

    private static final char TREE = '#';

    public static void main(String[] args) {
        try {
            char[][] map = generateMap(getInputs());

            Instant start = Instant.now();
            // down, right
            int[][] routes = new int[][]{
                    {1, 1},
                    {1, 3},
                    {1, 5},
                    {1, 7},
                    {2, 1}
            };

            System.out.println(checkRoutes(map, routes));
            System.out.println(Instant.now().toEpochMilli() - start.toEpochMilli());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Long checkRoutes(char[][] map, int[][] routeRules) {
        List<Long> trees = new ArrayList<>(){};
        for (int[] routeRule : routeRules) {
            int treeCounter = 0;
            int down = routeRule[0];
            int right = routeRule[1];
            int depth = map.length;
            int width = map[0].length;
            int x = 0;
            for (int y = down; y < depth; y += down) {
                x += right;
                if (x >= width) {
                    x -= width;
                }
                char position = map[y][x];
                if (position == TREE) {
                    treeCounter += 1;
                }
            }
            trees.add((long) treeCounter);
        }
        return trees.stream().reduce(1L, (a, b) -> a * b);
    }

    private static char[][] generateMap(List<String> template) {
        int width = template.get(0).length();
        int depth = template.size();
        char[][] map = new char[depth][width];
        int y = 0;
        for (String line : template) {
            for (int x = 0; x < line.length(); x += 1) {
                map[y][x] = line.charAt(x);
            }
            y += 1;
        }
        return map;
    }

    private static List<String> getInputs() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("C:\\w\\advent-of-code\\src\\main\\java\\Day3TobogganTrajectory\\input"))) {
            return lines.collect(Collectors.toUnmodifiableList());
        }
    }
}
