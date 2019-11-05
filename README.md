# kubeclient
生成tf-operator java client代码，并提交 tensorflow任务

### 生成tf-operator java client的方法

1. 修改kubeflow crd yaml文件，在openAPIV3Schema下添加参数type: object，注意，每个properties属性，都应对应一个type: object
vi tf-operator-source/src/github.com/kubeflow/tf-operator/examples/crd/crd-v1.yaml
修改后的crd-vi.yaml文件为：

```
    ...
    validation:
    openAPIV3Schema:
      type: object
      properties:
        spec:
          type: object
          properties:
            tfReplicaSpecs:
              type: object
              properties:
                # The validation works when the configuration contains
                # `Worker`, `PS` or `Chief`. Otherwise it will not be validated.
                Worker:
                  type: object
                  properties:
                    replicas:
                      type: integer
                      minimum: 1
                PS:
                  type: object
                  properties:
                    replicas:
                      type: integer
                      minimum: 1
                Chief:
                  type: object
                  properties:
                    replicas:
                      type: integer
                      minimum: 1
                      maximum: 1
```

注意：添加type: object的原因是，没有type: object的schema，无法通过openapi生成java 代码，具体原因参见https://github.com/kubernetes/kubernetes/issues/81445

2. 使用修改后的crd-v1.yaml创建kubeflow crd：
```
kubectl create -f tf-operator-source/src/github.com/kubeflow/tf-operator/examples/crd/crd-v1.yaml
```

3. 将openapi接口导出为swagger文件
```
kubectl get --raw="/openapi/v2" > /tmp/swagger
```

4. 在/tmp/java路径下生成java代码：
```
docker run -i --rm yue9944882/java-model-gen -p com.example < /tmp/swagger | tar -xzf - -C /tmp/
```
其中，可以修改yue9944882/java-model-gen镜像内的maven配置，指向较快的镜像库.

5. 查看生成的TFJob代码
```
/tmp/java/src/main/java/io/kubernetes/client/models# ls -l | grep TF
-rw-r--r-- 1 root root  5337 Oct 31 15:58 OrgKubeflowV1TFJob.java
-rw-r--r-- 1 root root  5754 Oct 31 15:58 OrgKubeflowV1TFJobList.java
-rw-r--r-- 1 root root  8310 Oct 31 15:58 OrgKubeflowV1TFJobListMetadata.java
-rw-r--r-- 1 root root  2635 Oct 31 15:58 OrgKubeflowV1TFJobSpec.java
-rw-r--r-- 1 root root  2516 Oct 31 15:58 OrgKubeflowV1TFJobSpecTfReplicaSpecsChief.java
-rw-r--r-- 1 root root  3966 Oct 31 15:58 OrgKubeflowV1TFJobSpecTfReplicaSpecs.java
-rw-r--r-- 1 root root  2476 Oct 31 15:58 OrgKubeflowV1TFJobSpecTfReplicaSpecsPS.java
```

6. 由于kubeflow在任务创建的yaml文件中，还使用了pod template，所以还需手工添加V1PodTemplateSpec参数到OrgKubeflowV1TFJobSpecTfReplicaSpecsPS和OrgKubeflowV1TFJobSpecTfReplicaSpecsChief中。同时，参照参数replicas，为V1PodTemplateSpec添加相应的方法

7. 由于kubeflow crd中定义ps和worker的参数完全相同，所以只会生成一个class OrgKubeflowV1TFJobSpecTfReplicaSpecsPS，为了便于区分，我们拷贝OrgKubeflowV1TFJobSpecTfReplicaSpecsPS生成另外一个class OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker，注意OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker内所有的PS都要改成Worker

8. OrgKubeflowV1TFJobSpecTfReplicaSpecs内worker的参数类型，也由OrgKubeflowV1TFJobSpecTfReplicaSpecsPS改为 OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker

9. 在kubernetes集群中，删除刚才创建的crd，使用原来未修改的crd yaml文件，再次创建crd。原因是添加type: object参数后，crd无法再识别pod template参数，会导致任务失败。所以需要使用原来的crd文件重新生成crd定义

10. tfjob的yaml文件内的参数worker, chief必须为全部小写或者全部大写，否则SnakeYAML将无法解析。例如Worker, Chief会导致yaml解析失败 