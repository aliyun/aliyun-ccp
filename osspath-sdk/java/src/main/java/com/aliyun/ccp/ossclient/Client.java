// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.ccp.ossclient;

import com.aliyun.tea.*;
import com.aliyun.ccp.ossclient.models.*;

public class Client {

    public String _domainId;
    public String _endpoint;
    public String _protocol;
    public String _nickname;
    public String _userAgent;
    public com.aliyun.credentials.Client _credential;
    public com.aliyun.ccpcredentials.Client _accessTokenCredential;
    public Client(Config config) throws Exception {
        if (com.aliyun.teautil.Common.isUnset(TeaModel.buildMap(config))) {
            throw new TeaException(TeaConverter.buildMap(
                new TeaPair("name", "ParameterMissing"),
                new TeaPair("message", "'config' can not be unset")
            ));
        }

        if (com.aliyun.teautil.Common.empty(config.domainId)) {
            throw new TeaException(TeaConverter.buildMap(
                new TeaPair("name", "ParameterMissing"),
                new TeaPair("message", "'config.domainId' can not be empty")
            ));
        }

        if (!com.aliyun.teautil.Common.empty(config.accessToken) || !com.aliyun.teautil.Common.empty(config.refreshToken)) {
            com.aliyun.ccpcredentials.models.Config accessConfig = com.aliyun.ccpcredentials.models.Config.build(TeaConverter.buildMap(
                new TeaPair("accessToken", config.accessToken),
                new TeaPair("endpoint", config.endpoint),
                new TeaPair("domainId", config.domainId),
                new TeaPair("clientId", config.clientId),
                new TeaPair("refreshToken", config.refreshToken),
                new TeaPair("clientSecret", config.clientSecret),
                new TeaPair("expireTime", config.expireTime)
            ));
            this._accessTokenCredential = new com.aliyun.ccpcredentials.Client(accessConfig);
        }

        if (!com.aliyun.teautil.Common.empty(config.accessKeyId)) {
            if (com.aliyun.teautil.Common.empty(config.credentialType)) {
                config.credentialType = "access_key";
            }

            com.aliyun.credentials.models.Config credentialConfig = com.aliyun.credentials.models.Config.build(TeaConverter.buildMap(
                new TeaPair("accessKeyId", config.accessKeyId),
                new TeaPair("type", config.credentialType),
                new TeaPair("accessKeySecret", config.accessKeySecret),
                new TeaPair("securityToken", config.securityToken)
            ));
            this._credential = new com.aliyun.credentials.Client(credentialConfig);
        }

        this._endpoint = config.endpoint;
        this._protocol = config.protocol;
        this._domainId = config.domainId;
        this._userAgent = config.userAgent;
        this._nickname = config.nickname;
    }

