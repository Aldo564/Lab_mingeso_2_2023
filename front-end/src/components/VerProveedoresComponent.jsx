import React, {Component} from 'react';
import styles from '../style.module.css';


class verProveedoresComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            proveedores: [],
        }
    }
    
    componentDidMount()
    {
        fetch("http://localhost:8080/proveedor/obtener")
        .then((response) => response.json())
        .then((data) => this.setState({ proveedores: data }));
    }

    
    render() {
        return (
            <div>
                <header>
                    <h1>Proveedores</h1>
                </header>
                <nav>
                    <ul>
                        <li><a href="/">Volver al menú principal</a></li>
                        <li><a href="/ingresarProveedor">Ingresar nuevo proveedor</a></li>
                    </ul>
                </nav>
                <div>
                    <h1>Lista de proveedores</h1>
                    <table className={styles.contentTable}>
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>Categoría</th>
                                <th>Retención</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.proveedores.map((proveedor) =>(
                                <tr key={proveedor.ID_PROVEEDOR}>
                                    <td> {proveedor.codigo} </td>
                                    <td> {proveedor.nombre} </td>
                                    <td> {proveedor.categoria} </td>
                                    {proveedor.retencion? (
                                        <td> Sí </td>
                                    ) : (
                                        <td> No </td>
                                    )}
                                </tr>
                            ))}
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

export default verProveedoresComponent;