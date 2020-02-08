package com.bidomi.converter.Repositories;

import com.bidomi.converter.Models.Convertation;
import com.bidomi.converter.Models.DaylyCurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ConvertationRepository extends JpaRepository <Convertation, Long> {
    ArrayList<Convertation> findAllByDateAndSourceCurrencyAndTargetCurrency(
            String date,String sourceCurrency, String targetCurrency);
}
