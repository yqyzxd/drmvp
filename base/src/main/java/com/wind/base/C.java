package com.wind.base;

import java.util.Map;

/**
 * Created by wind on 2017/3/1.
 */

public class C {
    public static class Version{
        public static final String API_VERSION="1.73";
    }
    public static class Api{
        /**
         * 测试地址
         */
        static String DEBUG_BASE_URL="https://marryu.miaotu.net/app/";

        /**
         * 线上地址
         */
        static String RELEASE_BASE_URL="https://app.51marryyou.com/";
        /**
         * 图片服务器地址
         */
        public static String IMAGE_SERVER_URL="https://img.51marryyou.com/upload.php";


        public static String getUrl(String part){
            return DEBUG_BASE_URL+part;
        }

        /**
         * 直播相关的线上地址
         */
        static String RELEASE_LIVE_URL="https://live.51marryyou.com/";

        public static String getLiveUrl(String part){
            return DEBUG_BASE_URL+part;
        }

        public static void addCommonParams(Map<String,String> paramsMap){
           /* String token=paramsMap.get("token");
            if (TextUtils.isEmpty(token)){
                paramsMap.put("token", Key.VISITOR_TOKEN);
            }
            paramsMap.put("version",Version.API_VERSION);
            //包名
            paramsMap.put("packet_name", Pack.PACK_NAME_MU);

            paramsMap.put("configure",getDevice());
            //token=56a7f058-16a1-f6fd-9f15-6dbcc6fbe56f&marry_timestamp=1234567890123&sign_key=38e3951a7fd78f6756777ed8057e8b75
            token=paramsMap.get("token");
            String marry_timestamp=System.currentTimeMillis()+"";
            String sign_key=Key.SIGN_KEY;
            String sign="token="+token+"&marry_timestamp="+marry_timestamp+"&sign_key="+sign_key;
            String marry_sign= Md5Util.md5Hex(sign);
            paramsMap.put("marry_sign",marry_sign);
            paramsMap.put("marry_timestamp",marry_timestamp);*/
        }
        public static String getDevice() {
            return "{\"device\":\"android\"}";
        }
    }

}
