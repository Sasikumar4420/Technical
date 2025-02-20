package com.demo.xyzmc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.xyzmc.entity.PlanType;
import com.demo.xyzmc.entity.TalkTimePlan;

@Repository
public interface TalkTimePlansRepository extends JpaRepository<TalkTimePlan, Long> {

	Optional<TalkTimePlan> findByPlanType(PlanType planType);

}
