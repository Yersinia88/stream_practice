public class Name {
    private final String lastName;
    private final String firstName;
    private final String secondFirstName;

    public Name(String lastName, String firstName, String secondFirstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondFirstName = secondFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondFirstName() {
        return secondFirstName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", lastName, firstName, secondFirstName);
    }
}
