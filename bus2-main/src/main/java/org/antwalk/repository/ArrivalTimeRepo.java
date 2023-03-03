package org.antwalk.repository;

import java.util.List;

import org.antwalk.entity.ArrivalTimeTable;
import org.antwalk.entity.RouteStop;
import org.antwalk.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalTimeRepo extends JpaRepository<ArrivalTimeTable, RouteStop>{
    
    public List<ArrivalTimeTable> findAllByRouteStop_Stop(Stop stop);

}
