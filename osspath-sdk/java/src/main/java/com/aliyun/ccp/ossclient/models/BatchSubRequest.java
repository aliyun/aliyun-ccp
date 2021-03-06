// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.ccp.ossclient.models;

import com.aliyun.tea.*;

/**
 * 
 */
public class BatchSubRequest extends TeaModel {
    @NameInMap("body")
    public java.util.Map<String, ?> body;

    @NameInMap("headers")
    public java.util.Map<String, ?> headers;

    @NameInMap("id")
    @Validation(required = true)
    public String id;

    @NameInMap("method")
    @Validation(required = true)
    public String method;

    @NameInMap("url")
    @Validation(required = true)
    public String url;

    public static BatchSubRequest build(java.util.Map<String, ?> map) throws Exception {
        BatchSubRequest self = new BatchSubRequest();
        return TeaModel.build(map, self);
    }

}
