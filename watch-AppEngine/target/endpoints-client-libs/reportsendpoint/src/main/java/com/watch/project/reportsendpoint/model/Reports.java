/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-02-14 18:40:25 UTC)
 * on 2014-03-31 at 12:11:40 UTC 
 * Modify at your own risk.
 */

package com.watch.project.reportsendpoint.model;

/**
 * Model definition for Reports.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the reportsendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Reports extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String address;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime dos;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer flags;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer followers;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String info;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lat;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("long")
  private java.lang.String reportsendpointLong;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> tags;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String title;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String user;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAddress() {
    return address;
  }

  /**
   * @param address address or {@code null} for none
   */
  public Reports setAddress(java.lang.String address) {
    this.address = address;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getDos() {
    return dos;
  }

  /**
   * @param dos dos or {@code null} for none
   */
  public Reports setDos(com.google.api.client.util.DateTime dos) {
    this.dos = dos;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getFlags() {
    return flags;
  }

  /**
   * @param flags flags or {@code null} for none
   */
  public Reports setFlags(java.lang.Integer flags) {
    this.flags = flags;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getFollowers() {
    return followers;
  }

  /**
   * @param followers followers or {@code null} for none
   */
  public Reports setFollowers(java.lang.Integer followers) {
    this.followers = followers;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Reports setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getInfo() {
    return info;
  }

  /**
   * @param info info or {@code null} for none
   */
  public Reports setInfo(java.lang.String info) {
    this.info = info;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLat() {
    return lat;
  }

  /**
   * @param lat lat or {@code null} for none
   */
  public Reports setLat(java.lang.String lat) {
    this.lat = lat;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLong() {
    return reportsendpointLong;
  }

  /**
   * @param reportsendpointLong reportsendpointLong or {@code null} for none
   */
  public Reports setLong(java.lang.String reportsendpointLong) {
    this.reportsendpointLong = reportsendpointLong;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getTags() {
    return tags;
  }

  /**
   * @param tags tags or {@code null} for none
   */
  public Reports setTags(java.util.List<java.lang.String> tags) {
    this.tags = tags;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTitle() {
    return title;
  }

  /**
   * @param title title or {@code null} for none
   */
  public Reports setTitle(java.lang.String title) {
    this.title = title;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUser() {
    return user;
  }

  /**
   * @param user user or {@code null} for none
   */
  public Reports setUser(java.lang.String user) {
    this.user = user;
    return this;
  }

  @Override
  public Reports set(String fieldName, Object value) {
    return (Reports) super.set(fieldName, value);
  }

  @Override
  public Reports clone() {
    return (Reports) super.clone();
  }

}
