package aston.java.intensive.module5.utils.faker;

import aston.java.intensive.module5.utils.StringUtils;

import java.util.Optional;

public class DataSet {
    private final DataLocale dataLocale;
    private final String defaultDelimiter = ",";

    private final Randomizer randomizer = new Randomizer();

    public final boolean supportsGenderFirstNames;
    public final boolean supportsGenderLastNames;
    public final boolean supportsGenderMiddleNames;
    public final boolean supportsFirstNames;
    public final boolean supportsLastNames;

    public DataSet(DataLocale dataLocale) {
        this.dataLocale = dataLocale;

        this.supportsFirstNames = Database.hasKey("first_name", this.dataLocale);
        this.supportsLastNames = Database.hasKey("last_name", this.dataLocale);
        this.supportsGenderFirstNames = Database.hasKey("male_first_name", this.dataLocale) && Database.hasKey("female_first_name", this.dataLocale);
        this.supportsGenderLastNames = Database.hasKey("male_last_name", this.dataLocale) && Database.hasKey("female_last_name", this.dataLocale);
        this.supportsGenderMiddleNames = Database.hasKey("male_middle_name", this.dataLocale) && Database.hasKey("female_middle_name", this.dataLocale);
    }

    public DataSet() {
        this(DataLocale.Ru);
    }

    public String firstName(Gender gender) {
        if ((gender == null && this.supportsFirstNames) || !this.supportsGenderFirstNames) {
            return getRandomArrayItem("first_name", this.dataLocale);
        }

        if (gender == null) {
            gender = this.randomizer.enumValue(Gender.class);
        }

        if (gender == Gender.Male)
        {
            return getRandomArrayItem("male_first_name", this.dataLocale);
        }
        return getRandomArrayItem("female_first_name", this.dataLocale);
    }

    public String lastName(Gender gender) {
        if ((gender == null && this.supportsLastNames) || !this.supportsGenderLastNames) {
            return getRandomArrayItem("last_name", this.dataLocale);
        }

        if (gender == null) {
            gender = this.randomizer.enumValue(Gender.class);
        }

        if (gender == Gender.Male)
        {
            return getRandomArrayItem("male_last_name", this.dataLocale);
        }
        return getRandomArrayItem("female_last_name", this.dataLocale);
    }

    public String middleName(Gender gender) {
        if (this.supportsGenderMiddleNames) {
            if (gender == null) {
                gender = this.randomizer.enumValue(Gender.class);
            }

            if (gender == Gender.Male)
            {
                return getRandomArrayItem("male_middle_name", this.dataLocale);
            }
            return getRandomArrayItem("female_middle_name", this.dataLocale);
        }

        return "";
    }

    public String password(int length) {
        return randomizer.password(length);
    }

    public String email() {
        Gender gender = this.randomizer.enumValue(Gender.class);
        var firstName = firstName(gender);
        var lastName = lastName(gender);

        return email(firstName, lastName);
    }

    public String email(String firstName, String lastName) {
        var domain= getRandomArrayItem("email_domains", this.dataLocale);
        var localPart = localPartEmail(firstName, lastName);
        return localPart + "@" + domain;
    }

    private String localPartEmail(String firstName, String lastName) {
        var val = this.randomizer.number(3);

        String result;
        if (val == 0)
        {
            result = firstName + this.randomizer.number(100);
        }
        else if (val == 1)
        {
            result = firstName + this.randomizer.arrayElement(new String[] { ".", "_" }) + lastName;
        }
        else
        {
            result = firstName + this.randomizer.arrayElement(new String[] { ".", "_" }) + lastName + this.randomizer.number(100);
        }

        result = result.replace(" ", "");
        result = Transliterater.translate(result, this.dataLocale);
        result = StringUtils.slugify(result);
        return result;
    }

    private String getRandomArrayItem(String category, DataLocale dataLocale) {
        var arr = getArray(category, dataLocale, null);
        if (arr.length == 0)
        {
            return "";
        }

        return this.randomizer.arrayElement(arr);
    }

    private String[] getArray(String category, DataLocale dataLocale, String delimiter)
    {
        var val = get(category, dataLocale);
        if (val.isEmpty()) {
            return new String[0];
        }
        delimiter = getDelimiter(defaultDelimiter);
        String[] arr = val.get().split(delimiter);
        return arr;
    }

    private Optional<String> get(String category, DataLocale dataLocale)
    {
        return Database.get(category, getDataLocale(dataLocale));
    }

    private DataLocale getDataLocale(DataLocale dataLocale) {
        return switch (dataLocale) {
            case null -> this.dataLocale;
            default -> dataLocale;
        };
    }

    private String getDelimiter(String delimiter) {
        return switch (delimiter) {
            case null -> this.defaultDelimiter;
            default -> delimiter;
        };
    }
}
