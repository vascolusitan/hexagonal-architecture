#!/bin/sh

# Start the Pub/Sub emulator
gcloud beta emulators pubsub start --host-port 0.0.0.0:8685 --project=hexagonal-architecture &

# Wait for the emulator to start (adjust sleep time as needed)
sleep 30

# Create Pub/Sub topics
curl -s -X PUT 'http://0.0.0.0:8685/v1/projects/hexagonal-architecture/topics/event-topic'

curl -s -X PUT 'http://0.0.0.0:8685/v1/projects/hexagonal-architecture/topics/json-topic'

# Create Pub/Sub subscriptions
curl -s -X PUT 'http://0.0.0.0:8685/v1/projects/hexagonal-architecture/subscriptions/event-topic-sub' \
    -H 'Content-Type: application/json' \
    --data '{"topic":"projects/hexagonal-architecture/topics/event-topic"}'

curl -s -X PUT 'http://0.0.0.0:8685/v1/projects/hexagonal-architecture/subscriptions/json-topic-sub' \
    -H 'Content-Type: application/json' \
    --data '{"topic":"projects/hexagonal-architecture/topics/json-topic"}'

# Keep the script running to keep the container alive
tail -f /dev/null