package de.educationshare.database.objects;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class TokensObject {
    @Id
    @Column(name = "token")
    private String token;

    @ManyToOne
    private AccountObject accountId;

    public TokensObject() {}
    public TokensObject(String token, AccountObject accountId) {
        this.token = token;
        this.accountId = accountId;
    }

}