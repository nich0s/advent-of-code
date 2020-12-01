import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Day1ReportRepair {
    /**
     * <p>
     * --- Day 1: Report Repair ---
     * After saving Christmas five years in a row, you've decided to take a vacation at a nice resort on a tropical island. Surely, Christmas will go on without you.
     * The tropical island has its own currency and is entirely cash-only. The gold coins used there have a little picture of a starfish; the locals just call them stars. None of the currency exchanges seem to have heard of them, but somehow, you'll need to find fifty of these coins by the time you arrive so you can pay the deposit on your room.
     * To save your vacation, you need to get all fifty stars by December 25th.
     * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
     * Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently, something isn't quite adding up.
     * Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
     * For example, suppose your expense report contained the following:
     * 1721
     * 979
     * 366
     * 299
     * 675
     * 1456
     * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
     * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you multiply them together?
     * </p>
     */

    private static final int TARGET_VALUE = 2020;


    public static void main(String[] args) {
        try {
            System.out.println(calculate(getInputs()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int calculate(List<Integer> inputs) {
        for (int x = 0; x < inputs.size() - 1; x += 1) {
            Integer left = inputs.get(x);
            for (int y = 0; y < inputs.size() - 1; y += 1) {
                Integer right = inputs.get(y);
                int sum = left + right;
                if (sum > 2020) {
                    break;
                }
                if (sum == TARGET_VALUE) {
                    return left * right;
                }
            }
        }
        throw new RuntimeException(String.format("No values match expected target value, target=%s", TARGET_VALUE));
    }

    private static List<Integer> getInputs() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("C:\\w\\advent-of-code\\2020\\Day1ReportRepair\\input"))) {
            return lines
                    .map(Integer::valueOf)
                    .sorted()
                    .collect(Collectors.toUnmodifiableList());
        }
    }
}
