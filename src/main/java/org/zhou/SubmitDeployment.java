package org.zhou;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class SubmitDeployment {

  private static final Logger LOG = LoggerFactory.getLogger(SubmitDeployment.class);

  public static void main(String[] args) throws IOException, ApiException {
    // Example yaml file can be found in $REPO_DIR/test-svc.yaml
    if(args.length < 1) {
      LOG.error("Please specify yaml file");
    }

    File file = new File(args[0]);
    V1Service yamlSvc = (V1Service) Yaml.load(file);

    // Deployment and StatefulSet is defined in apps/v1, so you should use AppsV1Api instead of
    // CoreV1API
    CoreV1Api api = new CoreV1Api();
    V1Service createResult = api.createNamespacedService("default", yamlSvc, null, null, null);

    System.out.println(createResult);
  }

}