package com.example.examenfinal.Secciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revistas {
    private String journalId;
    private String description;
    private String portada;
    private  String name;
    public Revistas() {
    }
    public Revistas(String description,String portada,String name,String journalId){
        this.description= description;
        this.portada=portada;
        this.name=name;
        this.journalId=journalId;
    }
    public String getjournalId() {
        return journalId;
    }

    public void setjournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getName() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String Description) {
        this.description = Description;
    }
    public String getPortada() {
        return portada;
    }
    public void setPortada(String Portada) {
        this.portada= Portada;
    }
    public Revistas(JSONObject item) throws JSONException {
        journalId  = item.getString("journal_id").toString();
        name= item.getString("name");
        description= item.getString("description");
        portada= item.getString("portada");
    }
    public static ArrayList<Revistas> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revistas> revistasList = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            revistasList.add(new Revistas(datos.getJSONObject(i)));
        }
        return revistasList;
    }
}
