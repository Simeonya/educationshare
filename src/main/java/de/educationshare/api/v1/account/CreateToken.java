package de.educationshare.api.v1.account;

import de.educationshare.Main;
import de.educationshare.database.objects.AccountObject;
import de.educationshare.database.objects.TokensObject;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateToken {
    @PostMapping(value = "/api/v1/login/token")
    public ResponseEntity<String> createToken(@RequestBody String body) {

        Main.getInstance().getLogger().info("Received request to create token");

        JSONObject json = new JSONObject(body);

        Main.getInstance().getLogger().info("Received request to create token");

        if (!(json.has("userid"))) {
            return ResponseEntity.badRequest().body("Userid not found");
        }

        Main.getInstance().getLogger().info("Userid found");

        String userid = json.getString("userid");

        Main.getInstance().getLogger().info("Userid: " + userid);

        Session session = Main.getInstance().getHibernateUtil().getSessionFactory().openSession();

        Main.getInstance().getLogger().info("Session opened");

        Query query = session.createQuery("FROM AccountObject WHERE id = :id");
        query.setParameter("id", Long.parseLong(userid));

        Main.getInstance().getLogger().info("Query created");

        if (query.getResultList().isEmpty()) {
            return ResponseEntity.badRequest().body("Account does not exists");
        }

        Main.getInstance().getLogger().info("Account exists");

        TokensObject token = new TokensObject();
        token.setToken(Main.getInstance().getRandomString(64));
        token.setAccountId((AccountObject) query.getSingleResult());

        Main.getInstance().getLogger().info("Token created");

        session.beginTransaction();
        session.save(token);
        session.getTransaction().commit();

        Main.getInstance().getLogger().info("Token saved");

        JSONObject response = new JSONObject();
        response.put("token", token.getToken());

        Main.getInstance().getLogger().info("Token sent");
        return ResponseEntity.ok(response.toString());
    }

}
