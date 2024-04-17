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

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class LoginAPI {
    @PostMapping(value = "/api/v1/login")
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

        if (query.getResultList().isEmpty()) {
            return ResponseEntity.badRequest().body("Account does not exists");
        }

        AccountObject account = (AccountObject) query.getSingleResult();

        if (!BCrypt.verifyer().verify(password.toCharArray(), account.getPassword()).verified) {
            return ResponseEntity.badRequest().body("Password is incorrect");
        }

        return ResponseEntity.ok("Logged in successfully");
    }

}
