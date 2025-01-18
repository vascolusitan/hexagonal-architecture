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
        "messageId": "0d6dbb28-9687-4033-9263-52a361b4d268",
        "eventEntity": "PERSON",
        "eventOperation": "CREATE"
      },
      "data": "ewogICJwZXJzb25JZCI6ICIzNzczMmU4Yy1hZjY3LTRmZjktYTI4My0zY2ViNTVmY2Q0ZjIiLAogICJuYW1lIjogIlZhc2NvIEx1c2l0YW5vIiwKICAiYWdlIjogMjcsCiAgInNleCI6ICJNQVNDVUxJTkUiLAogICJtYXR1cml0eSI6ICJBRFVMVCIKfQ=="
    }
  ]
}'
```
The actual json data is an object, but it must be base64 encoded.