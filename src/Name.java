import java.util.Optional;

public class Name {
    private final String lastName;
    private final String firstName;
    private Optional<String> secondFirstName;

    public Name(String lastName, String firstName, Optional<String> secondFirstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondFirstName = secondFirstName;
    }

    public Name(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Optional<String> getSecondFirstName() {
        return secondFirstName;
    }


    public String getSecondFirstNameToString() {
        String tempSecondFirstname;
        if (secondFirstName != null) {
            return tempSecondFirstname = String.valueOf(secondFirstName);
        } else {
            return tempSecondFirstname = "";
        }
    }

    @Override
    public String toString() {
        String tempSecondFirstname = getSecondFirstNameToString();

        return String.format("%s %s %s", lastName, firstName, tempSecondFirstname);
    }
}
