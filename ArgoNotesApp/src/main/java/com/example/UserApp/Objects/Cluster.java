package com.example.UserApp.Objects;

import java.util.UUID;

public class Cluster {
    private UUID cluster_id;
    private String cluster_name;

    public Cluster(){}

    public UUID getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(UUID cluster_id) {
        this.cluster_id = cluster_id;
    }

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }
}
