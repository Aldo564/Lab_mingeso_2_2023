import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import styles from './style.module.css';
import HomeComponent from "./components/HomeComponent";
import VerProveedoresComponent from "./components/VerProveedoresComponent";
import IngresarProveedorComponent from './components/IngresarProveedorComponent';
import SubirAcopioComponent from "./components/SubirAcopioComponent";
import SubirPorcentajeComponent from "./components/SubirPorcentajeComponent";
import GenerarPlanillaComponent from "./components/GenerarPlanillaComponent";
import VerPlanillaComponent from "./components/VerPlanillaComponent";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes className={styles.container}>
          <Route path = "/" element = {<HomeComponent />}> </Route>
          <Route path = "/verProveedores" element  = {<VerProveedoresComponent />}> </Route>
          <Route path = "/ingresarProveedor" element  = {<IngresarProveedorComponent />}> </Route>
          <Route path = "/subirAcopio" element = {<SubirAcopioComponent />}> </Route> 
          <Route path = "/subirPorcentaje" element = {<SubirPorcentajeComponent />}> </Route>
          <Route path = "/generarPlanilla" element = {<GenerarPlanillaComponent />}> </Route>
          <Route path = "/verPlanilla" element = {<VerPlanillaComponent />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
