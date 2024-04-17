package de.educationshare.api.v1.account;

import de.educationshare.Main;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public class ValidateToken {
        @PostMapping(value = "/api/auth/validate")
        public ResponseEntity<String> createToken(@RequestHeader("Authorization") String token) {
            if (token == null) {
                return ResponseEntity.badRequest().body("Token not found");
            }

            Session session = Main.getInstance().getHibernateUtil().getSessionFactory().openSession();
            Query query = session.createQuery("FROM TokensObject WHERE token = :token");
            query.setParameter("token", token);

            if (query.getResultList().isEmpty()) {
                return ResponseEntity.badRequest().body("Token is invalid");
            }

            return ResponseEntity.ok("Token is valid");
        }

    }
