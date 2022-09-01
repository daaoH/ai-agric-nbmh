package com.hszn.nbmh.user.utils;

/**
 * <p>
 * 计算高德经纬度坐标点之间的距离 工具类
 * </p>
 *
 * @author MCR
 * @since 2022-09-01
 */
public class AMapUtils {

    public static double calculateLineDistance(LatLng var0, LatLng var1) {
        if (var0 != null && var1 != null) {
            return calculateLineDistance(var0.latitude, var0.longitude, var1.latitude, var1.longitude);
        } else {
            try {
                throw new Exception("非法坐标值");
            } catch (Exception var27) {
                var27.printStackTrace();
                return 0.0;
            }
        }
    }

    public static double calculateLineDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        try {
            double var2 = longitude1;
            double var4 = latitude1;
            double var6 = longitude2;
            double var8 = latitude2;

            var2 *= 0.01745329251994329D;
            var4 *= 0.01745329251994329D;
            var6 *= 0.01745329251994329D;
            var8 *= 0.01745329251994329D;
            double var10 = Math.sin(var2);
            double var12 = Math.sin(var4);
            double var14 = Math.cos(var2);
            double var16 = Math.cos(var4);
            double var18 = Math.sin(var6);
            double var20 = Math.sin(var8);
            double var22 = Math.cos(var6);
            double var24 = Math.cos(var8);
            double[] var28 = new double[3];
            double[] var29 = new double[3];
            var28[0] = var16 * var14;
            var28[1] = var16 * var10;
            var28[2] = var12;
            var29[0] = var24 * var22;
            var29[1] = var24 * var18;
            var29[2] = var20;
            return (Math.asin(Math.sqrt((var28[0] - var29[0]) * (var28[0] - var29[0]) + (var28[1] - var29[1]) * (var28[1] - var29[1]) + (var28[2] - var29[2]) * (var28[2] - var29[2])) / 2.0D) * 1.27420015798544E7D);
        } catch (Throwable var26) {
            var26.printStackTrace();
            return 0.0;
        }

    }

    public static void main(String[] args) {

        double lat1 = 39.923423, lng1 = 116.368904;

        double lat2 = 39.922501, lng2 = 116.987271;

        double v = AMapUtils.calculateLineDistance(new LatLng(lat1, lng1), new LatLng(lat2, lng2));
        System.out.println("通过LatLng对象计算：两个点之间的距离为：" + v + " 米");

        double v1 = AMapUtils.calculateLineDistance(lat1, lng1, lat2, lng2);
        System.out.println("直接传入经纬度计算：两个点之间的距离为：" + v1 + " 米");

    }

}


