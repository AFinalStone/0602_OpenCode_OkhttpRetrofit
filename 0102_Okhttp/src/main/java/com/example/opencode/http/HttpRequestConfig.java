package com.example.opencode.http;

import java.util.concurrent.TimeUnit;

/**
 * @author syl
 * @time 2020/8/6 17:24
 */

public class HttpRequestConfig {

    private Builder mBuilder;

    public HttpRequestConfig(Builder builder) {
        mBuilder = builder;
    }

    public boolean isDebug() {
        return mBuilder.debug;
    }

    public String getDeviceId() {
        return mBuilder.deviceId;
    }

    public String getImei() {
        return mBuilder.imei;
    }

    public String getDeviceInfo() {
        return mBuilder.deviceInfo;
    }

    public String getVm() {
        if (mBuilder.isVm) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getAppChannel() {
        return mBuilder.appChannel;
    }

    public String getPackageValue() {
        return mBuilder.packageValue;
    }

    public String getUserId() {
        return mBuilder.userId;
    }

    public String getToken() {
        return mBuilder.token;
    }

    public String getAuthorization() {
        return mBuilder.authorization;
    }

    public long getConnectTimeout() {
        return mBuilder.connectTimeout;
    }

    public TimeUnit getConnectTimeUnit() {
        return mBuilder.connectTimeUnit;
    }

    public long getReadTimeout() {
        return mBuilder.readTimeout;
    }

    public TimeUnit getReadTimeUnit() {
        return mBuilder.readTimeUnit;
    }

    public String getAppVersion() {
        return mBuilder.appVersion;
    }

    public String getMiuiVersion() {
        return mBuilder.miuiVersion;
    }

    public String getCacheFilePath() {
        return mBuilder.cacheFilePath;
    }

    public long getCacheFileSize() {
        return mBuilder.cacheFileSize;
    }

    public static class Builder {
        private boolean debug;
        private String deviceId;//设备唯一id
        private String imei;
        private String deviceInfo;
        private boolean isVm;
        private String appChannel;//渠道
        private String appVersion;
        private String packageValue;//包名
        private String cacheFilePath;//缓存目录
        private long cacheFileSize;//缓存目录大小

        private long readTimeout;
        private TimeUnit readTimeUnit;
        private long connectTimeout;
        private TimeUnit connectTimeUnit;

        private String userId;
        private String token;
        private String authorization;
        private String miuiVersion;  // miui版本


        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setVm(boolean vm) {
            isVm = vm;
            return this;
        }

        public Builder setDeviceInfo(String info) {
            deviceInfo = info;
            return this;
        }

        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder setImei(String imei) {
            this.imei = imei;
            return this;
        }

        public Builder setAppChannel(String appChannel) {
            this.appChannel = appChannel;
            return this;
        }

        public Builder setPackageValue(String packageValue) {
            this.packageValue = packageValue;
            return this;
        }

        public Builder setReadTimeout(long timeout, TimeUnit timeUnit) {
            this.readTimeout = timeout;
            this.readTimeUnit = timeUnit;
            return this;
        }

        public Builder setConnectTimeout(long timeout, TimeUnit timeUnit) {
            this.connectTimeout = timeout;
            this.connectTimeUnit = timeUnit;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setAuthorization(String authorization) {
            this.authorization = authorization;
            return this;
        }

        public Builder setMiuiVersion(String miuiVersion) {
            this.miuiVersion = miuiVersion;
            return this;
        }

        public Builder setAppVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        public void setCacheFilePath(String cacheFilePath) {
            this.cacheFilePath = cacheFilePath;
        }

        public void setCacheFileSize(long cacheFileSize) {
            this.cacheFileSize = cacheFileSize;
        }

        public HttpRequestConfig build() {
            initEmptyInitialValues();
            return new HttpRequestConfig(this);
        }

        private void initEmptyInitialValues() {
            if (readTimeout <= 0) {
                readTimeout = 20;
                readTimeUnit = TimeUnit.SECONDS;
            }
            if (connectTimeout <= 0) {
                connectTimeout = 20;
                connectTimeUnit = TimeUnit.SECONDS;
            }
        }

    }

}