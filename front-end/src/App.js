import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import styles from './style.module.css';
import HomeComponent from "./components/HomeComponent";
import VerProveedoresComponent from "./components/VerProveedoresComponent";
import IngresarProveedorComponent from './components/IngresarProveedorComponent';
//import SubirAcopioComponent from "./components/SubirAcopioComponent";
//import SubirPorcentajeComponent from "./components/SubirPorcentajeComponent";
//import GenerarPlanillaComponent from "./components/GenerarPlanillaComponent";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes className={styles.container}>
          <Route path = "/" element = {<HomeComponent />}> </Route>
          <Route path = "/verProveedores" element  = {<VerProveedoresComponent />}> </Route>
          <Route path = "/ingresarProveedor" element  = {<IngresarProveedorComponent />}> </Route>
          {/*<Route path = "/subirAcopio" exact component = {<SubirAcopioComponent />}> </Route> 
          <Route path = "/subirPorcentaje" exact component = {<SubirPorcentajeComponent />}> </Route>
          <Route path = "/generarPlanilla" exact component = {<GenerarPlanillaComponent />}> </Route>*/}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
