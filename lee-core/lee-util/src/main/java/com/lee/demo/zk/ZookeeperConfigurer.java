package com.lee.demo.zk;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.io.Resource;

public class ZookeeperConfigurer extends PropertySourcesPlaceholderConfigurer {
    private Map<String, Object> ctxPropsMap = new HashMap();
    private ZookeeperResource zkLocation;
    private Resource[] localLocations = new Resource[0];

    public ZookeeperConfigurer() {
    }

    public void setLocation(Resource location) {
        this.zkLocation = (ZookeeperResource)location;
        super.setLocations((Resource[])mergeArray(this.localLocations, this.zkLocation));
    }

    public void setLocations(Resource[] locations) {
        System.arraycopy(locations, 0, this.localLocations, 0, locations.length);
        super.setLocations((Resource[])mergeArray(locations, this.zkLocation));
    }

    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
    }

    public Object getProperty(String key) {
        return this.ctxPropsMap.get(key);
    }

    public ZookeeperResource getZkResoucre() {
        return this.zkLocation;
    }

    private static Resource[] mergeArray(Resource[] m1, Resource m2) {
        Resource[] all = new Resource[m1.length + 1];
        if (m1.length > 0) {
            System.arraycopy(all, 0, m1, 0, m1.length);
            all[m1.length] = m2;
        } else {
            all[m1.length] = m2;
        }

        return all;
    }
}
