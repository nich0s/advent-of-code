package Day4PassportProcessing;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day4PassportProcessing {

    private static final boolean VALIDATE = true;

    public static void main(String[] args) {
        try {
            List<String> inputs = getInputs();
            Instant start = Instant.now();
            long count = 0L;
            for (String input : inputs) {
                try {
                    Passport passport = generatePassports(input);
                    if (VALIDATE) {
                        if (passport.validate()) {
                            count += 1;
                        } else {
                            System.out.println("invalid=" + passport);
                        }
                    } else {
                        count += 1;
                    }
                } catch (NullPointerException npe) {
                    // eat it. lombok is throwing npes;
                }
            }
            System.out.println((int) count);
            System.out.println(Instant.now().toEpochMilli() - start.toEpochMilli());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Passport generatePassports(String rawPassport) {
        String[] unorderedPairs = rawPassport.split(" ");
        Passport.PassportBuilder builder = Passport.builder();
        for (int i = 0; i < unorderedPairs.length; i += 1) {
            HashMap<String, Object> fieldMappings = new HashMap<>();
            for (String isolate : unorderedPairs) {
                String[] keyValue = isolate.split(":");
                fieldMappings.put(keyValue[0], keyValue[1]);
            }
            for (String key : fieldMappings.keySet()) {
                switch (key) {
                    case "byr":
                        builder.birthYear(Integer.valueOf(String.valueOf(fieldMappings.get("byr"))));
                        break;
                    case "iyr":
                        builder.issueYear(Integer.valueOf(String.valueOf(fieldMappings.get("iyr"))));
                        break;
                    case "eyr":
                        builder.expirationYear(Integer.valueOf(String.valueOf(fieldMappings.get("eyr"))));
                        break;
                    case "hgt":
                        builder.height(String.valueOf(fieldMappings.get("hgt")));
                        break;
                    case "hcl":
                        builder.hairColor(String.valueOf(fieldMappings.get("hcl")));
                        break;
                    case "ecl":
                        builder.eyeColor(String.valueOf(fieldMappings.get("ecl")));
                        break;
                    case "pid":
                        builder.id(String.valueOf(fieldMappings.get("pid")));
                        break;
                    case "cid":
                        builder.countryId(Integer.valueOf(String.valueOf(fieldMappings.get("cid"))));
                        break;
                }
            }
        }
        return builder.build();
    }

    private static List<String> getInputs() throws IOException {
        File file = new File("C:\\w\\advent-of-code\\src\\main\\java\\Day4PassportProcessing\\input");

        Scanner scanner = new Scanner(file);

        List<String> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                groups.add(String.join(" ", group));
                group.clear();
                continue;
            }
            group.add(line);
        }
        return groups;
    }
}
