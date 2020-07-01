# MongoDB_Java
Interacting with MongoDB running in local and also Mongo Atlas cloud using Java API

Preqequisites

1. Download and install MongoDB in local machine
2. Add environment variable for MongoDB bin folder
3. Open command prompt and type mongod to start mongodb server. MongoDB server runs on port 27017
4. By default mongod runs as a background service on windows
5. Open another command prompt terminal and run mongo to start mongo shell client. It automatically connects to mongodb server on port 27017


#Cluster Mode - Replication

#create 3 mongo server cluster with 1 primary and 2 secondary
mongod --port 27017 --dbpath "C:\Users\keeth\Desktop\Dinesh-Spring-boot\mongoDB\mongo-replication\data0" --replSet "rs0" --bind_ip localhost
mongod --port 27018 --dbpath "C:\Users\keeth\Desktop\Dinesh-Spring-boot\mongoDB\mongo-replication\data1" --replSet "rs0" --bind_ip localhost
mongod --port 27019 --dbpath "C:\Users\keeth\Desktop\Dinesh-Spring-boot\mongoDB\mongo-replication\data2" --replSet "rs0" --bind_ip localhost

mongo shell client
#this wil initiate the replica set and add 27017 to the replica set
rs.initiate()
#Add the other 2 nodes in the replica set
rs.add("localhost:27018")
rs.add("localhost:27019")

#Mongo DB Atlas

1. Create a MongoDB Atlas cluster in free tier
2. Obtain the cluster endpoint for shell and Java application
3. Use the cluster endpoint to connect to the MongoDB Atlas Cluster either from shell or the application


#Steps

1. Download the MongoDB driver for the application to talk to the MongoDB database
2. Use the MongoDB localhost credentials to connect to MongoDB local
3. Use MongoDB Atlas cluster endpoint to access MongoDB in the cloud
4. Run the java program
5. Verify the data either from Mongo Atlas console or from the shell

Mongo DB Architecture

<img width="900" alt="MongoDB Architecture" src="https://github.com/dineschandgr/MongoDB_Java/blob/master/Architecture.PNG">

MongoDB Atlas Console

<img width="900" alt="MongoDB Atlas" src="https://github.com/dineschandgr/MongoDB_Java/blob/master/mongoDB_Atlas.PNG">

MongoDB Stitch

<img width="900" alt="MongoDB Stitch" src="https://github.com/dineschandgr/MongoDB_Java/blob/master/mongoDB_stitch.bmp">

