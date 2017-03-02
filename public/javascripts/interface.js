    $(document).ready(function () {

    
    $(document).on("click", "#print-cenovnik", function () {
        window.print();
    });

    
    $("#poslovna-godina-admin").click(function () {
        $("#modal-container").empty();
        $("#modal-container").load("modal-poslovna-godina.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    
    $("#pdv-admin").click(function () {
        $("#modal-container").empty();
        $("#modal-container").load("modal-pdv.html");
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
    
    
    
    // ENTITY CREATION
    
    $(document).on("click", "#add-new-product", function () {
        $("#modal-container").empty();
        $("#modal-container").load("add-new-product-form.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    $(document).on("click", "#add-new-partner", function () {
        $("#modal-container").empty();
        $("#modal-container").load("add-new-partner-form.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    
    // HORIZONTAL MENU
    
    $("#notif-narudzbenice").click(function () {
        $("section").empty();
        $("section").load("table-narudzbenice.html");
    });
    
    
    // NARUDZBENICE
    
    $(document).on("click", ".product-list-link", function () {
        $("#modal-container").empty();
        $("#modal-container").load("view-product-list.html");
        $("#modal-container").css("display", "block");
        $(".overlay").show();
    });
    
    
});


