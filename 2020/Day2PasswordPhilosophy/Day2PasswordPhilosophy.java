package Day2PasswordPhilosophy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2PasswordPhilosophy {
    /**
     * --- Day 2: Password Philosophy ---
     * Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via toboggan.
     * <p>
     * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong with our computers; we can't log in!" You ask if you can take a look.
     * <p>
     * Their password database seems to be a little corrupted: some of the passwords wouldn't have been allowed by the Official Toboggan Corporate Policy that was in effect when they were chosen.
     * <p>
     * To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted database) and the corporate policy when that password was set.
     * <p>
     * For example, suppose you have the following list:
     * <p>
     * 1-3 a: abcde
     * 1-3 b: cdefg
     * 2-9 c: ccccccccc
     * Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
     * <p>
     * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.
     * <p>
     * How many passwords are valid according to their policies?
     */

    public static void main(String[] args) {
        try {
            System.out.println(getInputs().stream()
                    .map(raw -> parsePasswordPolicy(raw))
                    .map(policy -> validatePolicy(policy))
                    .filter(isValid -> isValid)
                    .collect(Collectors.toUnmodifiableList()).size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validatePolicy(PasswordPolicy policy) {
        if (!policy.getPassword().contains(String.valueOf(policy.getRequired()))) {
            return false;
        }

        long count = policy.getPassword().chars().filter(rune -> rune == policy.getRequired()).count();

        return count >= policy.getMin() && count <= policy.getMax();
    }

    private static PasswordPolicy parsePasswordPolicy(String raw) {
        String[] foo = raw.split(" ");
        String[] range = foo[0].split("-");
        String required = foo[1].replace(":", "");
        String password = foo[2];
        PasswordPolicy policy = new PasswordPolicy();
        policy.setMin(Integer.parseInt(range[0]));
        policy.setMax(Integer.parseInt(range[1]));
        policy.setRequired(required.charAt(0));
        policy.setPassword(password);
        return policy;
    }


    private static List<String> getInputs() throws IOException {
        // Pull a list of values from the text file
        try (Stream<String> lines = Files.lines(Path.of("C:\\w\\advent-of-code\\2020\\Day2PasswordPhilosophy\\input"))) {
            return lines.collect(Collectors.toUnmodifiableList());
        }
    }

    private static class PasswordPolicy {
        private int min;
        private int max;
        private char required;
        private String password;

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public char getRequired() {
            return required;
        }

        public void setRequired(char required) {
            this.required = required;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String toString() {
            return String.format("PasswordPolicy{min=%s,max=%s,requred=%s,password=%s", min, max, required, password);
        }
    }
}
