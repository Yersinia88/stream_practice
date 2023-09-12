import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(lastName, name.lastName) && Objects.equals(firstName, name.firstName) && Objects.equals(secondFirstName, name.secondFirstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, secondFirstName);
    }
}
