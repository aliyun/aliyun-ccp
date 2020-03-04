// This file is auto-generated, don't edit it. Thanks.

using System;
using System.Collections.Generic;
using System.IO;

using Tea;

namespace Aliyun.SDK.CCP.OSSClient.Models
{
    public class MobileSendSmsCodeRequest : TeaModel {
        [NameInMap("header")]
        [Validation(Required=false)]
        public MobileSendSmsCodeRequestHeader Header { get; set; }
        public class MobileSendSmsCodeRequestHeader : TeaModel {
            [NameInMap("x-pds-trace-id")]
            [Validation(Required=false)]
            public string TraceId { get; set; }
            [NameInMap("x-pds-device-id")]
            [Validation(Required=false)]
            public string DeviceId { get; set; }
        };

        [NameInMap("app_id")]
        [Validation(Required=true)]
        public string AppId { get; set; }

        [NameInMap("captcha_id")]
        [Validation(Required=false)]
        public string CaptchaId { get; set; }

        [NameInMap("captcha_text")]
        [Validation(Required=false)]
        public string CaptchaText { get; set; }

        [NameInMap("phone_number")]
        [Validation(Required=true)]
        public string PhoneNumber { get; set; }

        [NameInMap("type")]
        [Validation(Required=true)]
        public string Type { get; set; }

    }

}
