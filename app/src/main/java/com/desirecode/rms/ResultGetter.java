package com.desirecode.rms;

public class ResultGetter {

    private String ict1301,ict1302,ict1303,cmt1301,cmt1303,cml1201,cmt1005;

    public ResultGetter (String ict1301, String ict1302, String ict1303, String cmt1301, String cmt1303, String cml1201, String cmt1005){
        this.ict1301 = ict1301;
        this.ict1302 = ict1302;
        this.ict1303 = ict1303;
        this.cmt1301 = cmt1301;
        this.cmt1303 = cmt1303;
        this.cml1201 = cml1201;
        this.cmt1005 = cmt1005;
    }

    public String getIct1301() {
        return ict1301;
    }

    public String getIct1302() {
        return ict1302;
    }

    public String getIct1303() {
        return ict1303;
    }

    public String getCmt1301() {
        return cmt1301;
    }

    public String getCmt1303() {
        return cmt1303;
    }

    public String getCml1201() {
        return cml1201;
    }

    public String getCmt1005() {
        return cmt1005;
    }
}
