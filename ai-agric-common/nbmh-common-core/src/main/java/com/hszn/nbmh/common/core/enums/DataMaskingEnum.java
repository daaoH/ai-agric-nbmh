package com.hszn.nbmh.common.core.enums;

import com.hszn.nbmh.common.core.constant.DataMaskingConstant;
import org.springframework.util.StringUtils;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:12 22/8/26
 * @Modified By:
 */
public enum DataMaskingEnum {

    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> {
        return str;
    }),
    HALF_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if(i < (str.length() / 4) || i > (str.length() - (str.length() / 4))){
                    sb.append(str.charAt(i));
                }else{
                    sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingConstant.MASK_CHAR);
                }
            }
            return sb.toString();
        } else {
            return str;
        }
    }),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingConstant.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    });

    private final DataMaskingConstant operation;

    private DataMaskingEnum(DataMaskingConstant operation) {
        this.operation = operation;
    }

    public DataMaskingConstant operation() {
        return this.operation;
    }

}
