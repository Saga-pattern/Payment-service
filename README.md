# Payment-service

## How to build and push to docker
Clone repo
```
git clone https://github.com/Saga-pattern/Payment-service.git
```

Go to the project
```
cd Payment-service
```

Build project. Push image to docker hub
```
docker build -t stock-service .
```
```
docker tag stock-service <your-repo>:latest
```
```
docker push <your repo>:latest
```

## Event flow send and receive
### Process payment
Payment service will receive event from topic "process-payment" to make payment.

### Payment processed
After checking the total amount of the order is less than the amount in the account. Payment service will deduct money from user's account and send an event to kafka's "payment-processed" topic.

### Payment cancelled
After checking the amount in the account is not enough to pay the order. Payment service will send event to "cancel-payment" topic of kafka.
