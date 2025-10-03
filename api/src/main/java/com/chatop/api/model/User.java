package com.chatop.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data // Annotation Lombok qui génère les getters, setters, toString, equals, hashCode.
@Entity // Indique à JPA que cette classe est une entité et doit être mappée à une table.
@Table(name = "USERS") // Spécifie le nom de la table dans la BDD.
public class User {

    @Id // Marque ce champ comme la clé primaire de la table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indique que la BDD doit générer la valeur de l'ID (auto-incrément).
    private Long id;

    @Column(unique = true) // Assure que chaque email dans la table est unique.
    private String email;

    private String name;

    private String password;

    @CreationTimestamp // Hibernate remplira ce champ automatiquement avec la date de création.
    @Column(name = "created_at") // Mappe ce champ à la colonne 'created_at'.
    private LocalDateTime createdAt;

    @UpdateTimestamp // Hibernate mettra ce champ à jour automatiquement à chaque modification.
    @Column(name = "updated_at") // Mappe ce champ à la colonne 'updated_at'.
    private LocalDateTime updatedAt;

}