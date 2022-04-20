package com.afs.okhttpretrofit.bean;

public class LauncherBean {

    private RechargeBean recharge;
    private SfBean sf;
    private JyBean jy;
    private LiveBean live;
    private LoginBean login;
    private HtmlBean html;

    @Override
    public String toString() {
        return "LauncherBean{" +
                "recharge=" + recharge +
                ", sf=" + sf +
                ", jy=" + jy +
                ", live=" + live +
                ", login=" + login +
                ", html=" + html +
                '}';
    }

    public RechargeBean getRecharge() {
        return recharge;
    }

    public void setRecharge(RechargeBean recharge) {
        this.recharge = recharge;
    }

    public SfBean getSf() {
        return sf;
    }

    public void setSf(SfBean sf) {
        this.sf = sf;
    }

    public JyBean getJy() {
        return jy;
    }

    public void setJy(JyBean jy) {
        this.jy = jy;
    }

    public LiveBean getLive() {
        return live;
    }

    public void setLive(LiveBean live) {
        this.live = live;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public HtmlBean getHtml() {
        return html;
    }

    public void setHtml(HtmlBean html) {
        this.html = html;
    }

    public static class RechargeBean {
        private String hycz;
        private String mbcz;
        private String mbcztc;
        private String mzcz;
        private String mzcztc;

        public String getHycz() {
            return hycz;
        }

        public void setHycz(String hycz) {
            this.hycz = hycz;
        }

        public String getMbcz() {
            return mbcz;
        }

        public void setMbcz(String mbcz) {
            this.mbcz = mbcz;
        }

        public String getMbcztc() {
            return mbcztc;
        }

        public void setMbcztc(String mbcztc) {
            this.mbcztc = mbcztc;
        }

        public String getMzcz() {
            return mzcz;
        }

        public void setMzcz(String mzcz) {
            this.mzcz = mzcz;
        }

        public String getMzcztc() {
            return mzcztc;
        }

        public void setMzcztc(String mzcztc) {
            this.mzcztc = mzcztc;
        }

        @Override
        public String toString() {
            return "RechargeBean{" +
                    "hycz='" + hycz + '\'' +
                    ", mbcz='" + mbcz + '\'' +
                    ", mbcztc='" + mbcztc + '\'' +
                    ", mzcz='" + mzcz + '\'' +
                    ", mzcztc='" + mzcztc + '\'' +
                    '}';
        }
    }

    public static class SfBean {
        private String jctj;
        private String tjjyzb;

        public String getJctj() {
            return jctj;
        }

        public void setJctj(String jctj) {
            this.jctj = jctj;
        }

        public String getTjjyzb() {
            return tjjyzb;
        }

        public void setTjjyzb(String tjjyzb) {
            this.tjjyzb = tjjyzb;
        }

        @Override
        public String toString() {
            return "SfBean{" +
                    "jctj='" + jctj + '\'' +
                    ", tjjyzb='" + tjjyzb + '\'' +
                    '}';
        }
    }

    public static class JyBean {
        private String yjdzh;

        public String getYjdzh() {
            return yjdzh;
        }

        public void setYjdzh(String yjdzh) {
            this.yjdzh = yjdzh;
        }

        @Override
        public String toString() {
            return "JyBean{" +
                    "yjdzh='" + yjdzh + '\'' +
                    '}';
        }
    }

    public static class LiveBean {
        private String zbtjtc;

        public String getZbtjtc() {
            return zbtjtc;
        }

        public void setZbtjtc(String zbtjtc) {
            this.zbtjtc = zbtjtc;
        }

        @Override
        public String toString() {
            return "LiveBean{" +
                    "zbtjtc='" + zbtjtc + '\'' +
                    '}';
        }
    }

    public static class LoginBean {
        private String wechat_app_id;
        private String qq_app_id;
        private String yidun_business_id;
        private String pay_wechat_app_id;

        public String getWechat_app_id() {
            return wechat_app_id;
        }

        public void setWechat_app_id(String wechat_app_id) {
            this.wechat_app_id = wechat_app_id;
        }

        public String getQq_app_id() {
            return qq_app_id;
        }

        public void setQq_app_id(String qq_app_id) {
            this.qq_app_id = qq_app_id;
        }

        public String getYidun_business_id() {
            return yidun_business_id;
        }

        public void setYidun_business_id(String yidun_business_id) {
            this.yidun_business_id = yidun_business_id;
        }

        public String getPay_wechat_app_id() {
            return pay_wechat_app_id;
        }

        public void setPay_wechat_app_id(String pay_wechat_app_id) {
            this.pay_wechat_app_id = pay_wechat_app_id;
        }

        @Override
        public String toString() {
            return "LoginBean{" +
                    "wechat_app_id='" + wechat_app_id + '\'' +
                    ", qq_app_id='" + qq_app_id + '\'' +
                    ", yidun_business_id='" + yidun_business_id + '\'' +
                    ", pay_wechat_app_id='" + pay_wechat_app_id + '\'' +
                    '}';
        }
    }

    public static class HtmlBean {
        private String about_logo;
        private String user_protocol_link;
        private String privacy_protocol_link;
        private String recharge_protocol_link;
        private String relief_protocol_link;
        private String culture_protocol_link;
        private String agree_platform_link;
        private String protocol_link;
        private String teenager_link;
        private String copyright_info;

        public String getAbout_logo() {
            return about_logo;
        }

        public void setAbout_logo(String about_logo) {
            this.about_logo = about_logo;
        }

        public String getUser_protocol_link() {
            return user_protocol_link;
        }

        public void setUser_protocol_link(String user_protocol_link) {
            this.user_protocol_link = user_protocol_link;
        }

        public String getPrivacy_protocol_link() {
            return privacy_protocol_link;
        }

        public void setPrivacy_protocol_link(String privacy_protocol_link) {
            this.privacy_protocol_link = privacy_protocol_link;
        }

        public String getRecharge_protocol_link() {
            return recharge_protocol_link;
        }

        public void setRecharge_protocol_link(String recharge_protocol_link) {
            this.recharge_protocol_link = recharge_protocol_link;
        }

        public String getRelief_protocol_link() {
            return relief_protocol_link;
        }

        public void setRelief_protocol_link(String relief_protocol_link) {
            this.relief_protocol_link = relief_protocol_link;
        }

        public String getCulture_protocol_link() {
            return culture_protocol_link;
        }

        public void setCulture_protocol_link(String culture_protocol_link) {
            this.culture_protocol_link = culture_protocol_link;
        }

        public String getAgree_platform_link() {
            return agree_platform_link;
        }

        public void setAgree_platform_link(String agree_platform_link) {
            this.agree_platform_link = agree_platform_link;
        }

        public String getProtocol_link() {
            return protocol_link;
        }

        public void setProtocol_link(String protocol_link) {
            this.protocol_link = protocol_link;
        }

        public String getTeenager_link() {
            return teenager_link;
        }

        public void setTeenager_link(String teenager_link) {
            this.teenager_link = teenager_link;
        }

        public String getCopyright_info() {
            return copyright_info;
        }

        public void setCopyright_info(String copyright_info) {
            this.copyright_info = copyright_info;
        }

    }
}
