package store.account;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "account")
@Setter @Accessors(fluent = true)
@NoArgsConstructor
public class AccountModel {

    @Id
    @Column(name = "id_account")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_email")
    private String email;

    @Column(name = "tx_sha256")
    private String sha256;

    @Column(name = "dt_birthdate")
    private Date birthdate;

    @Column(name = "dt_creation")
    private Date creation;

    public AccountModel(Account a) {
        this.id = a.id();
        this.name = a.name();
        this.email = a.email();
        this.sha256 = a.sha256();
        this.birthdate = a.birthdate();
        this.creation = a.creation();
    }

    public Account to() {
        return Account.builder()
            .id(this.id)
            .name(this.name)
            .email(this.email)
            .sha256(this.sha256)
            .birthdate(this.birthdate)
            .creation(this.creation)
            .build();
        }

}
