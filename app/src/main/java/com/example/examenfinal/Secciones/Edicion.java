package com.example.examenfinal.Secciones;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class Edicion {
    private JSONArray objectaux;
    private String aux="";
    private String tittle;
    private String doi;
    private String abstracte;
    private String date;
    private String urlPDF;
    private String urlHTML;
    private String keywords;
    private String section;
    private String autores;
    private String submiss;
    public Edicion( ) {
    }
    public Edicion(String tittle, String doi, String abstracte, String date, String urlPDF, String urlHTML, String keywords,String section,String autores, String submiss) {
        this.tittle = tittle;
        this.doi = doi;
        this.abstracte = abstracte;
        this.date = date;
        this.urlPDF = urlPDF;
        this.urlHTML = urlHTML;
        this.keywords = keywords;
        this.section=section;
        this.autores=autores;
        this.submiss=submiss;
    }
    private ArrayList<Autores> AutoresList;
    public String getsubmiss() {
        return submiss;
    }
    public void setsubmiss(String submiss) {
        this.submiss = submiss;
    }
    public String gettittle() { return tittle;    }
    public void settittle(String tittle) {
        this.tittle = tittle;
    }
    public String getdoi() {
        return doi;
    }
    public void setdoi(String doi) {
        this.doi = doi;
    }
    public String getabstracte() { return abstracte; }
    public void setabstracte(String abstracte) {this.abstracte = abstracte; }
    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
    }
    public String geturlPDF() {
        return urlPDF;
    }
    public void seturlPDF(String urlPDF) {
        this.urlPDF = urlPDF;
    }
    public String geturlHTML() {
        return urlHTML;
    }
    public void seturlHTML(String urlHTML) {
        this.urlHTML = urlHTML;
    }
    public String getkeywords() {
        return keywords;
    }
    public void setkeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getsection() {
        return section;
    }
    public void setsection(String section) {
        this.section = section;
    }
    public String getautores() {
        return autores;
    }
    public void setautores(String autores) {
        this.autores = autores;
    }

    public Edicion(JSONObject item) throws JSONException {
        this.tittle =item.getString("title");
        this.doi = item.getString("doi");
        this.abstracte = item.getString("abstract");
        this.date = item.getString("date_published");
        this.objectaux=item.getJSONArray("authors");

        for (int i=0;i<this.objectaux.length();i++){
            JSONObject object2 = (JSONObject) this.objectaux.get(i);
            Autores au =new Autores();
            aux=aux+object2.getString("nombres");
            if (i!=this.objectaux.length()-1){aux=aux+", ";}
            au.setAutores(object2.getString("nombres"));
            au.setFiliacion(object2.getString("filiacion"));
            au.setEmail(object2.getString("email"));
        }
        this.autores=aux;
        this.urlPDF = item.getString("section");
        this.urlHTML = item.getString("section");
        this.keywords = item.getString("section");
        this.section=item.getString("section");
        this.submiss=item.getString("submission_id");
    }
    public Edicion(JSONObject item,String id) throws JSONException {
        if(item.getString("submission_id").equals(id)){
            this.tittle =item.getString("title");
            this.doi = item.getString("doi");
            this.abstracte = item.getString("abstract");
            this.date = item.getString("date_published");
            this.objectaux=item.getJSONArray("authors");
            for (int i=0;i<this.objectaux.length();i++){
                JSONObject object2 = (JSONObject) this.objectaux.get(i);
                Autores au =new Autores();
                aux=aux+object2.getString("nombres");
                if (i!=this.objectaux.length()-1){aux=aux+", ";}
                au.setAutores(object2.getString("nombres"));
                au.setFiliacion(object2.getString("filiacion"));
                au.setEmail(object2.getString("email"));
            }
            this.autores=aux;
            this.urlPDF = item.getString("section");
            this.urlHTML = item.getString("section");
            this.keywords = item.getString("section");
            this.section=item.getString("section");
            this.submiss=item.getString("submission_id");

        }
    }
    public static ArrayList<Edicion> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Edicion> edicion = new ArrayList<>();
        for (int i = 0; i < datos.length() ; i++) {
            edicion.add(new Edicion(datos.getJSONObject(i)));
        }
        return edicion;
    }
    public static ArrayList<Edicion> getEdicion (JSONArray datos,String submissAid) throws JSONException {
        ArrayList<Edicion> edicionlist = new ArrayList<>();
        for (int i = 0; i < datos.length() ; i++) {
            edicionlist.add(new Edicion(datos.getJSONObject(i),submissAid));
        }
        return edicionlist;
    }
    public static ArrayList<CategoriasCarreras> JsonObjectBuild(List<Edicion> edicion){
        ArrayList<CategoriasCarreras> categoriascarrerasrevista = new ArrayList<>();
        ArrayList<String> secciones= new ArrayList<>();
        String seccion="";
        String aux_seccion="";
        for (int i = 0; i < edicion.size() ; i++) {
            seccion=edicion.get(i).getsection();
            if(!seccion.equals(aux_seccion)){
                secciones.add(seccion);
            }
            aux_seccion=seccion;
        }
        for (int i=0;i< secciones.size();i++){
            List<Edicion> aux_edicion= new ArrayList<>();
            for (int j = 0; j < edicion.size() ; j++) {
                if(secciones.get(i).equals(edicion.get(j).getsection())){
                    aux_edicion.add(new Edicion(
                            edicion.get(j).gettittle(),
                            edicion.get(j).getdoi(),
                            edicion.get(j).getabstracte(),
                            edicion.get(j).getdate(),
                            edicion.get(j).geturlPDF(),
                            edicion.get(j).geturlHTML(),
                            edicion.get(j).getkeywords(),
                            edicion.get(j).getsection(),
                            edicion.get(j).getautores(),
                            edicion.get(j).getsubmiss()
                    ));
                }
            }
            categoriascarrerasrevista.add(new CategoriasCarreras(secciones.get(i), aux_edicion));
        }
        return categoriascarrerasrevista;
    }

}
