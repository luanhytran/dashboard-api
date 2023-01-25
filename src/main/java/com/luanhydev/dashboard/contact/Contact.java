package com.luanhydev.dashboard.contact;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Contact")
@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
    @Id
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;

    @Column(
            name = "project",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String project;

    @Column(
            name = "avatar",
            nullable = false
    )
    private String avatar;

    @Column(
            name = "employee_id",
            nullable = false
    )
    private Integer employeeId;

    public Contact(String firstName, String lastName, String title, String department, String project, String avatar, Integer employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.department = department;
        this.project = project;
        this.avatar = avatar;
        this.employeeId = employeeId;
    }
}
