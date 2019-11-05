package org.zhou;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ListPod {

  private static final Logger LOG = LoggerFactory.getLogger(ListPod.class);

  public static void main(String[] args) throws IOException, ApiException {
    LOG.info("Getting pods from local k8s.");
    ApiClient client = Config.defaultClient();
    Configuration.setDefaultApiClient(client);

    CoreV1Api api = new CoreV1Api();
    V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null);
    for (V1Pod item : list.getItems()) {
      System.out.println(item.getMetadata().getName());
    }
  }

}