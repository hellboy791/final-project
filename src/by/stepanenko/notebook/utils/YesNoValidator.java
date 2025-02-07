package by.stepanenko.notebook.utils;

import java.util.regex.Pattern;

public class YesNoValidator implements Validator {
    private final Pattern YESNO_PATTERN = Pattern.compile("^[y|n]$");

    public YesNoValidator(){}

    @Override
    public boolean match(String value) {
        return YESNO_PATTERN.matcher(value.toLowerCase()).matches();
    }
}

