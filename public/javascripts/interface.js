$.ajaxSetup({
    async: false
});


$(document).ready(function () {


    $.getJSON("/NarudzbenicaController/getNarudzbeniceNumber/", function (broj) {
        // napuni zagradu
        $("#brojNarudzbenica").text(broj);
    });


    $(document).on("click", "#print-me", function () {
        window.print();
    });

    
    $("#poslovna-godina-admin").click(function () {
        $("#modal-container").empty();
        $("#modal-container").load("/modal-poslovna-godina.html");
        fillPoslovnaGodinaForm();
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    
    $("#pdv-admin").click(function () {
        $("#modal-container").empty();
        $("#modal-container").load("/modal-pdv.html");
        fillPdvForm();
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
        fillSelectGroup();
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", "#add-napomena", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-napomena-form.html");
        setStavkaOtpremniceId($(this).data('stavka'));
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", "#add-new-faktura", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-faktura-form.html");
        fillSelectPartner();
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", "#add-new-stavka", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-stavka-form.html");
        fillSelectProduct();
        setFakturaId($(this).data('faktura'));
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", "#add-new-partner", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-partner-form.html");
        fillSelectLocation();
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
        fillSelectGroup();
        fillRobaForm(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", ".edit-faktura", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-faktura-form.html");
        fillSelectPartner();
        fillFakturaForm(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", ".edit-stavka", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-stavka-form.html");
        fillSelectProduct();
        fillStavkaForm(this.id);
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
        // poslovni partneri
    $(document).on("click", ".edit-partner", function () {
        $("#modal-container").empty();
        $("#modal-container").load("/add-new-partner-form.html");
        fillSelectLocation();
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
        
        function fillFakturaForm(id) {
            $.getJSON("/FakturaController/searchJsonById/"+id, function(faktura) {
                // fill it for editing
                $("#modal-add-new-faktura").attr("action", "/FakturaController/edit");
                $("#faktura-id").val(faktura.id);
                $('select[name="kupac"]').val(faktura.kupac);
                $('input[name="id"]').val(partner.id);
            });
        }
        
        function fillStavkaForm(id) {
            $.getJSON("/FakturaController/searchJsonByStavkaId/"+id, function(stavka) {
                // fill it for editing
                $("#modal-add-new-stavka").attr("action", "/FakturaController/editStavka");
                $("#stavka-id").val(stavka.id);
                $('select[name="proizvod"]').val(stavka.proizvod);
                $('input[name="kolicina"]').val(stavka.kolicina);
                $('input[name="rabat"]').val(stavka.rabat);
            });
        }
        
        function fillPartnerForm(id) {
            $.getJSON("/BusinessPartnerController/searchJsonById/"+id, function(partner) {
                // fill it for editing
                $("#modal-add-new-partner").attr("action", "/BusinessPartnerController/edit");
                $('input[name="id"]').val(partner.id);
                $('input[name="naziv"]').val(partner.naziv);
                //$('select[name="mesto"]').val(partner.mesto.id);

                $('input[name="adresa"]').val(partner.adresa);
                $('input[name="telefon"]').val(partner.telefon);
                $('input[name="email"]').val(partner.email);
                $('select[name="mesto"] option').each(function() { this.selected = (this.text == partner.mesto); });
            });
        }

        function fillGroupForm(id) {
            $.getJSON("/GrupaRobeController/searchJsonById/"+id, function(group) {
                $("#modal-add-new-group").attr("action", "/GrupaRobeController/edit");
                $('input[name="id"]').val(group.id);
                $('input[name="naziv"]').val(group.naziv);
                $('select[name="pdv"] option').each(function() { this.selected = (this.text == group.pdv); });
                });

        }
        
        function fillPdvForm() {
            $.getJSON("/PDVController/searchJsonPdv/", function (pdv) {
                for (i in pdv){
                    if(pdv[i].naziv == "Visoki PDV"){
                        $('input[name="stopaVisoki"]').val(pdv[i].procenat);
                    }else{
                        $('input[name="stopaNiski"]').val(pdv[i].procenat);
                    }
                }
            });
        }
        
        function fillPoslovnaGodinaForm() {
            $.getJSON("/BusinessYearController/searchJsonYear/", function (year) {
                console.log(year);
                $("#trenutnaPoslGod").append(year.year);
            });
        }

        function setFakturaId(id) {
        	$('#modal-add-new-stavka').append('<input type="hidden" name="fakturaId" value="'+id+'" />');
        }
        
        function setStavkaOtpremniceId(id) {
        	$('#modal-add-napomena').append('<input type="hidden" name="id" value="'+id+'" />');
        }
        
        // fill select fields
        function fillSelectGroup() {
            $.getJSON("/GrupaRobeController/searchAllJson/", function (grupe) {
                console.log(grupe);
                $.each(grupe, function (i, grupe) {
                    $('select[name="grupaRobe"]').append($('<option>', {
                        value: grupe.id,
                        text : grupe.naziv
                    }));
                });
            });
        }
        
        function fillSelectPartner() {
            $.getJSON("/BusinessPartnerController/searchAllJson/", function (kupci) {
                console.log(kupci);
                $.each(kupci, function (i, kupci) {
                    $('select[name="kupac"]').append($('<option>', {
                        value: kupci.id,
                        text : kupci.naziv
                    }));
                });
            });
        }
        
        function fillSelectProduct() {
            $.getJSON("/RobaController/searchAllJson/", function (robe) {
                console.log(robe);
                $.each(robe, function (i, robe) {
                    $('select[name="proizvod"]').append($('<option>', {
                        value: robe.id,
                        text : robe.naziv
                    }));
                });
            });
        }

        function fillSelectLocation() {
            $.getJSON("/MestoController/searchAllJson/", function (mesta) {
                console.log(mesta);
                $.each(mesta, function (i, mesta) {
                    $('select[name="mesto"]').append($('<option>', {
                        value: mesta.id,
                        text : mesta.naziv
                    }));
                });
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
                for (p in products){
                    $("#narudzbenica-products").append(
                      '<tr><td>'+products[p].naziv+'</td> <td>'+products[p].kolicina+'</td> </tr>'
                    );
                }

            });
        }
    
});




