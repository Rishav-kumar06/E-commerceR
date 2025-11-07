package com.myapp.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongoDatabase database;

    static {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/ecommerce_db");
            MongoClient mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("ecommerce_db");
            System.out.println("✅ Connected to MongoDB successfully!");
        } catch (Exception e) {
            System.err.println("❌ MongoDB Connection Failed: " + e.getMessage());
        }
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
