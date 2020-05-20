 pid=$(ps -ef | grep blog-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}')
if [ -n "$pid" ]; then
　kill -9 $pid;
fi
nohup java -jar blog-0.0.1-SNAPSHOT.jar > temp.out 2>&1 &