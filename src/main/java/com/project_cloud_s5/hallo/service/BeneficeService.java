package com.project_cloud_s5.hallo.service;

import java.util.ArrayList;
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

    public List getBenefice(int mois, int anne) {
        List list = new ArrayList<>();
        try {
            List<Benefice> ls = this.dao.getBenefice(anne, mois);
            double[] data = new double[12];
            for(int i = 0;i<12;i++){
                data[i] = ls.get(i).getBenefice();
            }
            list.add(this.dao.getBenefice(anne, mois));
            list.add(this.dao.getAnne_exist());
            list.add(data);
            return list;       
        } catch (Exception e) {
            throw e;
        }
    }
}
