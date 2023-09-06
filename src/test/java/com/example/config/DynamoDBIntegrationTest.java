package com.example.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DynamoDBIntegrationTest {
    Regions region = Regions.US_EAST_1; // Change this to your desired region

    private static final String TABLE_NAME = "TestTable";
    private AmazonDynamoDB dynamoDBClient;

    @BeforeAll
    public void setUp() {
        // Initialize the DynamoDB client
        dynamoDBClient = AmazonDynamoDBClient.builder().build();

        // Create a DynamoDB table for testing
        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName(TABLE_NAME)
                .withAttributeDefinitions(
                        new AttributeDefinition("ID", ScalarAttributeType.N)
                )
                .withKeySchema(
                        new KeySchemaElement("ID", KeyType.HASH)
                )
                .withProvisionedThroughput(
                        new ProvisionedThroughput(5L, 5L)
                );
        dynamoDBClient.createTable(createTableRequest);
    }

    @AfterAll
    public void tearDown() {
        // Clean up resources after testing (e.g., delete the DynamoDB table)
        dynamoDBClient.deleteTable(TABLE_NAME);
    }

    @Test
    public void testCRUDOperations() {
        // Perform your integration tests here, including CRUD operations on the DynamoDB table.
        // Use the dynamoDBClient to interact with the table.
    }
}
