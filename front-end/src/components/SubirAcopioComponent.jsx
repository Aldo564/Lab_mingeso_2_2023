import React, {Component} from 'react';
import '../App.css';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import SubirAcopioService from '../services/SubirAcopioService';
import Form from 'react-bootstrap/Form';
import styles from '../style.module.css';

class SubirAcopioComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            file: null,
        };
        this.onFileChange = this.onFileChange.bind(this);
    }

    onFileChange(event) {
        this.setState({ file: event.target.files[0] });
    }

    onFileUpload = event => {
        event.preventDefault();
        const MySwal = withReactContent(Swal);
        MySwal.fire({
            title: <strong>¿Está seguro de que desea cargar el archivo csv?</strong>,
            html: <i>Tenga en cuenta que el archivo solo será cargado si su extension es '.csv' y si su formato es correcto.</i>,
            icon: 'warning',
            showConfirmButton: true,
            confirmButtonText: 'Si',
            confirmButtonColor: '#00FF00',
            showDenyButton: true,
            denyButtonText: 'No'
        }).then(respuesta=>{
            if(respuesta.isConfirmed){
                MySwal.fire({title: <strong> Archivo cargado correctamente!</strong>, icon: "success", timer: "3000"});
                const formData = new FormData();
                formData.append("file", this.state.file);
                SubirAcopioService.CargarArchivo(formData).then((res) => {});
            }
            else{
                MySwal.fire({title: <strong> Archivo no cargado</strong>, icon: "error"});
            }
        });
    };


    render() {
        return (
            <div>
                <head>
                    <title>Subir Archivo</title>
                </head>
                <div>
                    <header>
                        <h1>Subir archivo de acopio</h1>
                    </header>
                    <nav>
                    <ul>
                        <li><a href="/">Volver al menú principal</a></li>
                    </ul>
                    </nav>
                    <div>
                    <h1>Cargar archivo de acopio</h1>
                    <Form.Group className={styles.fileuploadcontainer} controlId="formFileLg">
                        <Form.Control type="file" size="lg" 
                        onChange={this.onFileChange} 
                        className={styles.fileuploadbutton}/>
                    </Form.Group>
                    <input className={styles.fileuploadbutton}
                         type="submit" value="Cargar el archivo a la Base de Datos" onClick={this.onFileUpload}></input>
                    </div>
                </div>
            </div>
        );
    }
}

export default SubirAcopioComponent;
