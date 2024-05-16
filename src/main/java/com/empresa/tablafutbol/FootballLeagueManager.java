package com.empresa.tablafutbol;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class FootballLeagueManager {

    private MongoDatabase database;
    private MongoCollection<Document> teamsCollection;

    public FootballLeagueManager(String connectionString, String databaseName, String collectionName) {
        this.database = MongoClients.create(connectionString).getDatabase(databaseName);
        this.teamsCollection = database.getCollection(collectionName);
    }

    // CRUD operations

    public void addTeam(String teamName, int points) {
        Document teamDocument = new Document("name", teamName).append("points", points);
        teamsCollection.insertOne(teamDocument);
    }

    public List<Document> getTeams() {
        List<Document> teams = new ArrayList<>();
        teamsCollection.find().into(teams);
        return teams;
    }

    public void updateTeam(String teamName, int newPoints) {
        Document query = new Document("name", teamName);
        Document update = new Document("$set", new Document("points", newPoints));
        teamsCollection.updateOne(query, update);
    }

    public void deleteTeam(String teamName) {
        Document query = new Document("name", teamName);
        teamsCollection.deleteOne(query);
    }

    // Search algorithm

    public Document searchTeamByName(String teamName) {
        Document query = new Document("name", teamName);
        return teamsCollection.find(query).first();
    }

    // Sorting algorithm

    public List<Document> sortTeamsByPoints() {
        return teamsCollection.find().sort(new Document("points", -1)).into(new ArrayList<>());
    }

    // Time measurement

    public long measureExecutionTime(Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
