
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

### Installation
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/baptiste-jullion/OC-3-Developpez-le-back-end-en-utilisant-Java-et-Spring.git
   cd api
   ```
2. Configurez votre base de données dans `src/main/resources/application.properties`.
3. (Optionnel) Définissez les variables d'environnement pour le secret JWT et le dossier d'upload.

### Build & Run
```bash
mvn clean install
mvn spring-boot:run
```
L'API sera disponible par défaut sur `http://localhost:3001`.

### Documentation API
Swagger UI est disponible à l'adresse :
```
http://localhost:3001/swagger-ui.html
```
La documentation avec Scalar UI :
```
http://localhost:3001/docs
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

## Configuration

Modifiez `src/main/resources/application.properties` pour la base de données, le JWT et les paramètres d'upload.

Exemple :
```
spring.datasource.url=jdbc:mysql://localhost:3306/chatop_db
spring.datasource.username=chatop_db
spring.datasource.password=chatop_db
jwt.secret=1904e2f377271895eb994155723f9fe015e2352e4bd35bda7a3d591227a1bb35d621fd0ee3ffb74c7cd024590f24b98211f19c3a8212859184680fe4347ee030
jwt.expiration.ms=86400000
```
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
