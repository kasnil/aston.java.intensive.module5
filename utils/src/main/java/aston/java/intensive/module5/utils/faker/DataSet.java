package aston.java.intensive.module5.utils.faker;

import java.util.Optional;

public class DataSet {
    private final DataLocale dataLocale;
    private final String defaultDelimiter = ",";

    private final Randomizer randomizer = new Randomizer();

    public DataSet(DataLocale dataLocale) {
        this.dataLocale = dataLocale;
    }

    public DataSet() {
        this(DataLocale.Ru);
    }

    public String firstName(Gender gender) {
        return "";
    }

    public String lastName(Gender gender) {
        return "";
    }

    public String password() {
        return "";
    }

    public String email() {
        var domain= getRandomArrayItem("email_domains", this.dataLocale);
        var localPart = localPartEmail();
        return localPart + "@" + domain;
    }

    private String localPartEmail() {
        var firstName = getRandomArrayItem("first_name", DataLocale.En);
        var lastName = getRandomArrayItem("last_name", DataLocale.En);

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
