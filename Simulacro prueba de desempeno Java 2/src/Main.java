import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import database.ConfigDB;
import entity.Cita;
import model.EspecialidadModel;

import javax.swing.*;
import javax.swing.text.html.Option;

public class Main {
    public static void main(String[] args) {
        String option;
        do {
            option = JOptionPane.showInputDialog("""
                    HOSPITAL
                                        
                    1. Especialidades.
                    2. Médicos.
                    3. Pacientes.
                    4. Citas.
                                        
                    5. Salir.
                    """);

            switch (option) {
            case "1":
                String optio;
                optio = JOptionPane.showInputDialog("""
                        1. Ingresar una nueva especialidad.
                        2. Listar especialidades.
                        3. Actualizar especialidad.
                        4. Eliminar especialidad.
                        
                        0. Salir.
                        """);
                    switch (optio) {
                        case "1":
                            EspecialidadController.create();
                            break;

                        case "2":
                            JOptionPane.showMessageDialog(null,EspecialidadController.getAll());
                            break;

                        case "3":
                            EspecialidadController.update();
                            break;

                        case "4":
                            EspecialidadController.delete();
                            break;
                        case "0":
                            break;
                    }
                break;

            case "2":
                String opti;
                opti = JOptionPane.showInputDialog("""
                        1. Ingresar un nuevo medico.
                        2. Listar medicos.
                        3. Actualizar medico.
                        4. Eliminar medico.
                        
                        0. Salir.
                        """);
                switch (opti) {
                    case "1":
                        MedicoController.create();
                        break;

                    case "2":
                        String opti1;
                        opti1 = JOptionPane.showInputDialog("""
                        1. Listar todos.
                        2. Listar por especialidad.
                        
                        0. Salir.
                        """);
                        switch (opti1) {
                            case "1":
                                JOptionPane.showMessageDialog(null,MedicoController.getAll());
                                break;
                            case "2":
                                JOptionPane.showMessageDialog(null,MedicoController.getAllByEsp());
                                break;
                            case "0":
                                break;
                        }

                        break;

                    case "3":
                        MedicoController.update();
                        break;

                    case "4":
                        MedicoController.delete();
                        break;
                    case "0":
                        break;
                }
                break;

            case "3":
                String opt;
                opt = JOptionPane.showInputDialog("""
                        1. Ingresar un nuevo paciente.
                        2. Listar pacientes.
                        3. Actualizar paciente.
                        4. Eliminar paciente.
                        
                        0. Salir.
                        """);
                switch (opt) {
                    case "1":
                        PacienteController.create();
                        break;

                    case "2":
                        String opti2;
                        opti2 = JOptionPane.showInputDialog("""
                        1. Listar todos.
                        2. buscar por numero de documento.
                        
                        0. Salir.
                        """);
                        switch (opti2) {
                            case "1":
                                JOptionPane.showMessageDialog(null,PacienteController.getAll());
                                break;
                            case "2":
                                JOptionPane.showMessageDialog(null,PacienteController.getByDoc());
                                break;
                            case "0":
                                break;
                        }
                        break;

                    case "3":
                        PacienteController.update();
                        break;

                    case "4":
                        PacienteController.delete();
                        break;
                    case "0":
                        break;
                }
                break;

            case "4":
                String op;
                op = JOptionPane.showInputDialog("""
                        1. Ingresar una nueva cita.
                        2. Listar citas.
                        3. Actualizar cita.
                        4. Eliminar cita.
                        
                        0. Salir.
                        """);
                switch (op) {
                    case "1":
                        CitaController.create();
                        break;

                    case "2":
                        String opti3;
                        opti3 = JOptionPane.showInputDialog("""
                        1. Listar todas.
                        2. buscar cita por fecha.
                        
                        0. Salir.
                        """);
                        switch (opti3) {
                            case "1":
                                JOptionPane.showMessageDialog(null,CitaController.getAll());
                                break;
                            case "2":
                                JOptionPane.showMessageDialog(null,CitaController.getCitasDate());
                                break;
                            case "0":
                                break;
                        }
                        break;

                    case "3":
                        CitaController.update();
                        break;

                    case "4":
                        CitaController.delete();
                        break;
                    case "0":
                        break;
                }
                break;

        }}while (!option.equals("5"));
    }
}
