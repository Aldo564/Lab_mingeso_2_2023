import axios from "axios";

const API_URL = "http://localhost:8080/porcentaje";

class SubirPorcentajeService{
    
    CargarArchivo(file){
        return axios.post(API_URL, file);
    }
}

export default new SubirPorcentajeService()