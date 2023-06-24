import React, {Component} from 'react';
import '../App.css';
import style from '../style.module.css';
import Form from 'react-bootstrap/Form';

class IngresarProveedorComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            codigo: '',
            nombre: '',
            categoria: '',
            retencion: null,
            exito: false,
        };
    }

    changeCodigoProveedor = () => {
        this.setState(() => ({
            codigo: this.codigo
        }));
    };

    changeNombreProveedor = () => {
        this.setState(() => ({
            nombre: this.nombre
        }));
    };

    changeCategoriaProveedor = () => {
        this.setState(() => ({
            categoria: this.categoria
        }));
    };

    changeRetencionProveedor = () => {
        this.setState(() => ({
            retencion: this.retencion
        }));
    };

    guardarProveedor = () => {
        const flag = fetch("http://localhost:8080/proveedor/ingresar")
        .then((response) => response.json())
        .then((data) => this.setState({ exito: data }))
    }

    cambiarExito = () => {
        this.setState(() => ({
            exito: false
        }));
    }

    render() {
        return (
            <div>
                <head>
                    <title>Proveedores</title>
                </head>
                <header>
                    <h1>Proveedores</h1>    
                </header>
                <nav>
                    <ul>
                        <li><a href="/">Volver al menú principal</a></li>
                        <li><a href="/verProveedores">Ver proveedores</a></li>
                    </ul>
                </nav>
                
                {this.state.exito? (
                    <div>
                        <h1>Proveedor ingresado correctamente</h1>
                        <input class="btn-secondary" value="Ingresar otro proveedor" onClick={this.cambiarExito}/> 
                    </div>
                ) : (
                    <div>
                        <h1>Ingrese los datos del nuevo proveedor</h1>
                        <body>
                            
                            <ul class="registration-form">
                                <li>
                                    <label>Codigo</label>
                                    <input type="text" placeholder="Ingrese el codigo del proveedor" id="codigo"
                                        name="codigo" onChange={this.changeCodigoProveedor} required/>
                                </li>
                                <li>
                                    <label>Nombre</label>
                                    <input type="text" placeholder="Ingrese el nombre del proveedor" id="nombre"
                                        name="nombre" onChange={this.changeNombreProveedor} required/>
                                </li>
                                <li>
                                    <label>Categoria</label>
                                    <select id="categoria" name="categoria" onChange={this.changeCategoriaProveedor} required>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                    </select>
                                </li>
                                <li>
                                <label>Retencion</label>
                                    <input type="radio" name="retencion" value="true" onChange={this.changeRetencionProveedor} required/> Si
                                    <input type="radio" name="retencion" value="false" onChange={this.changeRetencionProveedor} required/> No
                                </li>
                                <li class="btn-secondary-container">
                                    <input class="btn-secondary" type="submit" value="Ingresar proveedor"/>
                                </li>
                            </ul> 
                            <footer>
                                <p>Derechos reservados MilkStgo</p>
                            </footer>
                        </body>
                    </div>     
                )}    
            </div>
        );
    }
}

export default IngresarProveedorComponent;
