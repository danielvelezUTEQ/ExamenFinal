package com.example.examenfinal.Secciones;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class CategoriasCarreras {
    private String section, section_id;
    private List<Edicion> edicionList;
    public CategoriasCarreras( ) {
    }
    public void setCategoriasCarrerasList(List<Edicion> edicionList) {
        this.edicionList = edicionList;
    }
    public CategoriasCarreras(String section_id, String section, List<Edicion> edicionList) {
        this.section = section;
        this.section_id = section_id;
        this.edicionList = edicionList;
    }
    public CategoriasCarreras(String section, List<Edicion> edicionList) {
        this.section = section;
        this.edicionList = edicionList;
    }
    public CategoriasCarreras(List<Edicion> edicionList) {
        this.edicionList = edicionList;
    }

    public String getsection() {
        return section;
    }

    public void setsection(String section) {
        this.section = section;
    }

    public String getsection_id() {
        return section_id;
    }
    public void setsection_id(String section_id) {
        this.section_id = section_id;
    }
    public List<Edicion> getedicionList() {
        return edicionList;
    }
    public CategoriasCarreras(JSONObject item) throws JSONException {
        this.section = item.getString("section");
        this.section_id = item.getString("section_id");
    }
    public static ArrayList<CategoriasCarreras> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<CategoriasCarreras> categorias = new ArrayList<>();

        for (int i = 0; i < datos.length() ; i++) {
            categorias.add(new CategoriasCarreras(datos.getJSONObject(i)));
        }
        return categorias;
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
