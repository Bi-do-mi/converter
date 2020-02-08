package com.bidomi.converter.Repositories;

import com.bidomi.converter.Models.DaylyCurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursRepository extends JpaRepository<DaylyCurs, Long> {

    DaylyCurs findByDate(String date);
}
