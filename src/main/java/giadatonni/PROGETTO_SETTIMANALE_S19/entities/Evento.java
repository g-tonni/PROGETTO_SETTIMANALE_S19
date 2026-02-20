package giadatonni.PROGETTO_SETTIMANALE_S19.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    @Column(name = "evento_id")
    private UUID eventoId;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false, length = 500)
    private String descrizione;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String luogo;

    @Column(name = "max_ingressi", nullable = false)
    private int maxIngressi;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Utente organizzatore;

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int maxIngressi, Utente organizzatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.maxIngressi = maxIngressi;
        this.organizzatore = organizzatore;
    }

    public UUID getEventoId() {
        return eventoId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public int getMaxIngressi() {
        return maxIngressi;
    }

    public void setMaxIngressi(int maxIngressi) {
        this.maxIngressi = maxIngressi;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }
}
