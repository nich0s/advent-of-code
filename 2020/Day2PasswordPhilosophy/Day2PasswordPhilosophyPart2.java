package Day2PasswordPhilosophy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2PasswordPhilosophyPart2 {
    /**
     * --- Part Two ---
     * While it appears you validated the passwords correctly, they don't seem to be what the Official Toboggan Corporate Authentication System is expecting.
     * <p>
     * The shopkeeper suddenly realizes that he just accidentally explained the password policy rules from his old job at the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little differently.
     * <p>
     * Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
     * <p>
     * Given the same example list from above:
     * <p>
     * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
     * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
     * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
     * How many passwords are valid according to the new interpretation of the policies?
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
        char[] password = policy.getPassword().toCharArray();

        try {
            char idx1 = password[policy.getFirst()];
            char idx2 = password[policy.getSecond()];

            if (idx1 == policy.getRequired() && idx2 == policy.getRequired()) {
                return false;
            }

            if (idx1 == policy.getRequired() || idx2 == policy.getRequired()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private static PasswordPolicy parsePasswordPolicy(String raw) {
        String[] foo = raw.split(" ");
        String[] range = foo[0].split("-");
        String required = foo[1].replace(":", "");
        String password = foo[2];
        PasswordPolicy policy = new PasswordPolicy();
        policy.setFirst(Integer.parseInt(range[0]));
        policy.setSecond(Integer.parseInt(range[1]));
        policy.setRequired(required.charAt(0));
        policy.setPassword(password);
        return policy;
    }


    private static List<String> getInputs() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("C:\\w\\advent-of-code\\2020\\Day2PasswordPhilosophy\\input"))) {
            return lines.collect(Collectors.toUnmodifiableList());
        }
    }

    private static class PasswordPolicy {
        private int first;
        private int second;
        private char required;
        private String password;

        public int getFirst() {
            // non-zero indexed
            return --first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            // non-zero indexed
            return --second;
        }

        public void setSecond(int second) {
            this.second = second;
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
            return String.format("PasswordPolicy{first=%s,second=%s,requred=%s,password=%s", first, second, required, password);
        }
    }
}
