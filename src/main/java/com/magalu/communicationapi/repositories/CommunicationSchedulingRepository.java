package com.magalu.communicationapi.repositories;

import com.magalu.communicationapi.models.CommunicationScheduling;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommunicationSchedulingRepository extends JpaRepository<CommunicationScheduling, Long> {
}
