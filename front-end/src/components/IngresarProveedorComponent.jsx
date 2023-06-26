import React, {Component} from 'react';
import '../App.css';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

class IngresarProveedorComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            codigo: '',
            nombre: '',
            categoria: '',
            retencion: null,
        };
    }

    changeCodigoProveedor = event => {
        this.setState({
            codigo: event.target.value
        });
        console.log(this.state.codigo)
    };

    changeNombreProveedor = event => {
        this.setState({
            nombre: event.target.value
        });
        console.log(this.state.nombre)
    };

    changeCategoriaProveedor = event => {
        this.setState({
            categoria: event.target.value
        });
        console.log(this.state.categoria)
    };

    changeRetencionProveedor = event => {
        this.setState({
            retencion: event.target.value
        });
        console.log(this.state.retencion)
    };

    guardarProveedor = event => {
        event.preventDefault();
        const MySwal = withReactContent(Swal);
        if(this.state.codigo == '')
        {
            MySwal.fire({
                title: <strong>Error en codigo</strong>,
                html: <i>Porfavor escriba un codigo valido</i>,
                icon: 'error'
            });
        }
        else if(this.state.nombre == '')
        {
            MySwal.fire({
                title: <strong>Error en nombre</strong>,
                html: <i>Porfavor escriba un nombre valido</i>,
                icon: 'error'
            });
        }
        else if(this.state.categoria == '')
        {
            MySwal.fire({
                title: <strong>Error en categoria</strong>,
                html: <i>Porfavor escriba una categoria valido</i>,
                icon: 'error'
            });
        }
        else if(this.state.retencion == null)
        {
            MySwal.fire({
                title: <strong>Error en retencion</strong>,
                html: <i>Porfavor escoga una opción</i>,
                icon: 'error'
            });
        }
        else
        {
            fetch(`http://localhost:8080/proveedor/ingresar?codigo=${this.state.codigo}&nombre=${this.state.nombre}&categoria=${this.state.categoria}&retencion=${this.state.retencion}`,
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title: '' }) 
            })
            .catch(err => console.error(err));

            MySwal.fire({
                title: <strong>Exito!</strong>,
                html: <i>Se a añadido el proveedor correctamente</i>,
                icon: 'success'
            });

            this.setState({codigo: ''});
            this.setState({nombre: ''});
            this.setState({categoria: ''});
            this.setState({retencion: null});

            event.target.reset();
        }
        
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
                <div>
                    <h1>Ingrese los datos del nuevo proveedor</h1>
                    <body>
                        <form onSubmit={this.guardarProveedor}>
                            <ul class="registration-form">
                                <li>
                                    <label>Codigo</label>
                                    <input type="text" placeholder={this.state.textoCodigo} id="codigo"
                                        name="codigo" onChange={this.changeCodigoProveedor}/>
                                </li>
                                <li>
                                    <label>Nombre</label>
                                    <input type="text" placeholder="Ingrese el nombre del proveedor" id="nombre"
                                        name="nombre" onChange={this.changeNombreProveedor}/>
                                </li>
                                <li>
                                    <label>Categoria</label>
                                    <select id="categoria" name="categoria" onChange={this.changeCategoriaProveedor}>
                                        <option value="" selected>Seleccione una categoria</option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                    </select>
                                </li>
                                <li>
                                <label>Retencion</label>
                                    <input type="radio" name="retencion" value="true" onChange={this.changeRetencionProveedor}/> Si
                                    {'\u00A0'}{'\u00A0'}{'\u00A0'}{'\u00A0'}{'\u00A0'}
                                    <input type="radio" name="retencion" value="false" onChange={this.changeRetencionProveedor}/> No
                                </li>
                                <li class="btn-secondary-container">
                                    <input class="btn-secondary" type="submit" value="Ingresar proveedor"/>
                                </li>
                            </ul> 
                        </form>
                        <footer>
                            <p>Derechos reservados MilkStgo</p>
                        </footer>
                    </body>
                </div>
            </div>
        );
    }
}

export default IngresarProveedorComponent;
