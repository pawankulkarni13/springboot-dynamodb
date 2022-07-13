#### Configure AWS CLI to run the configuration needed for DynamoDB
    https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Tools.CLI.html
    https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

### Commands to Run DynamoDB Locally

Execute below in the directory where the DynamoDB local jar is extracted.

    java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

    aws dynamodb list-tables --endpoint-url http://localhost:8000

#### DynamoDB Local
- Needs Java Runtime greater than equal to V8.
- It can be easily run thru a docker if local setup supports it.


### Further Reading
- Why DynamoDB ?
- Use cases
- DynamoDB Streams
- Comparison with other NoSQL DBs
- CRUD was target - Update and Delete can be added.