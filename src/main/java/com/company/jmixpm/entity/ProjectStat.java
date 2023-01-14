package com.company.jmixpm.entity;

import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.util.UUID;

@JmixEntity
public class ProjectStat {

    @JmixId
    private UUID ProjectId;

    private String projectName;

    private Integer tasksCount;

    private Integer planedEfforts;

    private Integer actualEfforts;

    public Integer getActualEfforts() {
        return actualEfforts;
    }

    public void setActualEfforts(Integer actualEfforts) {
        this.actualEfforts = actualEfforts;
    }

    public Integer getPlanedEfforts() {
        return planedEfforts;
    }

    public void setPlanedEfforts(Integer planedEfforts) {
        this.planedEfforts = planedEfforts;
    }

    public Integer getTasksCount() {return tasksCount;}

    public void setTasksCount(Integer tasksCount) {
        this.tasksCount = tasksCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UUID getProjectId() {
        return ProjectId;
    }

    public void setProjectId(UUID projectId) {
        this.ProjectId = projectId;
    }
}