package com.oktrueque.model;

import javax.lang.model.type.ArrayType;
import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * Created by Tomas on 07-Oct-17.
 */
@Entity
public class Report {

    private Long reportId;
    private Long usersQuantity;
    //    USERS BY STATUS.
    private Long activeUsers;
    private Long registeredUsers;
    private Long bannedUsers;
    private Long deletedUsers;
    //    TRUEQUES BY STATUS.
    private Long proposedTrueque;
    private Long activeTrueque;
    private Long deniedTrueque;
    private Long canceledTrueque;
    private Long confirmedTrueque;
    //    ITEMS BY STATUS
    private Long registeredItem;
    private Long activeItem;
    private Long deletedItem;
    private Long bannedItem;
    //   ITEMS PER CATEGORY
    private Long indumentaria;
    private Long videojuegos;
    private Long muebles;
    private Long herramientas;
    private Long libros;
    private Long instrumentos;
    private Long vehiculos;
    private Long electronica;
    private Long accesorios;
    private Long accesoriosCocina;
    private Long deportes;
    //  TRUEQUES
    private Long truequesIniciados;
    private Long truequesConcretados;
    // DENUNCIAS POR MES





}
