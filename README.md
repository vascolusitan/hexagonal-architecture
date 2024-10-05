# hexagonal-architecture

Tutorial: https://simonscholz.dev/tutorials/spring-quarkus-google-pubsub

Check if topics and subscriptions were created successfully:
```
curl -X GET 'http://0.0.0.0:8685/v1/projects/hexagonal-architecture/topics'

curl -X GET 'http://0.0.0.0:8685/v1/projects/hexagonal-architecture/subscriptions'
```

To publish to a Google Cloud Pub/Sub topic, you can use the following curl command:
```
curl -X POST "http://0.0.0.0:8685/v1/projects/hexagonal-architecture/topics/json-topic:publish" \
-H "Content-Type: application/json" \
-d '{
  "messages": [
    {
      "attributes": {
        "event": "CREATE_PERSON"
      },
      "data": "eyJuYW1lIjogIlZhc2NvIEx1c2l0YW5vIiwgImFnZSI6IDI3LCAic2V4IjogIk1BU0NVTElORSIsICJtYXR1cml0eSI6ICJBRFVMVCJ9"
    }
  ]
}'
```
The actual json data is an object, but it must be base64 encoded.