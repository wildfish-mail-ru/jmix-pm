package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectStat;
import com.company.jmixpm.entity.Task;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectStatsService {
    @Autowired
    private DataManager dataManager;

    public List<ProjectStat>  fetchProjectStatistics(){
        List<Project> projects = dataManager.load(Project.class).all().fetchPlan("project-with-tasks").list();

        List<ProjectStat> projectStats = projects.stream().map(project -> {
            ProjectStat stat = dataManager.create(ProjectStat.class);
            stat.setProjectId(project.getId());
            stat.setProjectName(project.getName());
            stat.setTasksCount(project.getTasks().size());
            Integer estimatedEfforts = project.getTasks().stream().map(Task::getEstimatedEfforts).reduce(0, Integer::sum);
            stat.setPlanedEfforts(estimatedEfforts);

            return stat;
        }).collect(Collectors.toList());

        return projectStats;
    }
}