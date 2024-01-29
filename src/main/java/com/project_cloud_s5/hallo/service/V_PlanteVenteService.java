package com.project_cloud_s5.hallo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.V_PlanteUtiliseDao;
import com.project_cloud_s5.hallo.model.State.V_planteUtilise;

@Service
public class V_PlanteVenteService {

    private final V_PlanteUtiliseDao daoPlante;
    

    public V_PlanteVenteService(V_PlanteUtiliseDao daoPlante) {
        this.daoPlante = daoPlante;
    }

    public List<V_planteUtilise> getPlanteUtilise() {
        try {
            return this.daoPlante.getPlanteutilise();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<V_planteUtilise> getTopPlante(int limit) {
        try {
            return this.daoPlante.getPlanteutiliseTop(limit);
        } catch (Exception e) {
            throw e;
        }

    }

}
