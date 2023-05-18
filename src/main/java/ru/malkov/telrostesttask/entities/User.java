package ru.malkov.telrostesttask.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The core entity of the API, representing an abstract user.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(catalog = "TelrosTestDB", schema = "telros_user_schema", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long user_id;
    private String name;
    private String surname;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }

        if (getUser_id() != null ? !getUser_id().equals(user.getUser_id())
                : user.getUser_id() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        if (getSurname() != null ? !getSurname().equals(user.getSurname())
                : user.getSurname() != null) {
            return false;
        }
        if (getSecondName() != null ? !getSecondName().equals(user.getSecondName())
                : user.getSecondName() != null) {
            return false;
        }
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(user.getDateOfBirth())
                : user.getDateOfBirth() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) {
            return false;
        }
        return getPhoneNumber() != null ? getPhoneNumber().equals(user.getPhoneNumber())
                : user.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser_id() != null ? getUser_id().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getSecondName() != null ? getSecondName().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

