package com.dewen.client2;

import com.dewen.model.Coordinate;
import com.dtflys.forest.annotation.*;

import java.util.Map;

/**
 * 高德地图服务客户端接口
 *
 * @author gongjun
 * @since 2016-06-01
 */
@BaseRequest(baseURL = "http://ditu.amap.com")
public interface Amap {

    /**
     * 根据经纬度获取详细地址
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    @Request(
            url = "/service/regeo",
            dataType = "json"
    )
    Map getLocation(@DataParam("longitude") String longitude, @DataParam("latitude") String latitude);

    /**
     * 根据经纬度获取详细地址
     *
     * @param coordinate 经纬度对象
     * @return
     */
    @Request(
            url = "/service/regeo",
            dataType = "json"
    )
    Map getLocation(@DataObject Coordinate coordinate);


    /**
     * 根据经纬度获取详细地址
     *
     * @param coordinate 经纬度对象
     * @return
     */
    @Request(
            url = "/service/regeo",
            dataType = "json",
            data = {
                    "longitude=${coord.longitude}",
                    "latitude=${coord.latitude}"
            }
    )
    Map getLocationByCoordinate(@DataVariable("coord") Coordinate coordinate);


}