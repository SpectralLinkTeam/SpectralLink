    $(document).ready(function () {

    
    $(document).on("click", "#print-cenovnik", function () {
        window.print();
    });

    
    $("#poslovna-godina-admin").click(function () {
        $("#modal-container").empty();
        $("#modal-container").load("/modal-poslovna-godina.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    
    $("#pdv-admin").click(function () {
        $("#modal-container").empty();
        $("#modal-container").load("/modal-pdv.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $("#izdate-fakture").click(function () {
        
    });
    
    $("#izdate-otpremnice").click(function () {
        
    });


    // Cancel dugme
    $(document).on("click", ".odustani", function () {
        $("#modal-container").empty();
        $("#modal-container").css("display", "none");
        $(".overlay").hide();
    });
    // esc key
    $(document).keyup(function(e) {
        if(e.keyCode == 27){
            $("#modal-container").empty();
            $("#modal-container").css("display", "none");
            $(".overlay").hide();
        }
    });
    
    
    
    // ENTITY CREATION
    
    $(document).on("click", "#add-new-product", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-product-form.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", "#add-new-partner", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-partner-form.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });

    $(document).on("click", "#add-new-group", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-group-form.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    


    // ENTITY EDITING
        //roba
    $(document).on("click", ".edit-roba", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-product-form.html");
        fillRobaForm(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
        // poslovni partneri
    $(document).on("click", ".edit-partner", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-partner-form.html");
        fillPartnerForm(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
        // grupe robe
    $(document).on("click", ".edit-grupa", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-group-form.html");
        fillGroupForm(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });

    // AJAX CALLS FOR FILLING FORMS
        function fillRobaForm(id) {
            $.getJSON("/RobaController/searchJsonById/"+id, function(roba) {
                // fill it for editing
                $("#modal-add-new-product").attr("action", "/RobaController/edit");
                $("#roba-id").val(roba.id);
                $('select[name="grupaRobe"]').val(roba.grupaRobe);
                $('input[name="naziv"]').val(roba.naziv);
                $('input[name="cena"]').val(roba.cena);
                $('input[name="jedinicaMere"]').val(roba.jedinicaMere);
                $('input[name="raspKol"]').val(roba.raspKol);
                $('input[name="opis"]').val(roba.opis);
            });
        }
        
        function fillPartnerForm(id) {
            $.getJSON("/BusinessPartnerController/searchJsonById/"+id, function(partner) {
                // fill it for editing
                $("#modal-add-new-partner").attr("action", "/BusinessPartnerController/edit");
                $('input[name="id"]').val(partner.id);
                $('input[name="naziv"]').val(partner.naziv);
                $('input[name="mesto"]').val(partner.mesto);
                $('input[name="adresa"]').val(partner.adresa);
                $('input[name="telefon"]').val(partner.telefon);
                $('input[name="email"]').val(partner.email);
            });

            // FOR SELECT
            /*$.getJSON("/MestoController/getAllJson/", function (mesta) {
                // fill selects
                console.log(mesta);
            })*/
        }

        function fillGroupForm(id) {
            $.getJSON("/GrupaRobeController/searchJsonById/"+id, function(group) {
                $("#modal-add-new-group").attr("action", "/GrupaRobeController/edit");
                $('input[name="id"]').val(group.id);
                $('input[name="naziv"]').val(group.naziv);
                $('select[name="pdv"] option').each(function() { this.selected = (this.text == group.pdv); });
                });

            }


    // NARUDZBENICE
    
    $(document).on("click", ".product-list-link", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/view-product-list.html");
        fillProductList(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });

        function fillProductList(id) {
            $.getJSON("/NarudzbenicaController/searchJsonProductsById/"+id, function(products) {
                for (var p in products){
                    $("#narudzbenica-products").append(
                      '<tr><td>'+products[p].naziv+'</td> <td>'+products[p].cena+'</td> <td>'+products[p].kolicina+'</td> <td>'+products[p].ukupnaCena+'</td></tr>'
                    );
                }

            });
        }
    
});


