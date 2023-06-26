import React, {Component} from 'react';
import styles from '../style.module.css';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';



class GenerarPlanillaComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            proveedores: [],
            redirect: false,
        }
    }
    
    componentDidMount()
    {
        const json = fetch("http://localhost:8080/proveedor/obtener")
        .then((response) => response.json())
        .then((data) => this.setState({ proveedores: data }));
    }

    changeCodigo = event => {
        this.setState({
            codigo: event.target.value
        });
    }

    generarPlanilla = async event => {
        event.preventDefault();
        const MySwal = withReactContent(Swal);

        const flag = await fetch("http://localhost:8080/planilla/generar/" + this.state.codigo)
        .then((response) => response.json())

        console.log("flag: " + flag);

        if(flag == true)    
        {
            this.setState({redirect: true})
            console.log("Redirect: " + this.state.redirect);
            window.location.href = "/verPlanilla";
        }
        else
        {
            MySwal.fire({
                title: <strong>Error</strong>,
                html: <i>No se han encontrado datos suficientes para generar una planilla</i>,
                icon: 'error'
            });
        }
    }

    redireccionar = () => {
        if(this.state.redirect){
            window.location.href = "/verPlanilla";
        }
    }

    render() {
        return (
            <div>
                <div>
                    <header>
                        <h1>Planillas de pago</h1>
                    </header>
                    <nav>
                        <ul>
                            <li><a href="/">Volver al menú principal</a></li>
                        </ul>
                    </nav>
                    <form onSubmit={this.generarPlanilla}>
                        <br/>
                        <h3>Seleccione al proveedor para generar planilla de pago</h3>
                        <select name="codigo" onChange={this.changeCodigo}>
                            <option value="">Seleccione un codigo</option>
                            {this.state.proveedores.map((proveedor) =>(
                                <option key={proveedor.ID_PROVEEDOR}>
                                    {proveedor.codigo}
                                </option>
                            ))}
                        </select>
                        <div> 
                            <button className={styles.fileuploadbutton}> Cargar planilla </button>    
                        </div>
                    </form>
                    <footer>
                        <p>Derechos reservados MilkStgo</p>
                    </footer>
                </div>
            </div>
        );
    }
}

export default GenerarPlanillaComponent;