    public AccountAccessTokenResponse cancelLink(CancelLinkRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/cancel_link");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccountAccessTokenResponse confirmLink(ConfirmLinkRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/confirm_link");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public void changePassword(DefaultChangePasswordRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/default/change_password");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 204)) {
                    return ;
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public void setPassword(DefaultSetPasswordRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/default/set_password");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 204)) {
                    return ;
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccountAccessTokenResponse getAccessTokenByLinkInfo(GetAccessTokenByLinkInfoRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/get_access_token_by_link_info");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public Captcha getCaptcha(GetCaptchaRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/get_captcha");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new Captcha());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public LinkInfoResponse getLinkInfo(GetByLinkInfoRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/get_link_info");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new LinkInfoResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public LinkInfoListResponse getLinkInfoByUserId(GetLinkInfoByUserIDRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/get_link_info_by_user_id");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new LinkInfoListResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccountAccessTokenResponse link(AccountLinkRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/link");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public MobileCheckExistResponse checkExist(MobileCheckExistRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/mobile/check_exist");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new MobileCheckExistResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccountAccessTokenResponse login(MobileLoginRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/mobile/login");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccountAccessTokenResponse register(MobileRegisterRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/mobile/register");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public MobileSendSmsCodeResponse mobileSendSmsCode(MobileSendSmsCodeRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/mobile/send_sms_code");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new MobileSendSmsCodeResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccountAccessTokenResponse token(TokenRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/account/token");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".auth.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccountAccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListStoresResponse adminListStores(AdminListStoresRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/domain/list_stores");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListStoresResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public AccessTokenResponse getUserAccessToken(GetUserAccessTokenRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/get_access_token");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new AccessTokenResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public CreateDriveResponse createDrive(CreateDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/create");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 201)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new CreateDriveResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public void deleteDrive(DeleteDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/delete");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 204)) {
                    return ;
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public GetDriveResponse getDrive(GetDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/get");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new GetDriveResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public GetDriveResponse getDefaultDrive(GetDefaultDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/get_default_drive");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new GetDriveResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListDriveResponse listDrives(ListDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/list");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListDriveResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListDriveResponse listMyDrives(ListMyDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/list_my_drives");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListDriveResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public UpdateDriveResponse updateDrive(UpdateDriveRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/drive/update");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new UpdateDriveResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSCompleteFileResponse completeFile(OSSCompleteFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/complete");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSCompleteFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSCopyFileResponse copyFile(OSSCopyFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/copy");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 201)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSCopyFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSCreateFileResponse createFile(OSSCreateFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/create");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 201)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSCreateFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public void deleteFile(OSSDeleteFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/delete");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 204)) {
                    return ;
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSGetFileResponse getFile(OSSGetFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/get");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSGetFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSGetDownloadUrlResponse getDownloadUrl(OSSGetDownloadUrlRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/get_download_url");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSGetDownloadUrlResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSGetSecureUrlResponse getSecureUrl(OSSGetSecureUrlRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/get_secure_url");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSGetSecureUrlResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSGetUploadUrlResponse getUploadUrl(OSSGetUploadUrlRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/get_upload_url");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSGetUploadUrlResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSListFileResponse listFile(OSSListFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/list");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSListFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSListUploadedPartResponse listUploadedParts(OSSListUploadedPartRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/list_uploaded_parts");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSListUploadedPartResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public OSSMoveFileResponse moveFile(OSSMoveFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/file/move");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new OSSMoveFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public CreateShareResponse createShare(CreateShareRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/share/create");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 201)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new CreateShareResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public void deleteShare(DeleteShareRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/share/delete");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 204)) {
                    return ;
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public GetShareResponse getShare(GetShareRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/share/get");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new GetShareResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListShareResponse listShare(ListShareRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/share/list");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListShareResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public UpdateShareResponse updateShare(UpdateShareRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/share/update");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new UpdateShareResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListStoreFileResponse listStorefile(ListStoreFileRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/osspath/store_file/list");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListStoreFileResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public CreateUserResponse createUser(CreateUserRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/create");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 201)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new CreateUserResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public void deleteUser(DeleteUserRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/delete");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 204)) {
                    return ;
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public GetUserResponse getUser(GetUserRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/get");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new GetUserResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListUserResponse listUsers(ListUserRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/list");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListUserResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public ListUserResponse searchUser(SearchUserRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/search");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new ListUserResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public UpdateUserResponse updateUser(UpdateUserRequest request, RuntimeOptions runtime) throws Exception {
        TeaModel.validateParams(request, "request");
        TeaModel.validateParams(runtime, "runtime");
        java.util.Map<String, Object> runtime_ = TeaConverter.buildMap(
            new TeaPair("timeouted", "retry"),
            new TeaPair("readTimeout", runtime.readTimeout),
            new TeaPair("connectTimeout", runtime.connectTimeout),
            new TeaPair("localAddr", runtime.localAddr),
            new TeaPair("httpProxy", runtime.httpProxy),
            new TeaPair("httpsProxy", runtime.httpsProxy),
            new TeaPair("noProxy", runtime.noProxy),
            new TeaPair("maxIdleConns", runtime.maxIdleConns),
            new TeaPair("socks5Proxy", runtime.socks5Proxy),
            new TeaPair("socks5NetWork", runtime.socks5NetWork),
            new TeaPair("retry", TeaConverter.buildMap(
                new TeaPair("retryable", runtime.autoretry),
                new TeaPair("maxAttempts", com.aliyun.teautil.Common.defaultNumber(runtime.maxAttempts, 3))
            )),
            new TeaPair("backoff", TeaConverter.buildMap(
                new TeaPair("policy", com.aliyun.teautil.Common.defaultString(runtime.backoffPolicy, "no")),
                new TeaPair("period", com.aliyun.teautil.Common.defaultNumber(runtime.backoffPeriod, 1))
            )),
            new TeaPair("ignoreSSL", runtime.ignoreSSL)
        );

        TeaRequest _lastRequest = null;
        long _now = System.currentTimeMillis();
        int _retryTimes = 0;
        while (Tea.allowRetry((java.util.Map<String, Object>) runtime_.get("retry"), _retryTimes, _now)) {
            if (_retryTimes > 0) {
                int backoffTime = Tea.getBackoffTime(runtime_.get("backoff"), _retryTimes);
                if (backoffTime > 0) {
                    Tea.sleep(backoffTime);
                }
            }
            _retryTimes = _retryTimes + 1;
            try {
                TeaRequest request_ = new TeaRequest();
                String accesskeyId = this.getAccessKeyId();
                String accessKeySecret = this.getAccessKeySecret();
                String securityToken = this.getAccessKeySecret();
                String accessToken = this.getAccessToken();
                request_.protocol = com.aliyun.teautil.Common.defaultString(_protocol, "https");
                request_.method = "POST";
                request_.pathname = this.getPathname(_nickname, "/v2/user/update");
                request_.headers = com.aliyun.teautil.Common.stringifyMapValue(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("user-agent", this.getUserAgent()),
                        new TeaPair("host", com.aliyun.teautil.Common.defaultString(_endpoint, "" + _domainId + ".api.alicloudccp.com")),
                        new TeaPair("content-type", "application/json; charset=utf-8")
                    ),
                    request.header
                ));
                if (!com.aliyun.teautil.Common.empty(accessToken)) {
                    request_.headers.put("authorization", "Bearer " + accessToken + "");
                } else if (!com.aliyun.teautil.Common.empty(accesskeyId) && !com.aliyun.teautil.Common.empty(accessKeySecret)) {
                    if (!com.aliyun.teautil.Common.empty(securityToken)) {
                        request_.headers.put("x-acs-security-token", securityToken);
                    }

                    request_.headers.put("date", com.aliyun.teautil.Common.getDateUTCString());
                    request_.headers.put("accept", "application/json");
                    request_.headers.put("x-acs-signature-method", "HMAC-SHA1");
                    request_.headers.put("x-acs-signature-version", "1.0");
                    String stringToSign = com.aliyun.roautil.Client.getStringToSign(request_);
                    request_.headers.put("authorization", "acs " + accesskeyId + ":" + com.aliyun.roautil.Client.getSignature(stringToSign, accessKeySecret) + "");
                }

                request_.body = Tea.toReadable(com.aliyun.teautil.Common.toJSONString(com.aliyun.roautil.Client.deleteSpecialKey(TeaModel.buildMap(request), "header")));
                _lastRequest = request_;
                TeaResponse response_ = Tea.doAction(request_, runtime_);

                java.util.Map<String, Object> respMap = null;
                Object obj = null;
                if (com.aliyun.teautil.Common.equalNumber(response_.statusCode, 200)) {
                    obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                    respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                    return TeaModel.toModel(TeaConverter.merge(Object.class,
                        TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id"))
                        ),
                        respMap
                    ), new UpdateUserResponse());
                }

                if (!com.aliyun.teautil.Common.empty(response_.headers.get("x-ca-error-message"))) {
                    throw new TeaException(TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        )),
                        new TeaPair("message", response_.headers.get("x-ca-error-message"))
                    ));
                }

                obj = com.aliyun.teautil.Common.readAsJSON(response_.body);
                respMap = com.aliyun.teautil.Common.assertAsMap(obj);
                throw new TeaException(TeaConverter.merge(Object.class,
                    TeaConverter.buildMap(
                        new TeaPair("data", TeaConverter.buildMap(
                            new TeaPair("requestId", response_.headers.get("x-ca-request-id")),
                            new TeaPair("statusCode", response_.statusCode),
                            new TeaPair("statusMessage", response_.statusMessage)
                        ))
                    ),
                    respMap
                ));
            } catch (Exception e) {
                if (Tea.isRetryable(e)) {
                    continue;
                }
                throw e;
            }
        }

        throw new TeaUnretryableException(_lastRequest);
    }

    public String getPathname(String nickname, String path) throws Exception {
        if (com.aliyun.teautil.Common.empty(nickname)) {
            return path;
        }

        return "/" + nickname + "" + path + "";
    }

    public void setExpireTime(String expireTime) throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_accessTokenCredential)) {
            return ;
        }

        _accessTokenCredential.setExpireTime(expireTime);
    }

    public String getExpireTime() throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_accessTokenCredential)) {
            return "";
        }

        String expireTime = _accessTokenCredential.getExpireTime();
        return expireTime;
    }

    public void setUserAgent(String userAgent) throws Exception {
        this._userAgent = userAgent;
    }

    public void appendUserAgent(String userAgent) throws Exception {
        this._userAgent = "" + _userAgent + " " + userAgent + "";
    }

    public String getUserAgent() throws Exception {
        String userAgent = com.aliyun.teautil.Common.getUserAgent(_userAgent);
        return userAgent;
    }

    public void setRefreshToken(String token) throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_accessTokenCredential)) {
            return ;
        }

        _accessTokenCredential.setRefreshToken(token);
    }

    public String getRefreshToken() throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_accessTokenCredential)) {
            return "";
        }

        String token = _accessTokenCredential.getRefreshToken();
        return token;
    }

    public void setAccessToken(String token) throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_accessTokenCredential)) {
            return ;
        }

        _accessTokenCredential.setAccessToken(token);
    }

    public String getAccessToken() throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_accessTokenCredential)) {
            return "";
        }

        String token = _accessTokenCredential.getAccessToken();
        return token;
    }

    public String getAccessKeyId() throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_credential)) {
            return "";
        }

        String accessKeyId = _credential.getAccessKeyId();
        return accessKeyId;
    }

    public String getAccessKeySecret() throws Exception {
        if (com.aliyun.teautil.Common.isUnset(_credential)) {
            return "";
        }

        String secret = _credential.getAccessKeySecret();
        return secret;
    }
}
