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
 * on 2014-03-31 at 12:11:38 UTC 
 * Modify at your own risk.
 */

package com.watch.project.commentidsendpoint.model;

/**
 * Model definition for CommentIds.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the commentidsendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class CommentIds extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.util.List<java.lang.Long> commentsIds;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Long> getCommentsIds() {
    return commentsIds;
  }

  /**
   * @param commentsIds commentsIds or {@code null} for none
   */
  public CommentIds setCommentsIds(java.util.List<java.lang.Long> commentsIds) {
    this.commentsIds = commentsIds;
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
  public CommentIds setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  @Override
  public CommentIds set(String fieldName, Object value) {
    return (CommentIds) super.set(fieldName, value);
  }

  @Override
  public CommentIds clone() {
    return (CommentIds) super.clone();
  }

}
