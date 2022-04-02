package com.dewen.ldap.lang.inum;

public interface Onum<KEYTYPE, VALUETYPE> {
    KEYTYPE getKey();

    VALUETYPE getDesc();
}
