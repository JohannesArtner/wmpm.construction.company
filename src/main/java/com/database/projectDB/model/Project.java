package com.database.projectDB.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.Id;

/**
 * Created by rudolfplettenberg on 08.05.16.
 */
public class Project {
    @Id
    private String id;

    @NotNull
    private long clientId;
    private long projectManagerId;

    private Offer offer;
    private ProjectStatus projectStatus;
    private double progress;


    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(long projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", clientId=" + clientId +
                ", projectManagerId=" + projectManagerId +
                ", offer=" + offer +
                ", projectStatus=" + projectStatus +
                ", progress=" + progress +
                '}';
    }


}
