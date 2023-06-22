import React, {Component} from 'react';
import './App.css';

class HomeComponent extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <header>
                    <title>MilkStgo</title>
                    </header>
                <div>
                    <h1>MilkStgo 2023</h1>
                </div>
                <nav>
                    <ul>
                        <li><a href="/verProveedores">Ver Proveedores</a></li>
                        <li><a href="/subirArchivoAcopio">Importar datos de acopio</a></li>
                        <li><a href="/subirArchivoPorcentaje">Importar datos de porcentajes de grasa y solido</a></li>
                        <li><a href="/seleccionarPlanilla">Generar planilla de pago</a></li>
                    </ul>
                </nav>
                <main>
                    <div>
                        <table>
                            <tr>
                                <th>
                                    <h2>Bienvenidos!</h2>
                                    <p>Somos MilkStgo, una empresa dedicada a la producción de comestibles a base de leche, principalmente yogurt, mantequilla y quesos. Tenemos más de 40 años produciendo productos para el mercado nacional.</p>
                                </th>
                                <th>
                                    <a><img src="https://i.ibb.co/rQWMfbv/milkstgo.png" alt="milkstgo"> </img></a>
                                </th>
                            </tr>
                        </table>
                    </div>
                </main>
                <footer>
                    <p>Derechos reservados MilkStgo</p>
                </footer>
            </div>
        );
    }
}

export default HomeComponent;