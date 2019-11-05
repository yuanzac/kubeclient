/*
 * Kubernetes
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.15.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.kubernetes.client.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * OrgKubeflowV1TFJobSpecTfReplicaSpecsChief
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-10-31T03:01:37.074Z")
public class OrgKubeflowV1TFJobSpecTfReplicaSpecsChief {
  @SerializedName("replicas")
  private Integer replicas = null;

  @SerializedName("template")
  private V1PodTemplateSpec template = null;

  public OrgKubeflowV1TFJobSpecTfReplicaSpecsChief replicas(Integer replicas) {
    this.replicas = replicas;
    return this;
  }

  public OrgKubeflowV1TFJobSpecTfReplicaSpecsChief template(V1PodTemplateSpec template) {
    this.template = template;
    return this;
  }

   /**
   * Get replicas
   * minimum: 1
   * maximum: 1
   * @return replicas
  **/
  @ApiModelProperty(value = "")
  public Integer getReplicas() {
    return replicas;
  }

  public void setReplicas(Integer replicas) {
    this.replicas = replicas;
  }

  /**
   * Template is the object that describes the pod that will be created for this TFReplicaSpecsChief
   * @return template
   **/
  @ApiModelProperty(value = "Template is the object that describes the pod that will be created for this TFReplica. RestartPolicy in PodTemplateSpec will be overide by RestartPolicy in TFReplicaSpec")
  public V1PodTemplateSpec getTemplate() {
    return template;
  }

  public void setTemplate(V1PodTemplateSpec template) {
    this.template = template;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrgKubeflowV1TFJobSpecTfReplicaSpecsChief orgKubeflowV1TFJobSpecTfReplicaSpecsChief = (OrgKubeflowV1TFJobSpecTfReplicaSpecsChief) o;
    return Objects.equals(this.replicas, orgKubeflowV1TFJobSpecTfReplicaSpecsChief.replicas)
        && Objects.equals(this.template, orgKubeflowV1TFJobSpecTfReplicaSpecsChief.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(replicas, template);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrgKubeflowV1TFJobSpecTfReplicaSpecsChief {\n");
    sb.append("    replicas: ").append(toIndentedString(replicas)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

