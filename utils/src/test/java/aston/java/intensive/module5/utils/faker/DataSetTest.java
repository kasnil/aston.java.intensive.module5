package aston.java.intensive.module5.utils.faker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataSetTest {
    private final DataSet dataSet = new DataSet();
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";;

    @Test
    public void testEmail()
    {
        var email = this.dataSet.email();
        assertTrue(validateEmail(email));
    }

    private static boolean validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            return email.matches(EMAIL_PATTERN);
        }
        return false;
    }
}
