// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.ccp.ccpclient.models;

import com.aliyun.tea.*;

public class CCPGetFileRequest extends TeaModel {
    @NameInMap("header")
    public CCPGetFileRequestHeader header;

    @NameInMap("drive_id")
    @Validation(required = true, pattern = "[0-9]+")
    public String driveId;

    @NameInMap("file_id")
    @Validation(required = true, pattern = "[a-z0-9.-_]{1,50}")
    public String fileId;

    @NameInMap("image_thumbnail_process")
    public String imageThumbnailProcess;

    @NameInMap("image_url_process")
    public String imageUrlProcess;

    public static CCPGetFileRequest build(java.util.Map<String, ?> map) throws Exception {
        CCPGetFileRequest self = new CCPGetFileRequest();
        return TeaModel.build(map, self);
    }

    public static class CCPGetFileRequestHeader extends TeaModel {
        @NameInMap("x-pds-trace-id")
        public String traceId;

        @NameInMap("x-pds-device-id")
        public String deviceId;

        public static CCPGetFileRequestHeader build(java.util.Map<String, ?> map) throws Exception {
            CCPGetFileRequestHeader self = new CCPGetFileRequestHeader();
            return TeaModel.build(map, self);
        }

    }

}
