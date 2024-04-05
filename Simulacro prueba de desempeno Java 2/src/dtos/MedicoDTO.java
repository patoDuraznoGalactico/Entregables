package dtos;

public class MedicoDTO {
    private String nombre_medico;
    private String apellidos_medico;
    private String nombre_especialidad;

    public MedicoDTO() {
    }

    public String getNombre_medico() {
        return nombre_medico;
    }

    public void setNombre_medico(String nombre_medico) {
        this.nombre_medico = nombre_medico;
    }

    public String getApellidos_medico() {
        return apellidos_medico;
    }

    public void setApellidos_medico(String apellidos_medico) {
        this.apellidos_medico = apellidos_medico;
    }

    public String getNombre_especialidad() {
        return nombre_especialidad;
    }

    public void setNombre_especialidad(String nombre_especialidad) {
        this.nombre_especialidad = nombre_especialidad;
    }

    @Override
    public String toString() {
        return "MedicoDTO{" +
                "nombre_medico='" + nombre_medico + '\'' +
                ", apellidos_medico='" + apellidos_medico + '\'' +
                ", nombre_especialidad='" + nombre_especialidad + '\'' +
                '}';
    }
}
