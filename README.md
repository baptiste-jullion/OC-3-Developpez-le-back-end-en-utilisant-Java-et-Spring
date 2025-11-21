
# Chatop API - Backend

## Description

Chatop API est un backend Spring Boot pour une plateforme de location. Il fournit des endpoints REST pour l'authentification des utilisateurs, la gestion des locations, la messagerie et la gestion des fichiers. Ce backend est conçu pour fonctionner avec un frontend Angular et utilise l'authentification JWT.

## Fonctionnalités
- Inscription, connexion et récupération du profil utilisateur
- Opérations CRUD sur les locations
- Messagerie entre utilisateurs
- Upload et téléchargement de fichiers (images)
- Authentification et autorisation JWT
- Documentation Swagger/OpenAPI

## Technologies
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- MapStruct
- Swagger (springdoc-openapi) & Scalar

## Prise en main

### Prérequis
- Java 17 ou supérieur
- Maven

### Configuration par variables d'environnement (recommandé)
Pour la sécurité et la portabilité, configurez les paramètres sensibles (base de données, JWT, port) via des variables d'environnement.

Exemple de variables à définir avant de lancer l'application :
```bash
export DB_URL=<votre_url_db>
export DB_USERNAME=<votre_utilisateur_db>
export DB_PASSWORD=<votre_mot_de_passe_db>
export JWT_SECRET=<votre_secret_jwt>
export JWT_EXPIRATION_MS=<expiration_jwt_en_ms>
export SERVER_PORT=<port_du_serveur>
```

### Installation
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/baptiste-jullion/OC-3-Developpez-le-back-end-en-utilisant-Java-et-Spring.git
   cd api
   ```
2. Configurez vos variables d'environnement comme indiqué ci-dessus (recommandé) ou modifiez les fichiers de configuration dans `src/main/resources` (non recommandé pour les secrets).

### Build & Run
```bash
mvn clean install
mvn spring-boot:run
```
L'API sera disponible par défaut sur `http://localhost:${SERVER_PORT}`.

### Documentation API
Swagger UI est disponible à l'adresse :
```
http://localhost:${SERVER_PORT}/swagger-ui.html
```
La documentation avec Scalar UI :
```
http://localhost:${SERVER_PORT}/docs
```

## Principaux Endpoints

### Authentification
- `POST /api/auth/register` — Inscription d'un nouvel utilisateur
- `POST /api/auth/login` — Connexion et récupération du JWT
- `GET /api/auth/me` — Récupérer les infos de l'utilisateur connecté

### Locations
- `GET /api/rentals` — Liste des locations
- `GET /api/rentals/{id}` — Détail d'une location
- `POST /api/rentals` — Création d'une location (multipart/form-data)
- `PUT /api/rentals/{id}` — Modification d'une location
- `DELETE /api/rentals/{id}` — Suppression d'une location

### Messages
- `POST /api/messages` — Envoyer un message

### Utilisateurs
- `GET /api/user/{id}` — Récupérer un utilisateur par son ID

### Fichiers
- `GET /api/files/{filename}` — Télécharger une image / y accéder via URL

## Création de la base de données MySQL

Avant de lancer l'application, créez la base de données et l'utilisateur MySQL avec les commandes suivantes :

```sql
-- Crée la base de données
CREATE DATABASE chatop_db;

-- Crée un utilisateur et lui donne les droits sur la base de données
CREATE USER 'chatop_db'@'localhost' IDENTIFIED BY 'chatop_db';
GRANT ALL PRIVILEGES ON chatop_db.* TO 'chatop_db'@'localhost';
FLUSH PRIVILEGES;
```
