import React, {Component} from 'react';
import './App.css';

class IngresarProveedorComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            proveedor: null,
        };
    }

    changeCodigoProveedor = () => {
        this.setState(() => ({
            proveedor: this.proveedor.codigo
        }));
    };

    changeNombreProveedor = () => {
        this.setState(() => ({
            proveedor: this.proveedor.nombre
        }));
    };

    changeCategoriaProveedor = () => {
        this.setState(() => ({
            proveedor: this.proveedor.categoria
        }));
    };

    changeRetencionProveedor = () => {
        this.setState(() => ({
            proveedor: this.proveedor.categoria
        }));
    };

    render() {
        return (
            <div>
                <head>
                    <title>Proveedores</title>
                </head>
                <main>
                    <header>
                        <h1>Proveedores</h1>
                    </header>
                    <nav>
                        <ul>
                            <li><a href="/">Volver al menú principal</a></li>
                            <li><a href="/verProveedores">Ver proveedores</a></li>
                        </ul>
                    </nav>
                    <hi>Ingrese los datos del nuevo proveedor</hi>
                    <form action="{@/ingresarProveedor}" method={"POST"}>
                        <ul className="registration-form">
                            <li>
                                <label>Codigo</label>
                                <input type="text" placeholder="Ingrese el codigo del proveedor" id="codigo"
                                       name="codigo" required onChange={this.changeCodigoProveedor}/>
                            </li>
                            <li>
                                <label>Nombre</label>
                                <input type="text" placeholder="Ingrese el nombre del proveedor" id="nombre"
                                       name="nombre" required onChange={this.changeNombreProveedor}/>
                            </li>
                            <li>
                                <label>Categoria</label>
                                <select id="categoria" name="categoria" required onChange={this.changeCategoriaProveedor}>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                </select>
                            </li>
                            <li>
                                <label>Retencion</label>
                                <input type="radio" name="retencion" value="true" required onChange={this.changeRetencionProveedor}/> Si
                                <input type="radio" name="retencion" value="false" required onChange={this.changeRetencionProveedor}/> No
                            </li>
                            <li className="btn-secondary-container">
                                <input className="btn-secondary" type="submit" value="Ingresar proveedor"/>
                            </li>
                        </ul>
                    </form>
                    <footer>
                        <p>Derechos reservados MilkStgo</p>
                    </footer>
                </main>
            </div>
        );
    }
}

export default IngresarProveedorComponent;