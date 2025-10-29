package models;

import java.time.LocalDate;
import java.util.UUID;

import models.Interfaces.Identification;

public class UserModel implements Identification<UUID> {

    private UUID id;
    private String name;
    private String email;
    private LocalDate birthDate;

    public UserModel(String name, String email, LocalDate birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("""
            {
                id: %s
                name: %s
                email: %s
                birthDate: %s
            }""", id, name, email, birthDate);
    }
}
