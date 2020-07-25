package br.com.beer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String tagline;

    @NonNull
    private String description;

    @NonNull
    private Boolean favorite = false;

    private String image_url;

    public boolean isValidCod() {
        return id > 0;
    }
}
