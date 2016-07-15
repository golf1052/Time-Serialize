package com.golf1052.time;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golf1052.time.dao.Comment;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.javatic.mongo.jacksonCodec.JacksonCodecProvider;
import fr.javatic.mongo.jacksonCodec.ObjectMapperFactory;
import fr.javatic.mongo.jacksonCodec.javaTime.JavaTimeBsonModule;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.mongodb.client.model.Filters.*;

public class SaveTest {
    public static void main(String[] args) {
        ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
        objectMapper.registerModule(new JavaTimeBsonModule());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(new JacksonCodecProvider(objectMapper)));
        MongoClientOptions clientOptions = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
        MongoClient mongoClient = new MongoClient("mongo", clientOptions);
        MongoDatabase database = mongoClient.getDatabase("blog");
        MongoCollection<Comment> comments = database.getCollection("comment", Comment.class);
        Comment comment = new Comment();
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        comment.setComment("This is a test.");
        comment.setCreatedAt(now);
        comments.insertOne(comment);
        Comment dbComment = comments.find(eq("created_at", now)).first();
        System.out.println(dbComment.getComment());
        System.out.println(dbComment.getCreatedAt().toString());
    }
}
