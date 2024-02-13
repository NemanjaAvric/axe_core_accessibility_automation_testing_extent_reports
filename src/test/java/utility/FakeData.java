package utility;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FakeData {

    private static final Faker faker = new Faker();

    public static String getFakePassword() {
        return faker.internet().password();
    }

    public static String getFakeFirstName() {
        return faker.name().firstName();
    }

    public static String getFakeLastName() {
        return faker.name().lastName();
    }

    public static String getFakeEmail() {
        return faker.internet().emailAddress();
    }

    public static String getFakeDateOfBirth() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(1950, Calendar.JANUARY, 1);

        // Set the end date
        Calendar endDate = Calendar.getInstance();
        endDate.set(2005, Calendar.DECEMBER, 31);

        // Generate a random date between startDate and endDate
        Date dateOfBirth = faker.date().between(startDate.getTime(), endDate.getTime());

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dateOfBirth);
    }

    public static String getFakePhoneNumber() {
        String cellPhone = faker.phoneNumber().cellPhone();
        return cellPhone.replace('.', ' ').replace('-', ' ').replace('(', ' ').replace(')', ' ').trim().replaceAll("\\s+", "");
    }

    public static String getFakeStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String getFakeCity() {
        return faker.address().city();
    }

    public static String getFakeState() {
        return faker.address().state();
    }

    public static String getFakeCountry() {
        return faker.address().country();
    }

    public static String getFakeZipPostalCode() {
        return faker.address().zipCode();
    }
}
