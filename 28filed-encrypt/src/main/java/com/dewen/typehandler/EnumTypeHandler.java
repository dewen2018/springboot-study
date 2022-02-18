package com.dewen.typehandler;

import com.dewen.enums.EStatus;
import org.apache.ibatis.type.MappedTypes;

/**
 * multi
 *
 * @param <E>
 */
@MappedTypes(value = {EStatus.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
    public EnumTypeHandler(Class<E> type) {
        super(type);
    }
}