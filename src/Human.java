/**
 * Created by User on 24.10.2016.
 */
public class Human {

        private static int col=0;
        private boolean qOne;
        private boolean qTwo;
        private String firstName;
        private String lastName;
        private int age;

    public Human() {
    }

    public Human(String firstName, String lastName, int age, boolean qOne, boolean qTwo) {
        this.qOne = qOne;
        this.qTwo = qTwo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        synchronized (this)
        {
            col++;
        }
    }

    public static int getCol() {
        return col;
    }

    public boolean getqOne() {
        return qOne;
    }

    public boolean getqTwo() {
        return qTwo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
