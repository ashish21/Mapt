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
 * on 2014-03-31 at 12:11:36 UTC 
 * Modify at your own risk.
 */

package com.watch.project.commentsendpoint.model;

/**
 * Model definition for Comments.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the commentsendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Comments extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> data;

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
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer tdwns;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer tups;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getData() {
    return data;
  }

  /**
   * @param data data or {@code null} for none
   */
  public Comments setData(java.util.List<java.lang.String> data) {
    this.data = data;
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
  public Comments setDos(com.google.api.client.util.DateTime dos) {
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
  public Comments setFlags(java.lang.Integer flags) {
    this.flags = flags;
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
  public Comments setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getTdwns() {
    return tdwns;
  }

  /**
   * @param tdwns tdwns or {@code null} for none
   */
  public Comments setTdwns(java.lang.Integer tdwns) {
    this.tdwns = tdwns;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getTups() {
    return tups;
  }

  /**
   * @param tups tups or {@code null} for none
   */
  public Comments setTups(java.lang.Integer tups) {
    this.tups = tups;
    return this;
  }

  @Override
  public Comments set(String fieldName, Object value) {
    return (Comments) super.set(fieldName, value);
  }

  @Override
  public Comments clone() {
    return (Comments) super.clone();
  }

}