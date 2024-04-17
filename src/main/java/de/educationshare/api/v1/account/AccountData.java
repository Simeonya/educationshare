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
public class AccountData {
    @PostMapping(value = "/api/v1/data/userid/")
    public ResponseEntity<String> getUserID(@RequestBody String body) {

        Main.getInstance().getLogger().info("Received request to get userid");

        JSONObject json = new JSONObject(body);
        if (!(json.has("username"))) {
            return ResponseEntity.badRequest().body("Username not found");
        }

        Main.getInstance().getLogger().info("Username found");

        String username = json.getString("username");
        Session session = Main.getInstance().getHibernateUtil().getSessionFactory().openSession();
        Query query = session.createQuery("FROM AccountObject WHERE username = :username");
        query.setParameter("username", username);

        Main.getInstance().getLogger().info("Query created");

        if (query.getResultList().isEmpty()) {
            return ResponseEntity.badRequest().body("Account does not exists");
        }

        Main.getInstance().getLogger().info("Account exists");

        AccountObject account = (AccountObject) query.getSingleResult();
        JSONObject response = new JSONObject();
        response.put("userid", account.getId().toString());

        Main.getInstance().getLogger().info("Userid: " + account.getId().toString());
        return ResponseEntity.ok(response.toString());
    }

}
