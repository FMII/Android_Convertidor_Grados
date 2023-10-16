package Modals;

public class Kelvin extends Grados
{
    public Kelvin(Double valor) {
        this.setValor(valor);
        this.setUnidad("K");
    }
    public Kelvin parse(celsius C)
    {
        Double valor=(C.getValor()+273.15);
        return new Kelvin(valor);

    }
    public Kelvin parse(Fahrenheit F)
    {
        Double valor=((F.getValor() - 32)*5)/9+ 273.15;
        return new Kelvin(valor);
    }
}
