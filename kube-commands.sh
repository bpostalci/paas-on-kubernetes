# start k8s cluster
minikube start --vm-driver=hyperkit --cpus=4 --memory=16384

# create swe599-demo namespace which is required by deploy.yaml
kubectl create namespace swe599-demo

# switch to minikube's docker environment
eval $(minikube docker-env)

# build spring demo docker image
docker build -f Dockerfile -t k8s-spring-demo .

# list current images in docker environment
docker images

# deploy image to kubernetes cluster
kubectl apply -f deploy.yaml

# list pods
kubectl get pod --namespace=swe599-demo

# list services
kubectl get service --namespace=swe599-demo

# open service
minikube service -n swe599-demo k8s-spring-demo-service

# delete deployment
kubectl delete deployments --namespace=swe599-demo k8s-spring-demo

# delete service
kubectl delete services --namespace=swe599-demo k8s-spring-demo-service

# logs of pods, replace <podname> with your pod name
kubectl logs <podname> --namespace=swe599-demo -f