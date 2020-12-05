package Day4PassportProcessing;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Value
public class Passport {

    @NonNull String id;
    Integer countryId;
    @NonNull Integer birthYear;
    @NonNull Integer issueYear;
    @NonNull Integer expirationYear;
    @NonNull String height;
    @NonNull String hairColor;
    @NonNull String eyeColor;

    public boolean validate() {
        List<String> reason = new ArrayList<>();
        if (birthYear < 1920 || birthYear > 2002) {
            reason.add("birthYear=" + birthYear);
        }

        if (issueYear < 2010 || issueYear > 2020) {
            reason.add("issueYear=" + issueYear);
        }

        if (expirationYear < 2020 || expirationYear > 2030) {
            reason.add("expirationYear=" + expirationYear);
        }

        if (!validateHeight(height)) {
            reason.add("height=" + height);
        }

        if (!validateHairColor(hairColor)) {
            reason.add("hairColor=" + hairColor);
        }

        if (!validateEyeColor(eyeColor)) {
            reason.add("eyeColor=" + eyeColor);
        }

        if (!validatePassportID(id)) {
            reason.add("id=" + id);
        }

        if (reason.size() > 0) {
            System.out.println(reason);
            return false;
        }

        return true;
    }

    private boolean validateHeight(String height) {
        Pattern pattern = Pattern.compile("(^[0-9]{1,3})(in|cm)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(height);
        if (!matcher.find()) {
            return false;
        }

        if (matcher.group(2).equals("in")) {
            if (Integer.parseInt(matcher.group(1)) < 59 || Integer.parseInt(matcher.group(1)) > 76) {
                return false;
            }
        }
        if (matcher.group(2).equals("cm")) {
            return Integer.parseInt(matcher.group(1)) >= 150 && Integer.parseInt(matcher.group(1)) <= 193;
        }
        return true;
    }

    private boolean validateHairColor(String hairColor) {
        // #123abc
        Pattern pattern = Pattern.compile("^#[0-9a-f]{6}$");
        return pattern.matcher(hairColor).find();
    }

    private boolean validateEyeColor(String eyeColor) {
        List<String> valid = new ArrayList<>() {
            {
                add("amb");
                add("blu");
                add("brn");
                add("gry");
                add("grn");
                add("hzl");
                add("oth");
            }
        };
        return valid.contains(eyeColor);
    }

    private boolean validatePassportID(String id) {
        Pattern pattern = Pattern.compile("^([0-9]{9})$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(id).find();
    }
}
