package com.project_cloud_s5.hallo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.V_PlanteUtiliseDao;
import com.project_cloud_s5.hallo.model.State.V_planteUtilise;
import com.project_cloud_s5.hallo.model.State.V_statistique_globale;

@Service
public class V_PlanteVenteService {

    private final V_PlanteUtiliseDao daoPlante;
    

    public V_PlanteVenteService(V_PlanteUtiliseDao daoPlante) {
        this.daoPlante = daoPlante;
    }

    public List getPlanteUtilise() {
        try {
            List<V_planteUtilise> list = this.daoPlante.getPlanteutilise();
            List vente_par_plante = new ArrayList<>();
            List nom_plante = new ArrayList<>();
            List all = new ArrayList<>();
            for (V_planteUtilise v_planteUtilise : list) {
                vente_par_plante.add(v_planteUtilise.getNbr_vente());
                nom_plante.add(v_planteUtilise.getNom_plante());
            }
            all.add(list);
            all.add(vente_par_plante);
            all.add(nom_plante);
            return all;
        } catch (Exception e) {
            throw e;
        }
    }

    public List getTopPlante(int limit) {
        try {
            List<V_planteUtilise> list = this.daoPlante.getPlanteutiliseTop(limit);
            List vente_par_plante = new ArrayList<>();
            List nom_plante = new ArrayList<>();
            List all = new ArrayList<>();
            for (V_planteUtilise v_planteUtilise : list) {
                vente_par_plante.add(v_planteUtilise.getNbr_vente());
                nom_plante.add(v_planteUtilise.getNom_plante());
            }
            all.add(list);
            all.add(vente_par_plante);
            all.add(nom_plante);
            return all;
        } catch (Exception e) {
            throw e;
        }

    }
    public List<V_statistique_globale> getV_statistique_globale(){
        try {
            return this.daoPlante.getV_statistique_globale();
        } catch (Exception e) {
            throw e;
        }
    }

}
