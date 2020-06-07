package taskapi.assignment.infrastructure;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;

import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.Assignments;
import taskapi.assignment.domain.SingleAssignment;

@Repository
public class MongoAssignmentRepository implements AssignmentRepository {

    private MongoOperations mongoOps;

    public MongoAssignmentRepository(MongoClient mongoClient){
        mongoOps = new MongoTemplate(mongoClient, "tasksdb");
    }

    @Override
    public void add(SingleAssignment assignment) {
        mongoOps.updateFirst(new Query(where("id").is(assignment.getPersonId())),
                             new Update().addToSet("taskIds", assignment.getTaskId()),
                             "Person");

    }

    @Override
    public Assignments getAllByPerson(String personId) {
        final Assignments assignments = mongoOps.findOne(new Query(where("id").is(personId)), Assignments.class, "Person");
        if(assignments == null){
            return new Assignments(personId, new ArrayList<>());
        }
        return assignments;
    }

    @Override
    public List<Assignments> getAll() {
        return mongoOps.findAll(Assignments.class, "Person");
    }
}
