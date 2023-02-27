### CI/CD - Complete the CI/CD Pipeline (Docker-Compose, Dynamic versioning)

### Technologies used:

AWS, Jenkins, Docker, Linux, Git, Java, Maven, Docker Hub

### Project Description:

1- CI step: Increment version

2- CI step: Build artifact for Java Maven application

3- CI step: Build and push Docker image to Docker Hub automatically by webhook

4- CD step: Deploy new application version with Docker Compose

5- CD step: Commit the version update

### Installation instructions:

###### Step 1: Create EC2 instance on AWS

###### Step 2: Install Docker inside the EC2 Instance

###### Step 3: Create SSH key credentials for EC2 server on Jenkins globally

![image](image/Screenshot%202023-02-26%20at%207.17.09%20pm.png)

###### Step 4: Install SSH Agent plugin on Jenkins UI

![image](image/Screenshot%202023-02-26%20at%207.17.01%20pm.png)

###### Step 5: Create Multi-pipeline for Java project and bind it to git repository and configure the credentials for the pipeline

###### Step 6: Create Dockerfile, Docker-compose.yaml file and Jenkins file for Java project

![image](image/Screenshot%202023-02-26%20at%209.36.29%20pm.png)

###### Step 7: Update the Jenkins file on Deployment stage with Jenkins login EC2 server, copy docker-compose file to EC2 instance and run Java app as a container by docker-compose

![image](image/Screenshot%202023-02-26%20at%2010.27.05%20pm.png)

###### Step 8: Update the Jenkins ip and app running port to EC2 Instance security

![image](image/Screenshot%202023-02-26%20at%208.17.49%20pm.png)

![image](image/Screenshot%202023-02-26%20at%2010.27.15%20pm.png)

###### Step 9: Extract multiple Linux commands into a separate shell script for Improvement

1-Create a shell script file

![image](image/Screenshot%202023-02-27%20at%202.16.11%20pm.png)

![image](image/Screenshot%202023-02-27%20at%202.49.09%20pm.png)
![image](image/Screenshot%202023-02-27%20at%202.49.17%20pm.png)

![image](image/Screenshot%202023-02-27%20at%202.53.45%20pm.png)

![image](image/Screenshot%202023-02-27%20at%202.48.23%20pm.png)

###### Step 10: Refactor automatically trigger jenkins build for every push event on Git

###### Step 11: Dynamically increment java app version
![image](image/Screenshot%202023-02-27%20at%205.40.39%20pm.png)
![image](image/Screenshot%202023-02-27%20at%205.40.50%20pm.png)
![image](image/Screenshot%202023-02-27%20at%204.48.45%20pm.png)
![image](image/Screenshot%202023-02-27%20at%205.41.09%20pm.png)
![image](image/Screenshot%202023-02-27%20at%205.41.25%20pm.png)
![image](image/Screenshot%202023-02-27%20at%205.42.14%20pm.png)
