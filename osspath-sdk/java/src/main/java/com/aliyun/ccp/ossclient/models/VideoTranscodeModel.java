// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.ccp.ossclient.models;

import com.aliyun.tea.*;

public class VideoTranscodeModel extends TeaModel {
    @NameInMap("headers")
    public java.util.Map<String, String> headers;

    @NameInMap("body")
    @Validation(required = true)
    public OSSVideoTranscodeResponse body;

    public static VideoTranscodeModel build(java.util.Map<String, ?> map) throws Exception {
        VideoTranscodeModel self = new VideoTranscodeModel();
        return TeaModel.build(map, self);
    }

}
