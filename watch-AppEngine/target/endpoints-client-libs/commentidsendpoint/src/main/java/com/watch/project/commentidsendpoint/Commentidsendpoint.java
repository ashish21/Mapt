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

package com.watch.project.commentidsendpoint;

/**
 * Service definition for Commentidsendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link CommentidsendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Commentidsendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.17.0-rc of the commentidsendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://publicfortress.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "commentidsendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Commentidsendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Commentidsendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getCommentIds".
   *
   * This request holds the parameters needed by the commentidsendpoint server.  After setting any
   * optional parameters, call the {@link GetCommentIds#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetCommentIds getCommentIds(java.lang.Long id) throws java.io.IOException {
    GetCommentIds result = new GetCommentIds(id);
    initialize(result);
    return result;
  }

  public class GetCommentIds extends CommentidsendpointRequest<com.watch.project.commentidsendpoint.model.CommentIds> {

    private static final String REST_PATH = "commentids/{id}";

    /**
     * Create a request for the method "getCommentIds".
     *
     * This request holds the parameters needed by the the commentidsendpoint server.  After setting
     * any optional parameters, call the {@link GetCommentIds#execute()} method to invoke the remote
     * operation. <p> {@link GetCommentIds#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetCommentIds(java.lang.Long id) {
      super(Commentidsendpoint.this, "GET", REST_PATH, null, com.watch.project.commentidsendpoint.model.CommentIds.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetCommentIds setAlt(java.lang.String alt) {
      return (GetCommentIds) super.setAlt(alt);
    }

    @Override
    public GetCommentIds setFields(java.lang.String fields) {
      return (GetCommentIds) super.setFields(fields);
    }

    @Override
    public GetCommentIds setKey(java.lang.String key) {
      return (GetCommentIds) super.setKey(key);
    }

    @Override
    public GetCommentIds setOauthToken(java.lang.String oauthToken) {
      return (GetCommentIds) super.setOauthToken(oauthToken);
    }

    @Override
    public GetCommentIds setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetCommentIds) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetCommentIds setQuotaUser(java.lang.String quotaUser) {
      return (GetCommentIds) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetCommentIds setUserIp(java.lang.String userIp) {
      return (GetCommentIds) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetCommentIds setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetCommentIds set(String parameterName, Object value) {
      return (GetCommentIds) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertCommentIds".
   *
   * This request holds the parameters needed by the commentidsendpoint server.  After setting any
   * optional parameters, call the {@link InsertCommentIds#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.watch.project.commentidsendpoint.model.CommentIds}
   * @return the request
   */
  public InsertCommentIds insertCommentIds(com.watch.project.commentidsendpoint.model.CommentIds content) throws java.io.IOException {
    InsertCommentIds result = new InsertCommentIds(content);
    initialize(result);
    return result;
  }

  public class InsertCommentIds extends CommentidsendpointRequest<com.watch.project.commentidsendpoint.model.CommentIds> {

    private static final String REST_PATH = "commentids";

    /**
     * Create a request for the method "insertCommentIds".
     *
     * This request holds the parameters needed by the the commentidsendpoint server.  After setting
     * any optional parameters, call the {@link InsertCommentIds#execute()} method to invoke the
     * remote operation. <p> {@link InsertCommentIds#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link com.watch.project.commentidsendpoint.model.CommentIds}
     * @since 1.13
     */
    protected InsertCommentIds(com.watch.project.commentidsendpoint.model.CommentIds content) {
      super(Commentidsendpoint.this, "POST", REST_PATH, content, com.watch.project.commentidsendpoint.model.CommentIds.class);
    }

    @Override
    public InsertCommentIds setAlt(java.lang.String alt) {
      return (InsertCommentIds) super.setAlt(alt);
    }

    @Override
    public InsertCommentIds setFields(java.lang.String fields) {
      return (InsertCommentIds) super.setFields(fields);
    }

    @Override
    public InsertCommentIds setKey(java.lang.String key) {
      return (InsertCommentIds) super.setKey(key);
    }

    @Override
    public InsertCommentIds setOauthToken(java.lang.String oauthToken) {
      return (InsertCommentIds) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertCommentIds setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertCommentIds) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertCommentIds setQuotaUser(java.lang.String quotaUser) {
      return (InsertCommentIds) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertCommentIds setUserIp(java.lang.String userIp) {
      return (InsertCommentIds) super.setUserIp(userIp);
    }

    @Override
    public InsertCommentIds set(String parameterName, Object value) {
      return (InsertCommentIds) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listCommentIds".
   *
   * This request holds the parameters needed by the commentidsendpoint server.  After setting any
   * optional parameters, call the {@link ListCommentIds#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListCommentIds listCommentIds() throws java.io.IOException {
    ListCommentIds result = new ListCommentIds();
    initialize(result);
    return result;
  }

  public class ListCommentIds extends CommentidsendpointRequest<com.watch.project.commentidsendpoint.model.CollectionResponseCommentIds> {

    private static final String REST_PATH = "commentids";

    /**
     * Create a request for the method "listCommentIds".
     *
     * This request holds the parameters needed by the the commentidsendpoint server.  After setting
     * any optional parameters, call the {@link ListCommentIds#execute()} method to invoke the remote
     * operation. <p> {@link ListCommentIds#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @since 1.13
     */
    protected ListCommentIds() {
      super(Commentidsendpoint.this, "GET", REST_PATH, null, com.watch.project.commentidsendpoint.model.CollectionResponseCommentIds.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListCommentIds setAlt(java.lang.String alt) {
      return (ListCommentIds) super.setAlt(alt);
    }

    @Override
    public ListCommentIds setFields(java.lang.String fields) {
      return (ListCommentIds) super.setFields(fields);
    }

    @Override
    public ListCommentIds setKey(java.lang.String key) {
      return (ListCommentIds) super.setKey(key);
    }

    @Override
    public ListCommentIds setOauthToken(java.lang.String oauthToken) {
      return (ListCommentIds) super.setOauthToken(oauthToken);
    }

    @Override
    public ListCommentIds setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListCommentIds) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListCommentIds setQuotaUser(java.lang.String quotaUser) {
      return (ListCommentIds) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListCommentIds setUserIp(java.lang.String userIp) {
      return (ListCommentIds) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListCommentIds setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListCommentIds setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListCommentIds set(String parameterName, Object value) {
      return (ListCommentIds) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeCommentIds".
   *
   * This request holds the parameters needed by the commentidsendpoint server.  After setting any
   * optional parameters, call the {@link RemoveCommentIds#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveCommentIds removeCommentIds(java.lang.Long id) throws java.io.IOException {
    RemoveCommentIds result = new RemoveCommentIds(id);
    initialize(result);
    return result;
  }

  public class RemoveCommentIds extends CommentidsendpointRequest<com.watch.project.commentidsendpoint.model.CommentIds> {

    private static final String REST_PATH = "commentids/{id}";

    /**
     * Create a request for the method "removeCommentIds".
     *
     * This request holds the parameters needed by the the commentidsendpoint server.  After setting
     * any optional parameters, call the {@link RemoveCommentIds#execute()} method to invoke the
     * remote operation. <p> {@link RemoveCommentIds#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveCommentIds(java.lang.Long id) {
      super(Commentidsendpoint.this, "DELETE", REST_PATH, null, com.watch.project.commentidsendpoint.model.CommentIds.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveCommentIds setAlt(java.lang.String alt) {
      return (RemoveCommentIds) super.setAlt(alt);
    }

    @Override
    public RemoveCommentIds setFields(java.lang.String fields) {
      return (RemoveCommentIds) super.setFields(fields);
    }

    @Override
    public RemoveCommentIds setKey(java.lang.String key) {
      return (RemoveCommentIds) super.setKey(key);
    }

    @Override
    public RemoveCommentIds setOauthToken(java.lang.String oauthToken) {
      return (RemoveCommentIds) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveCommentIds setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveCommentIds) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveCommentIds setQuotaUser(java.lang.String quotaUser) {
      return (RemoveCommentIds) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveCommentIds setUserIp(java.lang.String userIp) {
      return (RemoveCommentIds) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveCommentIds setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveCommentIds set(String parameterName, Object value) {
      return (RemoveCommentIds) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateCommentIds".
   *
   * This request holds the parameters needed by the commentidsendpoint server.  After setting any
   * optional parameters, call the {@link UpdateCommentIds#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.watch.project.commentidsendpoint.model.CommentIds}
   * @return the request
   */
  public UpdateCommentIds updateCommentIds(com.watch.project.commentidsendpoint.model.CommentIds content) throws java.io.IOException {
    UpdateCommentIds result = new UpdateCommentIds(content);
    initialize(result);
    return result;
  }

  public class UpdateCommentIds extends CommentidsendpointRequest<com.watch.project.commentidsendpoint.model.CommentIds> {

    private static final String REST_PATH = "commentids";

    /**
     * Create a request for the method "updateCommentIds".
     *
     * This request holds the parameters needed by the the commentidsendpoint server.  After setting
     * any optional parameters, call the {@link UpdateCommentIds#execute()} method to invoke the
     * remote operation. <p> {@link UpdateCommentIds#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link com.watch.project.commentidsendpoint.model.CommentIds}
     * @since 1.13
     */
    protected UpdateCommentIds(com.watch.project.commentidsendpoint.model.CommentIds content) {
      super(Commentidsendpoint.this, "PUT", REST_PATH, content, com.watch.project.commentidsendpoint.model.CommentIds.class);
    }

    @Override
    public UpdateCommentIds setAlt(java.lang.String alt) {
      return (UpdateCommentIds) super.setAlt(alt);
    }

    @Override
    public UpdateCommentIds setFields(java.lang.String fields) {
      return (UpdateCommentIds) super.setFields(fields);
    }

    @Override
    public UpdateCommentIds setKey(java.lang.String key) {
      return (UpdateCommentIds) super.setKey(key);
    }

    @Override
    public UpdateCommentIds setOauthToken(java.lang.String oauthToken) {
      return (UpdateCommentIds) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateCommentIds setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateCommentIds) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateCommentIds setQuotaUser(java.lang.String quotaUser) {
      return (UpdateCommentIds) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateCommentIds setUserIp(java.lang.String userIp) {
      return (UpdateCommentIds) super.setUserIp(userIp);
    }

    @Override
    public UpdateCommentIds set(String parameterName, Object value) {
      return (UpdateCommentIds) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Commentidsendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Commentidsendpoint}. */
    @Override
    public Commentidsendpoint build() {
      return new Commentidsendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link CommentidsendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setCommentidsendpointRequestInitializer(
        CommentidsendpointRequestInitializer commentidsendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(commentidsendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}