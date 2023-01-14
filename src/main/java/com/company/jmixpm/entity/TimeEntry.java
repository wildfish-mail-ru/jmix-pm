package com.company.jmixpm.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "TIME_ENTRY", indexes = {
        @Index(name = "IDX_TIME_ENTRY_TASK", columnList = "TASK_ID"),
        @Index(name = "IDX_TIME_ENTRY_ASSIGNEE", columnList = "ASSIGNEE_ID")
})
@Entity
public class TimeEntry {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TASK_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Task task;

    @PositiveOrZero
    @Column(name = "TIME_SPEND", nullable = false)
    @NotNull
    private Integer timeSpend;

    @NotNull
    @Column(name = "ENTRY_DATE", nullable = false)
    private LocalDateTime entryDate;

    @JoinColumn(name = "ASSIGNEE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User assignee;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Integer getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(Integer timeSpend) {
        this.timeSpend = timeSpend;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"entryDate", "description"})
    public String getInstanceName() {
        return String.format("%s %s", entryDate, description);
    }
}