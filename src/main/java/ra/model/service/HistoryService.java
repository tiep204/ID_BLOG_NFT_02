package ra.model.service;

import ra.model.entity.History;
import ra.model.entity.Likes;

import java.util.List;

public interface HistoryService {
    List<History> findAll();
    History findByID(int historyID);
    History save(History history);
    History saveOrUpdate(History history);
    void delete(int historyID);
}
