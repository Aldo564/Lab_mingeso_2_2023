import React, {Component} from 'react';
import styles from '../style.module.css';


class VerPlanillaComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            planilla: [],
        }
    }
    
    componentDidMount()
    {
        fetch("http://localhost:8080/planilla/obtener")
        .then((response) => response.json())
        .then((data) => this.setState({ planilla: data }));
    }

    
    render() {
        return (
            <div>
                <header>
                    <h1>Proveedores</h1>
                </header>
                <nav>
                    <ul>
                        <li><a href="/verProveedores">Ver Proveedores</a></li>
                        <li><a href="/subirAcopio">Importar datos de acopio</a></li>
                        <li><a href="/subirPorcentaje">Importar datos de porcentajes de grasa y solido</a></li>
                        <li><a href="/generarPlanilla">Generar planilla de pago</a></li>
                    </ul>
                </nav>
                <div>
                    <h1>Planilla de pago</h1>
                    <table className={styles.contentTable}>
                        <tbody>
                            <tr>
                                <th>Quincena</th>
                                <td> {this.state.planilla.fecha}</td>
                            </tr>
                            <tr>
                                <th>Codigo</th>
                                <td> {this.state.planilla.codigo}</td>
                            </tr>
                            <tr>
                                <th>Nombre</th>
                                <td> {this.state.planilla.nombre}</td>
                            </tr>
                            <tr>
                                <th>Kls de leche</th>
                                <td> {this.state.planilla.kg_leche}</td>
                            </tr>
                            <tr>
                                <th>Dias que entrego leche</th>
                                <td> {this.state.planilla.dias}</td>
                            </tr>
                            <tr>
                                <th>Promedio de kls por dia</th>
                                <td> {this.state.planilla.promedio_kg}</td>
                            </tr>
                            <tr>
                                <th>Variacion de kls de leche</th>
                                <td> {this.state.planilla.variacion_leche}</td>
                            </tr>
                            <tr>
                                <th>Porcentaje de grasa actual</th>
                                <td> {this.state.planilla.grasa}</td>
                            </tr>
                            <tr>
                                <th>Variacion de grasa</th>
                                <td> {this.state.planilla.variacion_grasa}</td>
                            </tr>
                            <tr>
                                <th>Porcentaje de solidos totales actual</th>
                                <td> {this.state.planilla.solidos}</td>
                            </tr>
                            <tr>
                                <th>Variacion de solidos totales</th>
                                <td> {this.state.planilla.variacion_st}</td>
                            </tr>
                            <tr>
                                <th>Pago por kls de leche</th>
                                <td> {this.state.planilla.pago_leche}</td>
                            </tr>
                            <tr>
                                <th>Pago por porcentaje de grasa</th>
                                <td> {this.state.planilla.pago_grasa}</td>
                            </tr>
                            <tr>
                                <th>Pago por porcentaje de solidos totales</th>
                                <td> {this.state.planilla.pago_solidos}</td>
                            </tr>
                            <tr>
                                <th>Pago por bonificacion de frecuencia</th>
                                <td> {this.state.planilla.bonif_freq}</td>
                            </tr>
                            <tr>
                                <th>Descuento por variacion de kls de leche</th>
                                <td> {this.state.planilla.dct_variacion_leche}</td>
                            </tr>
                            <tr>
                                <th>Descuento por variacion de porcentaje de grasa</th>
                                <td> {this.state.planilla.dct_variacion_grasa}</td>
                            </tr>
                            <tr>
                                <th>Descuento por variacion de porcentaje de solidos totales</th>
                                <td> {this.state.planilla.dct_variacion_st}</td>
                            </tr>
                            <tr>
                                <th>Pago total</th>
                                <td> {this.state.planilla.pago_total}</td>
                            </tr>
                            <tr>
                                <th>Monto de retencion</th>
                                <td> {this.state.planilla.monto_retencion}</td>
                            </tr>
                            <tr>
                                <th>Monto final</th>
                                <td> {this.state.planilla.monto_final}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <footer>
                    <p>Derechos reservados MilkStgo</p>
                </footer>
            </div>
        );
    }
}

export default VerPlanillaComponent;