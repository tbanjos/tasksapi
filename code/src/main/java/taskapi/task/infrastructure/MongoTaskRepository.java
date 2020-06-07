package taskapi.task.infrastructure;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;

import taskapi.task.domain.Task;
import taskapi.task.domain.TaskRepository;

@Repository
public class MongoTaskRepository implements TaskRepository {

    private MongoOperations mongoOps;

    public MongoTaskRepository(MongoClient mongoClient){
        mongoOps = new MongoTemplate(mongoClient, "tasksdb");
    }

    @Override
    public List<Task> getAll() {
        return mongoOps.findAll(Task.class);
    }

    @Override
    public void add(Task task) {
        mongoOps.upsert(new Query(where("id").is(task.getId())),
                        new Update().set("description", task.getDescription()),
                        Task.class);
    }

    @Override
    public Optional<Task> get(String id) {
        final Task task = mongoOps.findOne(new Query(where("id").is(id)), Task.class);
        if(task == null){
            return Optional.empty();
        }
        return Optional.of(task);
    }

    @Override
    public void update(Task task) {
        mongoOps.upsert(new Query(where("id").is(task.getId())),
                        new Update().set("description", task.getDescription()),
                        Task.class);
    }

    @Override
    public void delete(String id) {
        mongoOps.remove(new Query(where("id").is(id)), Task.class);
    }
}
