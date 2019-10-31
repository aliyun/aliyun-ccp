// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.ccp.ccpclient.models;

import com.aliyun.tea.*;

public class CreateAppRequest extends TeaModel {
    @NameInMap("app_name")
    @Validation(pattern = "", maxLength = 0)
    public String appName;

    @NameInMap("description")
    @Validation(pattern = "", maxLength = 0)
    public String description;

    @NameInMap("is_third_party")
    @Validation(pattern = "", maxLength = 0)
    public boolean isThirdParty;

    @NameInMap("logo")
    @Validation(pattern = "", maxLength = 0)
    public String logo;

    @NameInMap("redirect_uri")
    @Validation(pattern = "", maxLength = 0)
    public String redirectUri;

    @NameInMap("scope")
    @Validation(pattern = "", maxLength = 0)
    public String[] scope;

    @NameInMap("type")
    @Validation(pattern = "", maxLength = 0)
    public String type;

}

