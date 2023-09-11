public class Name {
    private final String lastName;
    private final String firstName;
    private String secondFirstName;

    public Name(String lastName, String firstName, String secondFirstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        try{
            if (secondFirstName != null) {
                this.secondFirstName = secondFirstName;
            } else {
                this.secondFirstName.equals("");
            }
        }catch (NullPointerException e) {
            System.out.println("NullPointerException Caught.");
        }
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

    public String getSecondFirstName() {
        return secondFirstName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", lastName, firstName, secondFirstName);
    }
}
