package com.stark.springbootdynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.stark.springbootdynamodb.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDynamodbApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
		"amazon.dynamodb.endpoint=http://localhost:8000/",
		"amazon.aws.accesskey=pawan",
		"amazon.aws.secretkey=pawan" })
class SpringbootDynamodbApplicationTests {

	@ClassRule
	public static LocalDbCreationRule dynamoDB = new LocalDbCreationRule();

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private EmployeeRepository repository;

	@Before
	public void setup() throws Exception {

		try {
			dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

			CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(EmployeeRepository.class);

			tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

			amazonDynamoDB.createTable(tableRequest);
		} catch (ResourceInUseException e) {
			// Do nothing, table already created
		}

		// TODO How to handle different environments. i.e. AVOID deleting all entries in ProductInfo on table
		dynamoDBMapper.batchDelete(repository.findAll());
	}

}
