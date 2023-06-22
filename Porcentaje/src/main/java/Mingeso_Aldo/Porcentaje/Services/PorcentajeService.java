package Mingeso_Aldo.Porcentaje.Services;

import Mingeso_Aldo.Porcentaje.Entities.PorcentajeEntity;
import Mingeso_Aldo.Porcentaje.Repositories.PorcentajeRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class PorcentajeService {
    @Autowired
    PorcentajeRepository porcentajeRepository;

    Integer ID_ARCHIVO_PORCENTAJE = 1;

    private final Logger logg = LoggerFactory.getLogger(PorcentajeService.class);

    @Generated
    public boolean guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    @Generated
    public boolean leerCsvPorcentaje(String direccion){
        String texto = "";
        BufferedReader bf = null;
        //dataPorcentajeRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDBPorcentaje(ID_ARCHIVO_PORCENTAJE,bfRead.split(";")[0], Integer.parseInt(bfRead.split(";")[1]), Integer.parseInt(bfRead.split(";")[2]));
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
            ID_ARCHIVO_PORCENTAJE++;
            return true;
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
        return false;
    }

    public void guardarDataPorcentaje(PorcentajeEntity data){
        porcentajeRepository.save(data);
    }

    public void guardarDataDBPorcentaje(int id_archivo,String cod_proveedor, int grasa, int solido){
        PorcentajeEntity newData = new PorcentajeEntity();
        newData.setID_archivo(id_archivo);
        newData.setCod_proveedor(cod_proveedor);
        newData.setGrasa(grasa);
        newData.setSolido(solido);
        guardarDataPorcentaje(newData);
    }

    public ArrayList<PorcentajeEntity> obtenerPorcentajes() {
        return (ArrayList<PorcentajeEntity>) porcentajeRepository.findAll();
    }

    public int obtenerCantArchivos(ArrayList<PorcentajeEntity> porcentajes)
    {
        int cant = 0;
        ArrayList<Integer> aux = new ArrayList<>();
        for (PorcentajeEntity porcentaje:porcentajes)
        {
            if (!(aux.contains(porcentaje.getID_archivo())))
            {
                aux.add(porcentaje.getID_archivo());
            }
        }

        cant = aux.size();
        return cant;
    }

    public int obtenerGrasa(String codigo)
    {
        ArrayList<PorcentajeEntity> porcentajes = porcentajeRepository.getAll();
        int id = obtenerCantArchivos(porcentajes);

        PorcentajeEntity porcentaje_Actual_Filtrado = porcentajeRepository.findFiltro(id, codigo);
        int grasa = porcentaje_Actual_Filtrado.getGrasa();

        return grasa;
    }

    public int obtenerSolido(String codigo)
    {
        ArrayList<PorcentajeEntity> porcentajes = porcentajeRepository.getAll();
        int id = obtenerCantArchivos(porcentajes);

        PorcentajeEntity porcentaje_Actual_Filtrado = porcentajeRepository.findFiltro(id, codigo);
        int solido = porcentaje_Actual_Filtrado.getSolido();

        return solido;
    }

    public String obtenerDiferenciaGrasa(String codigo)
    {
        ArrayList<PorcentajeEntity> porcentajes = porcentajeRepository.getAll();
        int id = obtenerCantArchivos(porcentajes);
        String variacion = "0";

        if (id == 1)
        {
            variacion = "0";
        }
        else
        {
            PorcentajeEntity porcentaje_Actual = porcentajeRepository.findFiltro(id, codigo);
            PorcentajeEntity porcentaje_Pasado = porcentajeRepository.findFiltro(id-1, codigo);

            if (porcentaje_Pasado != null)
            {
                int porcentaje = (porcentaje_Actual.getGrasa() * 100)/porcentaje_Pasado.getGrasa();
                variacion = Integer.toString(porcentaje);
            }
        }

        return variacion;
    }

    public String obtenerDiferenciaSolido(String codigo)
    {
        ArrayList<PorcentajeEntity> porcentajes = obtenerPorcentajes();
        int id = obtenerCantArchivos(porcentajes);
        String variacion = "0";

        if (id == 1)
        {
            variacion = "0";
        }
        else
        {
            PorcentajeEntity porcentaje_Actual = porcentajeRepository.findFiltro(id, codigo);
            PorcentajeEntity porcentaje_Pasado = porcentajeRepository.findFiltro(id-1, codigo);

            if (porcentaje_Pasado != null)
            {
                int porcentaje = (porcentaje_Actual.getSolido() * 100)/porcentaje_Pasado.getSolido();
                variacion = Integer.toString(porcentaje);
            }
        }

        return variacion;
    }

    @Generated
    public void eliminarData(ArrayList<PorcentajeEntity> datas)
    {
        porcentajeRepository.deleteAll(datas);
    }
}
