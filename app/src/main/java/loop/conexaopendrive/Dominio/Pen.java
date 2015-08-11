package loop.conexaopendrive.Dominio;

/**
 * Created by lobao on 07/08/2015.
 */
public class Pen {
    private String modelo;
    private String categoria;
    private int foto;


        public Pen(String m, String b, int f){
            modelo = m;
            categoria = b;
            foto = f;
        }
    public String getModelo(){return modelo;}
    public void setModelo(String modelo){this.modelo = modelo;}
    public String getCategoria(){return categoria;}
    public void setCategoria (String categoria){this.categoria = categoria;}
    public int getFoto(){return foto;}
    public void setFoto(int foto){this.foto = foto;}

}
