package in.nic.cmf.geotagger;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith (SpringJUnit4ClassRunner.class)
@Configuration ("/dmsservice-servlet.xml")
public class GeoTagsServiceImplTest {

    @Autowired
    GeoTagsInfo geoservice;

    String      city     = "chennai,delhi,mumbai";

    String      ip       = "124.7.124.74,124.7.124.168";

    String      ip_map   = "{GeoTags="
                                 + "{Locations=[{Ip=124.7.124.74, State=25, District=, longitude=80.2833, latitude=13.0833, City=Chennai, country=IN},"
                                 + " {Ip=124.7.124.168, State=25, District=, longitude=80.2833, latitude=13.0833, City=Chennai, country=IN}]}}";
    String      city_map = "{GeoTags="
                                 + "{Locations=[{Ip=, State=25, District=, longitude=80.2833, latitude=13.0833, City=Chennai, country=IN}, {Ip=, State=NY, District=, "
                                 + "longitude=-74.9308, latitude=42.3179, City=Delhi, country=US}, {Ip=, State=16, District=, longitude=72.8258, latitude=18.975, City=Mumbai,"
                                 + " country=IN}]}}";

    @Test
    public void testProcessByCity() {
        Map geo = geoservice.processByCity(city);
        Assert.assertEquals(geo.toString(), ip_map);
    }

    @Test
    public void testProcessByIp() {
        Map geo = geoservice.processByIp(ip);
        Assert.assertEquals(geo.toString(), ip_map);

    }

}
