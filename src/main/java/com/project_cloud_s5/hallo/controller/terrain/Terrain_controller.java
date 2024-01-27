package com.project_cloud_s5.hallo.controller.terrain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project_cloud_s5.hallo.controller.exception.Gestion_exception;
import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;
import com.project_cloud_s5.hallo.model.dto.TerrainDTO;
import com.project_cloud_s5.hallo.model.terrain.Terrain;
import com.project_cloud_s5.hallo.service.Terrain_serve;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/terrains")
public class Terrain_controller {

    private static final Logger logger = LoggerFactory.getLogger(Terrain_controller.class);
    private final Terrain_serve service;
    public Terrain_controller(Terrain_serve servivce)
    {
        this.service = servivce;
    }
    @GetMapping
    public ResponseEntity<Object> get_all_Terrains() {
         try {
            List<Terrain> list_Terrains =  service.getTerrains();
            logger.info("Liste des terrains récupérée avec succès : {}", list_Terrains);
            return Gestion_exception.generateResponse("Liste terrains", HttpStatus.OK, list_Terrains);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la récupération des terrains : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la récupération des terrains", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @GetMapping("/invalid")
    public ResponseEntity<Object> get_all_Terrains_invalid() {
        try {
           List<Terrain> list_Terrains =  service.getTerrainsInvalid();
           logger.info("Liste des terrains récupérée avec succès : {}", list_Terrains);
           return Gestion_exception.generateResponse("Liste terrains", HttpStatus.OK, list_Terrains);
       } catch (Exception e) {
           logger.error("Une erreur s'est produite lors de la récupération des terrains : {}", e.getMessage());
           return Gestion_exception.generateResponse("Erreur survenue lors de la récupération des terrains", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
       }
   }

   @PutMapping("/{id}")
    public ResponseEntity<Object> validationTerrain(@PathVariable("id") String id) throws Exception{
        System.out.println("id_terrain---"+id);
        try {
            int id_terrain = Integer.parseInt(id);
            return Gestion_exception.generateResponse("validation terrain ok", HttpStatus.OK,service.validateTerrain(Integer.toString(id_terrain)));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la validation terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la validation terrain by id", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> insertTerrain(@RequestBody TerrainDTO terrain) throws Exception
    {
        Terrain t = new Terrain(terrain.getId_proprietaire(),terrain.getDesc_terrain(), terrain.getCoord_location(), terrain.getSurface());
        try {
            int result = service.insertTerrain(t,terrain.getNombre_parcelle());
            System.out.println(result);
            return Gestion_exception.generateResponse("enregistrement terrain", HttpStatus.OK,result);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de l'enregestrement terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de l'enregestrement terrain", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTerrainById(@PathVariable("id") String id) throws Exception{
        try {
            Integer.parseInt(id);
           Terrain terrain = service.getTerrainById(id);
            return Gestion_exception.generateResponse("recuperation terrain ok", HttpStatus.OK,terrain);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la recuperation terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la recuperation terrain by id", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @DeleteMapping("{idterrain}")
    public ResponseEntity<Object> deleteterrain(@PathVariable String idterrain)throws Exception{
        
        try {
            return Gestion_exception.generateResponse("suppression terrain ok", HttpStatus.OK, service.deleteterrain(idterrain));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la suppression terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la suppression terrain", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    } 

    @PutMapping("description")
    public ResponseEntity<Object> updateTerrainDesc(@RequestBody TerrainDTO terrain)throws Exception{
        try {
            return Gestion_exception.generateResponse("mise à jour terrain ok", HttpStatus.OK, service.updateTerrainDesc(terrain.getDesc_terrain(), String.valueOf(terrain.getId_terrain())));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la mise à jour terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la mise à jour terrain", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PutMapping("/surface")
    public ResponseEntity<Object> updateTerrainSurface(@RequestBody TerrainDTO terrain)throws Exception{
        try {
            return Gestion_exception.generateResponse("mise à jour terrain ok", HttpStatus.OK, service.updateTerrainSurface(terrain.getLongueur(), terrain.getLargeur(), String.valueOf(terrain.getId_terrain())));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la mise à jour terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la mise à jour terrain", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
    @GetMapping("/photo/{id}")
    public ResponseEntity<Object> getPhotoTerrainById(@PathVariable("id") String id) throws Exception{
        try {
            int id_terrain = Integer.parseInt(id);
            List<String> terrain = service.getTerrainsPhotos(Integer.toString(id_terrain));
            return Gestion_exception.generateResponse("recuperation photo terrain ok", HttpStatus.OK,terrain);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la recuperation photo terrain : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la recuperation photo terrain by id", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @DeleteMapping("/photo")
    public ResponseEntity<Object> deletepicture(@RequestBody TerrainDTO terrain)throws Exception{     
        try {
            return Gestion_exception.generateResponse("suppression photo terrain ok", HttpStatus.OK, service.deletepicture(String.valueOf(terrain.getId_photos_terrain()), String.valueOf(terrain.getId_terrain())));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la suppression photo : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de la suppression photo", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    } 

    @PostMapping("/photo")
    public ResponseEntity<Object> insertpicture(@RequestBody TerrainDTO terrain)throws Exception {
        try {
            return Gestion_exception.generateResponse("insert photo terrain ok", HttpStatus.OK, service.insertpicture(terrain.getPhoto(), String.valueOf(terrain.getId_terrain())));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de l'enregistrement photo : {}", e.getMessage());
            return Gestion_exception.generateResponse("Erreur survenue lors de l'enregistrement photo", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }


}
