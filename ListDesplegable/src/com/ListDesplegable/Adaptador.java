package com.ListDesplegable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class Adaptador extends BaseExpandableListAdapter{

    Context context;

    String [] Titulos = {"Abarrotes","Elctronica","Limpieza","Lacteos"};

    static String [][] SubTitulos = {
            {"Chiles Rajas","Sabritas","Maruchan","Pan Bimbo"},
            {"DVD","Auriculares","Laptop","Memoria USB"},
            {"Fabuloso","Acido Muriatico","Jabon Axion","Escoba"},
            {"Leche Yaqui","Yogurt LALA","Lecha Descremada","Danonino"}
    };

    public Adaptador(Context context){
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return Titulos.length;
    }

    @Override
    public int getChildrenCount(int PosicionGrupo) {
        return SubTitulos[PosicionGrupo].length;
    }

    @Override
    public Object getGroup(int PosicionGrupo) {
        return PosicionGrupo;
    }

    @Override
    public Object getChild(int i, int i2) {
        return 0;
    }

    @Override
    public long getGroupId(int PosicionGrupo) {
        return PosicionGrupo;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int PosicionGrupo, boolean b, View view, ViewGroup viewGroup) {

        View VistaInflate = View.inflate(context,R.layout.layout_titulos,null);

        TextView LabelTitulo = (TextView)VistaInflate.findViewById(R.id.lblTitulos);

        LabelTitulo.setText(Titulos[PosicionGrupo]);

        return VistaInflate;
    }

    @Override
    public View getChildView(int PosicionGrupo, int SubPosicionHijo, boolean b, View view, ViewGroup viewGroup) {

        View VistaInflate = View.inflate(context,R.layout.layout_subtitulos,null);

        TextView LabelSubTitulos = (TextView)VistaInflate.findViewById(R.id.lblSubtitulos);

        LabelSubTitulos.setText(SubTitulos[PosicionGrupo][SubPosicionHijo]);

        return VistaInflate;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
