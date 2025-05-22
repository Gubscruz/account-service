package store.account;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Builder
@Data @Accessors(fluent = true)
public class Account {

    private String id;
    private String name;
    private String email;
    private String password;
    private String sha256;
    private Date birthdate;
    private Date creation;
    
}
