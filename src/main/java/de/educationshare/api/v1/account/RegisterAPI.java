package de.educationshare.api.v1.account;

import at.favre.lib.crypto.bcrypt.BCrypt;
import de.educationshare.Main;
import de.educationshare.database.objects.AccountObject;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class RegisterAPI {
    @PostMapping(value = "/api/v1/register")
    public ResponseEntity<String> register(@RequestBody String body) {
        JSONObject json = new JSONObject(body);


        if (!(json.has("username") || json.has("password"))) {
            return ResponseEntity.badRequest().body("Username or password is null");
        }

        String username = json.getString("username");
        String password = json.getString("password");


        Session session = Main.getInstance().getHibernateUtil().getSessionFactory().openSession();

        Query query = session.createQuery("FROM AccountObject WHERE username = :username");
        query.setParameter("username", username);

        if (!query.getResultList().isEmpty()) {
            return ResponseEntity.badRequest().body("Account already exists");
        }

        Date createdAt = new Date(System.currentTimeMillis());
        password = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        AccountObject account = new AccountObject(username, password, false, createdAt);

        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();

        return ResponseEntity.ok("Account created");
    }

}
