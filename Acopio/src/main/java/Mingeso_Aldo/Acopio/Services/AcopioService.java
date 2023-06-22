package Mingeso_Aldo.Acopio.Services;


import Mingeso_Aldo.Acopio.Entities.AcopioEntity;
import Mingeso_Aldo.Acopio.Repositories.AcopioRepository;
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
public class AcopioService {

    @Autowired
    AcopioRepository acopioRepository;

    Integer ID_ARCHIVO_ACOPIO = 1;

    public ArrayList<AcopioEntity> obtenerAcopios(){
        return (ArrayList<AcopioEntity>) acopioRepository.findAll();
    }

    private final Logger logg = LoggerFactory.getLogger(AcopioService.class);

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
    public boolean leerCsvAcopio(String direccion){
        String texto = "";
        BufferedReader bf = null;
        //dataAcopioRepository.deleteAll();
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
                    guardarDataDBAcopio(ID_ARCHIVO_ACOPIO, bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], Integer.parseInt(bfRead.split(";")[3]));
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
            ID_ARCHIVO_ACOPIO++;
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

    public void guardarDataAcopio(AcopioEntity data){
        acopioRepository.save(data);
    }

    public void guardarDataDBAcopio(Integer ID_archivo, String fecha, String turno, String proveedor, Integer kg_leche){
        AcopioEntity newData = new AcopioEntity();
        newData.setID_archivo(ID_archivo);
        newData.setFecha(fecha);
        newData.setTurno(turno);
        newData.setProveedor(proveedor);
        newData.setKg_leche(kg_leche);
        guardarDataAcopio(newData);
    }

    public int kgs_leche(String codigo)
    {
        int total_kgs = 0;
        ArrayList<AcopioEntity> acopios = acopioRepository.getAll();

        int id = obtenerCantArchivos(acopios);
        ArrayList<Integer> kgs_leche = acopioRepository.kgsFiltro(id, codigo);

        for (Integer kg:kgs_leche)
        {
            total_kgs = total_kgs + kg;
        }

        return total_kgs;
    }

    public int obtenerCantArchivos(ArrayList<AcopioEntity> acopios)
    {
        int cant = 0;
        ArrayList<Integer> aux = new ArrayList<>();
        for (AcopioEntity acopio:acopios)
        {
            if (!(aux.contains(acopio.getID_archivo())))
            {
                aux.add(acopio.getID_archivo());
            }
        }

        cant = aux.size();
        return cant;
    }

    public ArrayList<String> obtenerFechas(String codigo)
    {
        ArrayList<AcopioEntity> acopios = acopioRepository.getAll();
        int id = obtenerCantArchivos(acopios);

        ArrayList<String> fechas = acopioRepository.fechasFiltro(id, codigo);

        return fechas;
    }

    public ArrayList<String> obtenerVariacionLeche(String codigo)
    {
        ArrayList<String> salida = new ArrayList<>();
        String variacion = "0";
        String variacionNum = "0";

        ArrayList<AcopioEntity> acopios = acopioRepository.getAll();

        int cant_Archivos = obtenerCantArchivos(acopios);

        if (cant_Archivos != 1)
        {
            ArrayList<AcopioEntity> acopio_Actual_Filtrado = acopioRepository.findFiltro(cant_Archivos, codigo);
            ArrayList<AcopioEntity> acopio_Pasado_Filtrado = acopioRepository.findFiltro(cant_Archivos-1, codigo);

            if (!(acopio_Pasado_Filtrado.isEmpty()))
            {
                int kilos_Actual = 0;
                int kilos_pasado = 0;

                for(AcopioEntity acopio:acopio_Actual_Filtrado)
                {
                    kilos_Actual = kilos_Actual + acopio.getKg_leche();
                }

                for(AcopioEntity acopio:acopio_Pasado_Filtrado)
                {
                    kilos_pasado = kilos_pasado + acopio.getKg_leche();
                }

                variacionNum = Integer.toString(kilos_Actual-kilos_pasado);

                int porcentaje = (kilos_Actual * 100)/kilos_pasado;
                variacion = Integer.toString(porcentaje);
            }
        }

        salida.add(variacionNum);
        salida.add(variacion);

        return salida;
    }

    public ArrayList<Boolean> obtenerTurnos(String codigo)
    {
        ArrayList<Boolean> salida = new ArrayList<>();
        ArrayList<AcopioEntity> acopios = acopioRepository.getAll();
        int id = obtenerCantArchivos(acopios);

        ArrayList<String> turnos = acopioRepository.findTurnos(id, codigo);

        if (turnos.contains("M"))
        {
            salida.add(true);
        }
        else
        {
            salida.add(false);
        }

        if (turnos.contains("T"))
        {
            salida.add(true);
        }
        else
        {
            salida.add(false);
        }

        return salida;
    }

    public void eliminarData(ArrayList<AcopioEntity> datas)
    {
        acopioRepository.deleteAll(datas);
    }
}
