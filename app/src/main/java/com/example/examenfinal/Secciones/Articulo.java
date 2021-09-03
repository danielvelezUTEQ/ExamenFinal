package com.example.examenfinal.Secciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

    public class Articulo {
        private String issue_id;
        private String volume;
        private String number;
        private  String year;
        private  String date_published;
        private  String title;
        private  String cover;
        private  String doi;
        public Articulo() {
        }
        public Articulo(String issue_id,String volume,String number,String year, String date_published,String title, String cover,String doi){
            this.issue_id= issue_id;
            this.volume=volume;
            this.number=number;
            this.year=year;
            this.date_published=date_published;
            this.title=title;
            this.cover=cover;
            this.doi=doi;
        }
        public String getissue_id() {
            return issue_id;
        }
        public void setissue_id(String issue_id) {
            this.issue_id = issue_id;
        }
        public String getnumber() {
            return number;
        }
        public void setnumber(String number) {
            this.number = number;
        }
        public String getyear() {
            return year;
        }
        public void setyear(String year) {
            this.year = year;
        }
        public String getvolume() {
            return volume;
        }
        public void setvolume(String volume) {
            this.volume= volume;
        }
        public String getdate_published() {
            return date_published;
        }
        public void setdate_published(String date_published) {
            this.date_published = date_published;
        }
        public String gettitle() {
            return title;
        }
        public void settitle(String title) {
            this.title = title;
        }
        public String getcover() {
            return cover;
        }
        public void setcover(String cover) {
            this.cover= cover;
        }
        public String getdoi() {
            return doi;
        }
        public void setdoi(String doi) {
            this.doi= doi;
        }
        public Articulo(JSONObject item) throws JSONException {
            issue_id  = item.getString("issue_id").toString();
            volume= item.getString("volume");
            number= item.getString("number");
            year= item.getString("year");
            date_published= item.getString("date_published");
            title= item.getString("title");
            cover= item.getString("cover");
            doi= item.getString("doi");
        }
        public static ArrayList<Articulo> JsonObjectsBuild(JSONArray datos) throws JSONException {
            ArrayList<com.example.examenfinal.Secciones.Articulo> articuloList = new ArrayList<>();
            for (int i = 0; i < datos.length(); i++) {
                articuloList.add(new Articulo(datos.getJSONObject(i)));
            }
            return articuloList;
        }
    }
