package com.id.dynamointegration.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.id.dynamointegration.model.Student;

@Repository
public class DynamoRepo {
	
	@Autowired
	private DynamoDBMapper mapper;
	
	@Autowired
	private DynamoDB dyna;
	
	public void insertIntoTable(Student s){
		mapper.save(s);
	}

	public Student getOneStudent(String studentId){
		return mapper.load(Student.class,studentId);
	}
	
	public void updateStudent(Student s){
		try{
			mapper.save(s, buildDynamoSaveExp(s));
		}
		catch(ConditionalCheckFailedException e){
			
		} 
	}
	private DynamoDBSaveExpression buildDynamoSaveExp(Student s) {
		
		DynamoDBSaveExpression saveExp= new DynamoDBSaveExpression();
		Map<String,ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("studentid", new ExpectedAttributeValue(new AttributeValue(s.getStudentId()))).withComparisonOperator(ComparisonOperator.EQ);
		saveExp.setExpected(expected);
		return saveExp;
	}

	public void getListOfTables() {
		// TODO Auto-generated method stub
		   TableCollection<ListTablesResult> tables = dyna.listTables();
	        Iterator<Table> iterator = tables.iterator();

	        System.out.println("Listing table names");

	        while (iterator.hasNext()) {
	            Table table = iterator.next();
	            System.out.println(table.getTableName());
	        }
		
	}

	public void test() {
		AmazonDynamoDB client=	AmazonDynamoDBClientBuilder.standard()
		.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
		.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("key1", "keyy2")))
		
		.build();
		DynamoDB dynamoDB = new DynamoDB(client);
		
		Table table = dynamoDB.getTable("test");
		System.out.println(table.getTableName());
		KeyAttribute primaryKey = new KeyAttribute("id", "1");
		Item item2 = table.getItem(primaryKey);
		System.out.println(item2.get("c2"));
		try{
			Item item = new Item().withPrimaryKey("id","3").withString("c2", "x");
			table.putItem(item);
		}
		catch(Exception e){
			
		}
	}

}
