// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.ccp.ossclient.models;

import com.aliyun.tea.*;

public class OSSListUploadedPartResponse extends TeaModel {
    @NameInMap("requestId")
    public String requestId;

    @NameInMap("file_path")
    public String filePath;

    @NameInMap("next_part_number_marker")
    public String nextPartNumberMarker;

    @NameInMap("upload_id")
    public String uploadId;

    @NameInMap("uploaded_parts")
    public java.util.List<UploadPartInfo> uploadedParts;

    public static OSSListUploadedPartResponse build(java.util.Map<String, ?> map) throws Exception {
        OSSListUploadedPartResponse self = new OSSListUploadedPartResponse();
        return TeaModel.build(map, self);
    }

}
