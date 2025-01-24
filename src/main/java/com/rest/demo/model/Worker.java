package com.rest.demo.model;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    private String fName;
    @NotBlank(message = "Last name cannot be blank")
    private String lName;

    public Worker() {
    }

    public Worker(String email, String fName, String lName) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) && Objects.equals(email, worker.email) && Objects.equals(fName, worker.fName) && Objects.equals(lName, worker.lName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, fName, lName);
    }

    public @Email(message = "Please enter a valid email address") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Please enter a valid email address") String email) {
        this.email = email;
    }

    public @NotBlank(message = "First name cannot be blank") String getfName() {
        return fName;
    }

    public void setfName(@NotBlank(message = "First name cannot be blank") String fName) {
        this.fName = fName;
    }

    public @NotBlank(message = "Last name cannot be blank") String getlName() {
        return lName;
    }

    public void setlName(@NotBlank(message = "Last name cannot be blank") String lName) {
        this.lName = lName;
    }
}
