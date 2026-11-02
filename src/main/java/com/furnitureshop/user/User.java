package com.furnitureshop.user;

import com.furnitureshop.branch.Branch;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(nullable = false)
    private boolean active = true;

    protected User() {
        // Required by JPA.
    }

    public User(
            String username,
            String passwordHash,
            Role role,
            Branch branch
    ) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public Branch getBranch() {
        return branch;
    }

    public boolean isActive() {
        return active;
    }
}