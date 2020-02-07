cd task-manager
docker build -t task-manager .
docker run -d -p 8888:8080 task-manager