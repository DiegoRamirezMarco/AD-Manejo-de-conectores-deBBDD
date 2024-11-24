package model;

public class Coche {
    private int id;
    private String marca;
    private int matricula;
    private int plazas;

    public Coche(String marca, int matricula, int plazas) {
        this.marca = marca;
        this.matricula = matricula;
        this.plazas = plazas;
    }

    public Coche(int id, String marca, int matricula, int plazas) {
        this.id = id;
        this.marca = marca;
        this.matricula = matricula;
        this.plazas = plazas;
    }

    public Coche() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", matricula=" + matricula +
                ", plazas=" + plazas +
                '}';
    }
}
