
using System;
using System.Collections.Generic;

using Tea;

namespace Aliyun.SDK.CCP.CCPClient.Models
{
    public class CancelLinkRequest : TeaModel {
        [NameInMap("temporary_token")]
        [Validation(Required=true)]
        public string TemporaryToken { get; set; }

    }

}
