package ru.malkov.telrostesttask.dto;

/**
 * Data transfer object containing contact information of a
 * {@link ru.malkov.telrostesttask.entities.User} class entity
 *
 * @param phoneNumber -- user's phone number
 * @param email       -- user's email
 */
public record UserContactInfoDto(String phoneNumber, String email) {

}
