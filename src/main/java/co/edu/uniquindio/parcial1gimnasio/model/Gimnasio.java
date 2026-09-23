package co.edu.uniquindio.parcial1gimnasio.model;

/**
 * Representa la información básica del gimnasio SmartGym.
 *
 * Implementa el patrón Singleton para garantizar
 * una única instancia del gimnasio durante la ejecución
 * de la aplicación.
 */
public final class Gimnasio {

    private static Gimnasio instancia;

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;

    private Gimnasio(String nombreComercial, String nit,
                     String direccion, String telefono,
                     String correoElectronico, String paginaWeb) {

        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.paginaWeb = paginaWeb;
    }

    /**
     * Obtiene la única instancia del gimnasio.
     *
     * @return instancia única de Gimnasio.
     */
    public static Gimnasio getInstance(
            String nombreComercial,
            String nit,
            String direccion,
            String telefono,
            String correoElectronico,
            String paginaWeb) {

        if (instancia == null) {
            instancia = new Gimnasio(
                    nombreComercial,
                    nit,
                    direccion,
                    telefono,
                    correoElectronico,
                    paginaWeb
            );
        }

        return instancia;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }
}