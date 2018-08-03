package com.example.webflux.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.cluster.ClusterInfo;

@EnableCouchbaseRepositories
@Configuration
public class CouchbaseConfig extends AbstractReactiveCouchbaseConfiguration {
    
    @Value("${couchbase.cluster.username:Administrator}")
    private String clusterUsername;
    
    @Value("${couchbase.cluster.password:Administrator}")
    private String clusterPassword;
    
    @Value("${couchbase.bucket.name}")
    private String bucketName;
    
    @Value("${spring.couchbase.bootstrap-hosts}")
    private String bootstrapHosts;

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(bootstrapHosts);
    }

    @Override
    protected String getBucketName() {
        return this.bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }

    @Override
    @Bean(destroyMethod = "disconnect", name = BeanNames.COUCHBASE_CLUSTER)
    @Primary
    public Cluster couchbaseCluster() throws Exception {
        Cluster cluster = CouchbaseCluster.create(getBootstrapHosts());
        cluster.authenticate(this.clusterUsername, this.clusterPassword);
        return cluster;
    }

    @Override
    @Bean(destroyMethod = "close", name = BeanNames.COUCHBASE_BUCKET)
    @Primary
    public Bucket couchbaseClient() throws Exception {
        return couchbaseCluster().openBucket(getBucketName());
    }

    @Override
    @Bean(name = BeanNames.COUCHBASE_CLUSTER_INFO)
    @Primary
    public ClusterInfo couchbaseClusterInfo() throws Exception {
        return couchbaseCluster().clusterManager().info();
    }

}
