# aws_ip_filter

As a Developer I want to see all the ip ranges for selectable regions from AWS ip ranges in order to create ip filter on
security groups. 

For simplicity this version is just filtering IPv4 ip's and not IPv6. The dockerfile is kept simple, with the default
command ```docker build -t ipfilter .``` the container will be built. To run use 
```docker run -p <port-to-open-on-local-mashine>:8080 ipfilter```.

Currently, there is one GitHub action which is building a new jar file after every push to the main branch. 
Additionaly there could be a new action, which is taking the new jar to build the container (and optionally upload it
to docker hub). But for this task, this should be enough. 

Of course there can be more tests, in example the controller test can also test the output.