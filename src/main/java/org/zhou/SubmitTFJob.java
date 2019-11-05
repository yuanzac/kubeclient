package org.zhou;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.apis.CustomObjectsApi;
import io.kubernetes.client.models.OrgKubeflowV1TFJob;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpec;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecs;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecsChief;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecsPS;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker;
import io.kubernetes.client.models.V1Container;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1PodSpec;
import io.kubernetes.client.models.V1PodTemplate;
import io.kubernetes.client.models.V1PodTemplateSpec;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubmitTFJob {

  private static final Logger LOG = LoggerFactory.getLogger(SubmitTFJob.class);

  public static void main(String[] args) throws IOException {
    if(args.length < 1) {
      LOG.error("Please specify yaml file");
    }

    OrgKubeflowV1TFJob tfJob = new OrgKubeflowV1TFJob();
    tfJob.apiVersion("kubeflow.org/v1");
    tfJob.kind("TFJob");
    V1ObjectMeta metadata = new V1ObjectMeta();
    metadata.name("dist-mnist-for-e2e-test");
    metadata.namespace("default");
    tfJob.metadata(metadata);

    OrgKubeflowV1TFJobSpec tfJobSpecs =  new OrgKubeflowV1TFJobSpec();
    OrgKubeflowV1TFJobSpecTfReplicaSpecs tfReplicaSpecTfReplicaSpecs =  new OrgKubeflowV1TFJobSpecTfReplicaSpecs();
    OrgKubeflowV1TFJobSpecTfReplicaSpecsPS tfReplicaSpecTfReplicaSpecsPS =  new OrgKubeflowV1TFJobSpecTfReplicaSpecsPS();
    OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker tfReplicaSpecTfReplicaSpecsWorker =  new OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker();

    tfReplicaSpecTfReplicaSpecsPS.replicas(2);
    V1PodTemplateSpec template = new V1PodTemplateSpec();
    V1PodSpec spec = new V1PodSpec();
    V1Container container = new V1Container();
    container.setName("tensorflow");
    container.setImage("kubeflow/tf-dist-mnist-test:1.0");
    List<V1Container> containers = new ArrayList<V1Container>();
    containers.add(container);
    spec.containers(containers);
    template.spec(spec);
    tfReplicaSpecTfReplicaSpecsPS.template(template);

    tfReplicaSpecTfReplicaSpecsWorker.replicas(4);
    V1PodTemplateSpec workerTemplate = new V1PodTemplateSpec();
    V1PodSpec workerSpec = new V1PodSpec();
    V1Container workerContainer = new V1Container();
    workerContainer.setName("tensorflow");
    workerContainer.setImage("kubeflow/tf-dist-mnist-test:1.0");
    List<V1Container> workContainers = new ArrayList<V1Container>();
    workContainers.add(workerContainer);
    workerSpec.containers(workContainers);
    workerTemplate.spec(workerSpec);
    tfReplicaSpecTfReplicaSpecsWorker.template(workerTemplate);

    tfReplicaSpecTfReplicaSpecs.worker(tfReplicaSpecTfReplicaSpecsWorker);
    tfReplicaSpecTfReplicaSpecs.PS(tfReplicaSpecTfReplicaSpecsPS);
    tfJobSpecs.tfReplicaSpecs(tfReplicaSpecTfReplicaSpecs);
    tfJob.spec(tfJobSpecs);

    System.out.println(tfJob.toString());

    ApiClient apiClient = Config.defaultClient();
    Configuration.setDefaultApiClient(apiClient);
    CustomObjectsApi customObjectsApi = new CustomObjectsApi(apiClient);

    try {
      customObjectsApi.createNamespacedCustomObject("kubeflow.org", "v1",
          "default", "tfjobs", tfJob, "true");
    } catch (ApiException e) {
      System.out.println(e.getResponseBody());
    }
  }

}