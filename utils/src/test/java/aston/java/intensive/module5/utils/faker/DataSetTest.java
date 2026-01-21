package aston.java.intensive.module5.utils.faker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataSetTest {
    private final DataSet dataSet = new DataSet(DataLocale.Ru);
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Test
    public void testEmail()
    {
        var email = this.dataSet.email();
        assertTrue(validateEmail(email));
    }

    @Test
    public void testPassword()
    {
        var password = this.dataSet.password(12);
        assertEquals(12, password.length());
    }

    @Test
    public void testFirstName()
    {
        var firstName = this.dataSet.firstName(null);
        assertFalse(firstName.isEmpty());
    }

    @Test
    public void testLastName()
    {
        var lastName = this.dataSet.lastName(null);
        assertFalse(lastName.isEmpty());
    }

    @Test
    public void testMiddleName()
    {
        var middleName = this.dataSet.middleName(null);
        assertFalse(middleName.isEmpty());
    }

    private static boolean validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            return email.matches(EMAIL_PATTERN);
        }
        return false;
    }
}
