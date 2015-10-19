package br.com.caelum.fj57design.converter;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 16/10/15.
 */
public class AlunoConverterGson {

    public String toJson(List<Aluno> alunos) {

        Gson gson = new Gson();
        String s = gson.toJson(alunos);
        StringWriter stringWriter = new StringWriter();

        try {
            JsonWriter writer = gson.newJsonWriter(stringWriter);

            writer.beginObject().name("list").beginArray();
            writer.beginObject().name("aluno").jsonValue(s).endObject();
            writer.endArray().endObject();
            writer.close();
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return s;
    }


}
