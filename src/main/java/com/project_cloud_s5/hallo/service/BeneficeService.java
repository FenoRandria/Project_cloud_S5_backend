package com.project_cloud_s5.hallo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.BeneficeDao;
import com.project_cloud_s5.hallo.model.State.Benefice;

@Service
public class BeneficeService {

    private final BeneficeDao dao;

    public BeneficeService(BeneficeDao dao) {
        this.dao = dao;
    }

    public List<Benefice> getBenefice(int mois, int anne) {
        try {
            return this.dao.getBenefice(anne, mois);
        } catch (Exception e) {
            throw e;
        }
    }
}
