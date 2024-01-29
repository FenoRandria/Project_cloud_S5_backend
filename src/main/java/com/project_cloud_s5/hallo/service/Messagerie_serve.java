package com.project_cloud_s5.hallo.service;
import org.springframework.stereotype.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import com.project_cloud_s5.hallo.model.proprietaire.Messagerie;

@Service
public class Messagerie_serve {
    @Autowired
    com.project_cloud_s5.hallo.repository.MessagerieRepository messagingRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public void nouveauMessage(Messagerie message) throws Exception {
        try {
            message.setDateHeureEnvoie(LocalDateTime.now());
            messagingRepository.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public List<Messagerie> getDiscussions(String idEnvoyeur, String idReceveur) {
        this.setStatus(idReceveur, idEnvoyeur, 20, 1);
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("idEnvoyeur").is(idEnvoyeur).and("idReceveur").is(idReceveur),
                Criteria.where("idEnvoyeur").is(idReceveur).and("idReceveur").is(idEnvoyeur)
        );
        Query query = new Query(criteria).with(Sort.by(Sort.Direction.ASC, "dateHeureEnvoie"));
        return mongoTemplate.find(query, Messagerie.class);
    }
    public void setStatus(String idEnvoyeur, String idReceveur, int status, int avant) {
        Query query = new Query(Criteria.where("idEnvoyeur").is(idEnvoyeur).and("idReceveur").is(idReceveur).and("status").is(avant));
        Update update = new Update().set("status", status);
        mongoTemplate.updateFirst(query, update, Messagerie.class);
        System.out.println("Set status teto");
    }
    public List<Messagerie> getLastMessagesForReceiver(String receiverId) {
        org.springframework.data.mongodb.core.aggregation.Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("idReceveur").is(receiverId)),
            Aggregation.sort(Sort.Direction.DESC, "dateHeureEnvoie"),
            Aggregation.group("idEnvoyeur", "idReceveur")
                    .first("$$ROOT").as("lastMessage"),
            Aggregation.replaceRoot().withValueOf("$lastMessage")  
        );
        return mongoTemplate.aggregate(aggregation, "discussions", Messagerie.class).getMappedResults();
    }
}