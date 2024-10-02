package co.edu.umanizales.kids_list.service;

import co.edu.umanizales.kids_list.model.ListDE;
import co.edu.umanizales.kids_list.model.NodeDE;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE listDE;

    @PostConstruct
    public void init(){
        listDE = new ListDE();
    }

    public NodeDE showKids(){
        return listDE.getHead();
    }
}
