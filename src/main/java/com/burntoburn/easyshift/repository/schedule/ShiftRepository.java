package com.burntoburn.easyshift.repository.schedule;

import com.burntoburn.easyshift.entity.schedule.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
