package br.com.beer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString(exclude = {"id"})
public class Beer implements Serializable {

   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "cod_local")
   private int codLocal = 0;

   private int id;

   @NonNull
   private String name;

   @NonNull
   private String tagline;

   @NonNull
   private String description;

   private Boolean favorite = false;

   private String image_url;

    public boolean isValidCod() {
        return codLocal > 0;
    }
}
