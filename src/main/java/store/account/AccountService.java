package store.account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findById(String id) {
        return accountRepository.findById(id).get().to();
    }

    public Account create(Account account) {
        final String pass = account.password().trim();
        if (pass.length() < 8) {
            throw new RuntimeException("Password too short!");
        }
        account.sha256(calcHash(pass));
        account.creation(new Date());
        return accountRepository.save(new AccountModel(account)).to();
    }

    public Account findByEmailAndPassword(String email, String password) {
        final String sha256 = calcHash(password);
        AccountModel m  = accountRepository.findByEmailAndSha256(email, sha256);
        return m == null ? null : m.to();
    }

    public List<Account> findAll() {
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .map(AccountModel::to)
            .toList();
    }

    /*
     * A reference to implement a nice password's hash
     * https://github.com/ByteByteGoHq/system-design-101/tree/main?tab=readme-ov-file#how-to-store-passwords-safely-in-the-database-and-how-to-validate-a-password
     */
    private String calcHash(String value) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            byte[] hash = digester.digest(value.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
