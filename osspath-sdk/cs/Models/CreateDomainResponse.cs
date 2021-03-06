// This file is auto-generated, don't edit it. Thanks.

using System;
using System.Collections.Generic;
using System.IO;

using Tea;

namespace Aliyun.SDK.CCP.OSSClient.Models
{
    /**
     * create domain response
     */
    public class CreateDomainResponse : TeaModel {
        [NameInMap("auth_alipay_app_id")]
        [Validation(Required=false)]
        public string AuthAlipayAppId { get; set; }

        [NameInMap("auth_alipay_enable")]
        [Validation(Required=false)]
        public bool? AuthAlipayEnable { get; set; }

        [NameInMap("auth_alipay_private_key")]
        [Validation(Required=false)]
        public string AuthAlipayPrivateKey { get; set; }

        [NameInMap("auth_config")]
        [Validation(Required=false)]
        public Dictionary<string, object> AuthConfig { get; set; }

        [NameInMap("auth_dingding_app_id")]
        [Validation(Required=false)]
        public string AuthDingdingAppId { get; set; }

        [NameInMap("auth_dingding_app_secret")]
        [Validation(Required=false)]
        public string AuthDingdingAppSecret { get; set; }

        [NameInMap("auth_dingding_enable")]
        [Validation(Required=false)]
        public bool? AuthDingdingEnable { get; set; }

        [NameInMap("auth_endpoint_enable")]
        [Validation(Required=false)]
        public bool? AuthEndpointEnable { get; set; }

        [NameInMap("auth_ram_app_id")]
        [Validation(Required=false)]
        public string AuthRamAppId { get; set; }

        [NameInMap("auth_ram_app_secret")]
        [Validation(Required=false)]
        public string AuthRamAppSecret { get; set; }

        [NameInMap("auth_ram_enable")]
        [Validation(Required=false)]
        public bool? AuthRamEnable { get; set; }

        [NameInMap("created_at")]
        [Validation(Required=false)]
        public string CreatedAt { get; set; }

        [NameInMap("data_hash_name")]
        [Validation(Required=false)]
        public string DataHashName { get; set; }

        [NameInMap("description")]
        [Validation(Required=false)]
        public string Description { get; set; }

        [NameInMap("domain_id")]
        [Validation(Required=false)]
        public string DomainId { get; set; }

        [NameInMap("domain_name")]
        [Validation(Required=false)]
        public string DomainName { get; set; }

        [NameInMap("event_filename_matches")]
        [Validation(Required=false)]
        public string EventFilenameMatches { get; set; }

        [NameInMap("event_mns_endpoint")]
        [Validation(Required=false)]
        public string EventMnsEndpoint { get; set; }

        [NameInMap("event_mns_topic")]
        [Validation(Required=false)]
        public string EventMnsTopic { get; set; }

        [NameInMap("event_names")]
        [Validation(Required=false)]
        public List<string> EventNames { get; set; }

        [NameInMap("event_role_arn")]
        [Validation(Required=false)]
        public string EventRoleArn { get; set; }

        [NameInMap("init_drive_enable")]
        [Validation(Required=false)]
        public bool? InitDriveEnable { get; set; }

        [NameInMap("init_drive_size")]
        [Validation(Required=false)]
        public long InitDriveSize { get; set; }

        [NameInMap("init_drive_store_id")]
        [Validation(Required=false)]
        public string InitDriveStoreId { get; set; }

        [NameInMap("path_type")]
        [Validation(Required=false)]
        public string PathType { get; set; }

        [NameInMap("published_app_access_strategy")]
        [Validation(Required=false)]
        public AppAccessStrategy PublishedAppAccessStrategy { get; set; }

        [NameInMap("sharable")]
        [Validation(Required=false)]
        public bool? Sharable { get; set; }

        [NameInMap("store_level")]
        [Validation(Required=false)]
        public string StoreLevel { get; set; }

        [NameInMap("store_region_list")]
        [Validation(Required=false)]
        public List<string> StoreRegionList { get; set; }

        [NameInMap("updated_at")]
        [Validation(Required=false)]
        public string UpdatedAt { get; set; }

    }

}
