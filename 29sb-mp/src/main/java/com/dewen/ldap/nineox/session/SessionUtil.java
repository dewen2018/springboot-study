package com.dewen.ldap.nineox.session;

public class SessionUtil {
    private static final ThreadLocal<Long> threadUid = new ThreadLocal<Long>();
    private static final ThreadLocal<String> threadUname = new ThreadLocal<String>();
    private static final ThreadLocal<String> threadIp = new ThreadLocal<String>();
    private static final ThreadLocal<Long> threadOrgId = new ThreadLocal<Long>();
    private static final ThreadLocal<String> threadSsoUid = new ThreadLocal<String>();
    private static final ThreadLocal<String> threadSsoOrgId = new ThreadLocal<String>();
    private static final ThreadLocal<String> threadSsoDepId = new ThreadLocal<String>();
    private static final ThreadLocal<String> threadSsoTalendId = new ThreadLocal<String>();

    public static long getUid() {
        if (threadUid.get() == null) {
            return 0;
        } else {
            long uid = (Long) threadUid.get();
            return uid;
        }
    }

    public static void setUid(long uid) {
        threadUid.set(uid);
    }

    public static String getUname() {
        if (threadUname.get() == null) {
            return "";
        } else {
            String uname = threadUname.get();
            return uname;
        }
    }

    public static void setUname(String uname) {
        threadUname.set(uname);
    }

    public static String getIp() {
        if (threadIp.get() == null) {
            return "";
        } else {
            String ip = threadIp.get();
            return ip;
        }
    }

    public static void setIp(String ip) {
        threadIp.set(ip);
    }
    
    public static String getSsoUid() {
        if (threadSsoUid.get() == null) {
            return "";
        } else {
            String ssoUid = threadSsoUid.get();
            return ssoUid;
        }
    }

    public static void setSsoUid(String ssoUid) {
        if(null != ssoUid)
            threadSsoUid.set(ssoUid);
    }
    
    public static String getSsoOrgId() {
        if (threadSsoOrgId.get() == null) {
            return "";
        } else {
            String ssoOrgId = threadSsoOrgId.get();
            return ssoOrgId;
        }
    }

    public static void setSsoOrgId(String ssoOrgId) {
    	if(null != ssoOrgId)
    	  threadSsoOrgId.set(ssoOrgId);
    }
    
    public static String getSsoDepId() {
        if (threadSsoDepId.get() == null) {
            return "";
        } else {
            String ssoDepId = threadSsoDepId.get();
            return ssoDepId;
        }
    }

    public static void setSsoDepId(String ssoDepId) {
    	if(null!=ssoDepId)
    	   threadSsoDepId.set(ssoDepId);
    }    

    public static void setOrgId(long orgId) {
        threadOrgId.set(orgId);
    }

    public static Object getOrgId() {
        if (threadOrgId.get() == null) {
            return 0;
        } else {
            long orgId = (Long) threadOrgId.get();
            return orgId;
        }
    }
    
    public static String getSsoTalendId() {
        if (threadSsoTalendId.get() == null) {
            return "";
        } else {
            String ssoTalendId = threadSsoTalendId.get();
            return ssoTalendId;
        }
    }

    public static void setSsoTalendId(String ssoTalendId) {
        if(null!=ssoTalendId)
           threadSsoTalendId.set(ssoTalendId);
    }   

    public static void remove() {
        threadUid.remove();
        threadUname.remove();
        threadIp.remove();
        threadOrgId.remove();
        threadSsoUid.remove();
        threadSsoOrgId.remove();
        threadSsoDepId.remove();
        threadSsoTalendId.remove();
    }
}
