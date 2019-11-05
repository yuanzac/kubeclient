package org.zhou;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CustomObjectsApi;
import io.kubernetes.client.models.OrgKubeflowV1TFJob;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpec;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecs;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecsPS;
import io.kubernetes.client.models.OrgKubeflowV1TFJobSpecTfReplicaSpecsWorker;
import io.kubernetes.client.models.V1Container;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1PodSpec;
import io.kubernetes.client.models.V1PodTemplateSpec;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubmitTFJobWithYaml {

  private static final Logger LOG = LoggerFactory.getLogger(SubmitTFJobWithYaml.class);

  public static void main(String[] args) throws IOException{
    if(args.length < 1) {
      LOG.error("Please specify yaml file");
    }

    File file = new File(args[0]);
    OrgKubeflowV1TFJob tfJob;
    Yaml.addModelMap("kubeflow.org/v1", "TFJob", OrgKubeflowV1TFJob.class);
    try {
      tfJob = (OrgKubeflowV1TFJob) Yaml.load(file);
    } catch(IOException e) {
      LOG.error("Exception " + e.getClass());
      LOG.error(e.getMessage(), e);
      throw e;
    }

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