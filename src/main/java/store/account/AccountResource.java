package store.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<AccountOut> create(AccountIn accountIn) {
        Account created = accountService.create(AccountParser.to(accountIn));
        return ResponseEntity.ok().body(AccountParser.to(created));
    }

    @Override
    public ResponseEntity<List<AccountOut>> findAll() {
        return ResponseEntity
            .ok()
            .body(accountService.findAll().stream().map(AccountParser::to).toList());
    }

    @Override
    public ResponseEntity<AccountOut> findByEmailAndPassword(AccountIn accountIn) {
        Account account = accountService.findByEmailAndPassword(
            accountIn.email(),
            accountIn.password()
        );
        return ResponseEntity
            .ok()
            .body(AccountParser.to(account));
    }

    @Override
    public ResponseEntity<AccountOut> whoami(String idAccount) {
        return ResponseEntity
            .ok()
            .body(AccountParser.to(accountService.findById(idAccount)));
    }
    
}
