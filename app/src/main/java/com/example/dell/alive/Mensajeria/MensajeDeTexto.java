package com.example.dell.alive.Mensajeria;

/**
 * Created by DELL on 08/09/2017.
 */

public class MensajeDeTexto {
    private String id;
    private String mensaje;
    private int tipoMensaje;
    private String HoraDelMensaje;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getHoraDelMensaje() {
        return HoraDelMensaje;
    }

    public void setHoraDelMensaje(String horaDelMensaje) {
        HoraDelMensaje = horaDelMensaje;
    }

    public MensajeDeTexto() {

    }
}
