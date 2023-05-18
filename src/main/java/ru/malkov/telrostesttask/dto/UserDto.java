package ru.malkov.telrostesttask.dto;

import java.time.LocalDate;

public record UserDto(String name, String surname, String secondName, LocalDate dateOfBirth,
                      String phoneNumber, String email) {

}
