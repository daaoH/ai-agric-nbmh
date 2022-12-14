package com.hszn.nbmh.third.utils;

/**
 * <p>
 * 随机验证码生成工具
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
public class GeneratorCodeUtil {

    public static String createRandom(String phoneNumber) {

        //利用哈希值生成验证码
        int hash = phoneNumber.hashCode();
        //加密
        int encryption = 2020666;
        //用hash异或上加密得到生成第一次加密结果
        //这个生成的加过永远是固定的如果加密码不动的情况下
        long result = hash ^ encryption;
        //利用获得当前时间再次加密得出结果
        long time = System.currentTimeMillis();
        result = result ^ time;
        //取后六位
        long code = result % 1000000;
        //此时随机验证码生成完成
        //此时会出现生成的验证码为负值，利用三目表达式进行解决
        code = code < 0 ? -code : code;
        //此时还会出现第二个问题当生成000656这种只会显示656不会补零所以我们现在要解决当位数不够在前补零
        //解决思路利用数据结构的算法，利用数组，将得到的验证码长度作为数组的角码，在拼接字符就可以完成补零
        //先把得到的验证码变成字符串
        String codeStr = code + "";
        //再把code的字符长度取出,作为数组的角标
        int len = codeStr.length();
        String[] patch = {"000000", "00000", "0000", "000", "00", "0", ""};
        //验证有多少为需要补
        String patch1 = patch[len];
        return patch1 + codeStr;
    }


    public static void main(String[] args) {
        String code = createRandom("18687039336");
        System.out.println("====code=====" + code);
    }


}
