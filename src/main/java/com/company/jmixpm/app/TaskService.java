package com.company.jmixpm.app;

import com.company.jmixpm.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private DataManager dataManager;

    // найдём самого незанятого пользователя, у кого меньше всего занятых часов, а не количество задач
    public User findLeastBusyUser() {

        List<KeyValueEntity> list;
        list = dataManager.loadValues("select u, sum(t.estimatedEfforts) from User u left outer join Task_ t on u = t.assignee group by u order by sum(t.estimatedEfforts)")
//        list = dataManager.loadValues("select u, count(t.id) from User u left outer join Task_ t on u = t.assignee group by u order by count(t.id)")
                .properties("user", "tasks")
                .list();

        User user = list.get(0).getValue("user");

//        for (KeyValueEntity kvEntity : kvEntities) {
//            Customer customer = kvEntity.getValue("customer");
//            BigDecimal sum = kvEntity.getValue("sum");
//            sb.append(customer.getName()).append(" : ").append(sum).append("\n");
//        }
//

//        try {
//            user = list.stream().findFirst().get().getValue("user");
//        } catch (Exception e) {
//            throw new IllegalStateException();
//        }

//        try {
//            user = list.get(0).getValue("user");
//        } catch (Exception e) {
//            throw new RuntimeException("e!");
//        }


        return user;
    }
}