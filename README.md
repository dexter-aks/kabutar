# kabutar
messaging service

## Developer
Amit Kumar Sharma | 340amit@gmail.com

### Prerequisite
1. Install Docker - to run test containers for integration test cases
2. Install Postgress - for actual db calls 

### Build
./gradlew build

### Run
./gradlew bootRun

# Endpoints
### User
1. POST - /user/create - 
Body {"nickName":"Amit"}

### Message
=======
### Endpoints
## User
1. POST - /user/create - 
Body {"nickName":"Amit"}

## Message
1. POST /message/send - 
Body {"content": "hello", "senderNickname":"Amit", "receiverNickName": "Ravi}

2. GET /message/sent?sender=<Amit>

3. GET /message/receive?receiver=<Ravi>&sender=<Amit>
 
