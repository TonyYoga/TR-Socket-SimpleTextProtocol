package app.core;

import java.util.Objects;

public class Person {
    private int id;
    private String fName;
    private String lName;
    private String phone;

    private Person(int id, String fName, String lName, String phone) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id == person.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    public String toStrData() {
        return id + "," + fName + "," + lName + "," + phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static Person fromString(String data) {
        Objects.requireNonNull(data);
        String[] args = data.split(",");
        if (args.length != 4) {
            throw new IllegalArgumentException("Missing data filelds!");
        }
        return new Person(Integer.parseInt(args[0]), args[1], args[2], args[3]);
    }

    public static Person of(int id, String fName, String lName, String phone) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(fName);
        Objects.requireNonNull(lName);
        Objects.requireNonNull(phone);
        return new Person(id, fName, lName, phone);
    }

}
