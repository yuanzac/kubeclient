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
 * ListMeta describes metadata that synthetic resources must have, including lists and various status objects. A resource may have only one of {ObjectMeta, ListMeta}.
 */
@ApiModel(description = "ListMeta describes metadata that synthetic resources must have, including lists and various status objects. A resource may have only one of {ObjectMeta, ListMeta}.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-10-31T03:01:37.074Z")
public class OrgKubeflowV1TFJobListMetadata {
  @SerializedName("continue")
  private String _continue = null;

  @SerializedName("remainingItemCount")
  private Long remainingItemCount = null;

  @SerializedName("resourceVersion")
  private String resourceVersion = null;

  @SerializedName("selfLink")
  private String selfLink = null;

  public OrgKubeflowV1TFJobListMetadata _continue(String _continue) {
    this._continue = _continue;
    return this;
  }

   /**
   * continue may be set if the user set a limit on the number of items returned, and indicates that the server has more data available. The value is opaque and may be used to issue another request to the endpoint that served this list to retrieve the next set of available objects. Continuing a consistent list may not be possible if the server configuration has changed or more than a few minutes have passed. The resourceVersion field returned when using this continue value will be identical to the value in the first response, unless you have received this token from an error message.
   * @return _continue
  **/
  @ApiModelProperty(value = "continue may be set if the user set a limit on the number of items returned, and indicates that the server has more data available. The value is opaque and may be used to issue another request to the endpoint that served this list to retrieve the next set of available objects. Continuing a consistent list may not be possible if the server configuration has changed or more than a few minutes have passed. The resourceVersion field returned when using this continue value will be identical to the value in the first response, unless you have received this token from an error message.")
  public String getContinue() {
    return _continue;
  }

  public void setContinue(String _continue) {
    this._continue = _continue;
  }

  public OrgKubeflowV1TFJobListMetadata remainingItemCount(Long remainingItemCount) {
    this.remainingItemCount = remainingItemCount;
    return this;
  }

   /**
   * remainingItemCount is the number of subsequent items in the list which are not included in this list response. If the list request contained label or field selectors, then the number of remaining items is unknown and the field will be left unset and omitted during serialization. If the list is complete (either because it is not chunking or because this is the last chunk), then there are no more remaining items and this field will be left unset and omitted during serialization. Servers older than v1.15 do not set this field. The intended use of the remainingItemCount is *estimating* the size of a collection. Clients should not rely on the remainingItemCount to be set or to be exact.  This field is alpha and can be changed or removed without notice.
   * @return remainingItemCount
  **/
  @ApiModelProperty(value = "remainingItemCount is the number of subsequent items in the list which are not included in this list response. If the list request contained label or field selectors, then the number of remaining items is unknown and the field will be left unset and omitted during serialization. If the list is complete (either because it is not chunking or because this is the last chunk), then there are no more remaining items and this field will be left unset and omitted during serialization. Servers older than v1.15 do not set this field. The intended use of the remainingItemCount is *estimating* the size of a collection. Clients should not rely on the remainingItemCount to be set or to be exact.  This field is alpha and can be changed or removed without notice.")
  public Long getRemainingItemCount() {
    return remainingItemCount;
  }

  public void setRemainingItemCount(Long remainingItemCount) {
    this.remainingItemCount = remainingItemCount;
  }

  public OrgKubeflowV1TFJobListMetadata resourceVersion(String resourceVersion) {
    this.resourceVersion = resourceVersion;
    return this;
  }

   /**
   * String that identifies the server&#39;s internal version of this object that can be used by clients to determine when objects have changed. Value must be treated as opaque by clients and passed unmodified back to the server. Populated by the system. Read-only. More info: https://git.k8s.io/community/contributors/devel/api-conventions.md#concurrency-control-and-consistency
   * @return resourceVersion
  **/
  @ApiModelProperty(value = "String that identifies the server's internal version of this object that can be used by clients to determine when objects have changed. Value must be treated as opaque by clients and passed unmodified back to the server. Populated by the system. Read-only. More info: https://git.k8s.io/community/contributors/devel/api-conventions.md#concurrency-control-and-consistency")
  public String getResourceVersion() {
    return resourceVersion;
  }

  public void setResourceVersion(String resourceVersion) {
    this.resourceVersion = resourceVersion;
  }

  public OrgKubeflowV1TFJobListMetadata selfLink(String selfLink) {
    this.selfLink = selfLink;
    return this;
  }

   /**
   * selfLink is a URL representing this object. Populated by the system. Read-only.
   * @return selfLink
  **/
  @ApiModelProperty(value = "selfLink is a URL representing this object. Populated by the system. Read-only.")
  public String getSelfLink() {
    return selfLink;
  }

  public void setSelfLink(String selfLink) {
    this.selfLink = selfLink;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrgKubeflowV1TFJobListMetadata orgKubeflowV1TFJobListMetadata = (OrgKubeflowV1TFJobListMetadata) o;
    return Objects.equals(this._continue, orgKubeflowV1TFJobListMetadata._continue) &&
        Objects.equals(this.remainingItemCount, orgKubeflowV1TFJobListMetadata.remainingItemCount) &&
        Objects.equals(this.resourceVersion, orgKubeflowV1TFJobListMetadata.resourceVersion) &&
        Objects.equals(this.selfLink, orgKubeflowV1TFJobListMetadata.selfLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_continue, remainingItemCount, resourceVersion, selfLink);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrgKubeflowV1TFJobListMetadata {\n");
    
    sb.append("    _continue: ").append(toIndentedString(_continue)).append("\n");
    sb.append("    remainingItemCount: ").append(toIndentedString(remainingItemCount)).append("\n");
    sb.append("    resourceVersion: ").append(toIndentedString(resourceVersion)).append("\n");
    sb.append("    selfLink: ").append(toIndentedString(selfLink)).append("\n");
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

