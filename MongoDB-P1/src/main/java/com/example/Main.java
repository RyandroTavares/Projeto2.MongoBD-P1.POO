package com.example;

import java.util.function.Consumer;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.example.model.Produto;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {
        CodecRegistry pojoCodeRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), 
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient("localhost:27017", 
        MongoClientOptions.builder().codecRegistry(pojoCodeRegistry).build());   

        MongoDatabase database = mongoClient.getDatabase("exemplo").withCodecRegistry(pojoCodeRegistry);

        MongoCollection<Produto> collection = database.getCollection("Produto", Produto.class);

        // Inserindo dados!
        collection.insertOne(new Produto(1, "Arroz", 5));

        // Atualizar dados
        // collection.updateOne(new Document("_id", 1), set("descricao", "Arroz Tio João"));

        // Deletar
        // collection.deleteOne(new Document("_id", 1));

    }
}