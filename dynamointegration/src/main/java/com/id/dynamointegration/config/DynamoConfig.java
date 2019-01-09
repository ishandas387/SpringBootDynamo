package com.id.dynamointegration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DynamoConfig {

	@Value("${aws.access.key}")
	private String awsAccesKey;
	
	@Value("${aws.access.secretkey}")
	private String awsSecretKey;
	
	@Value("${aws.access.region}")
	private String awsRegion;
	
	@Value("${aws.access.endpoint}")
	private String awsDynamoEndpoint;
	
	@Bean
	public DynamoDBMapper mapper(){
		return new DynamoDBMapper(amazonDynamoDbConfig());
	}
	@Bean
	public DynamoDB dyna(){
		return new DynamoDB(amazonDynamoDbConfig());
	}
	
	public AmazonDynamoDB amazonDynamoDbConfig(){
		/*return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoEndpoint, "us-west-2"))
				.build();*/
		
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "us-west-2"))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccesKey, awsSecretKey)))
				.build();
	}
}
